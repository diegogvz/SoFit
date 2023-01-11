package com.example.sofit;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.data.SeriesDataSource;
import com.example.sofit.model.ModelExercise;
import com.example.sofit.model.Serie;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class AddExercise extends BaseActivity {
    private ModelExercise predefinedExercise;
    private String session;
    private EditText editTextExerciseTitle;
    private ImageView imageViewExercise;
    private static final int PICK_IMAGE = 1;
    private ImageView mImage;
    Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        setTitle("Add Exercise");


        //Sacar los extras
        Bundle extras = getIntent().getExtras();
        this.predefinedExercise =(ModelExercise) extras.getParcelable("predefinedExercise");
        this.session = extras.getString("idSession");


        Button predefinedExerciseButton = (Button) findViewById(R.id.button_select_predefined_exercise);
        //Comprobar si proviene de select predefied exercises
        //Si es que si, rellenamos automaticamente el formulario.
        if (predefinedExercise != null) {
            fillFormAddExercise();
        }

        predefinedExerciseButton.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, SelectPredefinedExercises.class);
            i.putExtra("idSession", session);
            startActivity(i);
        });

        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos()){
                    clickOnItem();
                }
            }
        });

        Button btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(AddExercise.this, Session.class);
                i.putExtra("idSession", getIntent().getExtras().getString("idSession"));
                startActivity(i);
            }
        });

        mImage = findViewById(R.id.imageView2);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("image", null);

        if (mImageUri != null) {
            mImage.setImageURI(Uri.parse(mImageUri));
        } else {
            mImage.setImageResource(R.drawable.exercise);
        }

        ImageButton btnPhoto = (ImageButton) findViewById(R.id.imageButton3);
        btnPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                imageSelect();
            }
        });

    }

    private void fillFormAddExercise() {
        //Load gif
        Picasso.get().load(predefinedExercise.getImage()).into((ImageView) findViewById(R.id.imageView2));
        //Set title

    //    System.out.println("Ex obj name: "+predefinedExercise.getName());
        ((EditText)findViewById(R.id.editTextExerciseTitle)).setText(String.valueOf(predefinedExercise.getName()));
//        System.out.println("EditText"+editTextExerciseTitle.getText());
    }


    private void clickOnItem(){
        ModelExercise exercise = new ModelExercise();

        exercise.setName(String.valueOf(((EditText)findViewById(R.id.editTextExerciseTitle)).getText()));
        //exercise.setImage(String.valueOf(R.id.imageView2));
        if(predefinedExercise!=null){
            exercise.setImage(predefinedExercise.getImage());
        }
        else{
            exercise.setImage("");
        }

        ExerciseDataSource exerciseDataSource =
                new ExerciseDataSource(getApplicationContext());
        exerciseDataSource.open();
        //String whichSession = getIntent().getExtras().getString("idSession");
        exerciseDataSource.createExercise(exercise,session);
        exerciseDataSource.close();
        SeriesDataSource sds = new SeriesDataSource(getApplicationContext());
        int numSeries = Integer.parseInt(((EditText)findViewById(R.id.TextEdit_series)).getText().toString());
        while (numSeries>0){
            sds.open();
            int reps = Integer.parseInt(((EditText)findViewById(R.id.TextEdit_repetitions)).getText().toString());
            int weight = Integer.parseInt(((EditText)findViewById(R.id.TextEdit_weight)).getText().toString());
            sds.addSerieOnExercise(new Serie(reps,weight),exercise.getName());
            sds.close();
            numSeries--;
        }
        Intent i = new Intent(AddExercise.this, Session.class);
        //i.putExtra("idSession", getIntent().getExtras().getString("idSession"));
        i.putExtra("idSession", session);
        startActivity(i);
    }

    private boolean validarCampos(){
        if(((EditText)findViewById(R.id.editTextExerciseTitle)).getText().toString().isEmpty()
                || ((EditText)findViewById(R.id.TextEdit_weight)).getText().toString().isEmpty()
                || ((EditText)findViewById(R.id.TextEdit_repetitions)).getText().toString().isEmpty()
                || ((EditText)findViewById(R.id.TextEdit_series)).getText().toString().isEmpty()){
            Snackbar.make(findViewById(R.id.tableLayout2),"Enter all the data",
                    Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(Integer.parseInt(((EditText)findViewById(R.id.TextEdit_weight)).getText().toString()) <= 0
                || Integer.parseInt(((EditText)findViewById(R.id.TextEdit_repetitions)).getText().toString()) <= 0
                || Integer.parseInt(((EditText)findViewById(R.id.TextEdit_series)).getText().toString()) <= 0){
            Snackbar.make(findViewById(R.id.tableLayout2),"Numerical data must be greater than 0",
                    Snackbar.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    public void imageSelect() {
        permissionsCheck();
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Elige una foto"), PICK_IMAGE);
    }

    public void permissionsCheck() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    mImageUri=data.getData();
                    SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("image", String.valueOf(mImageUri));
                    editor.commit();
                    mImage.setImageURI(mImageUri);
                    mImage.invalidate();
                }
            }
        }
    }
}