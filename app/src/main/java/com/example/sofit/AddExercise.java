package com.example.sofit;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.Exercise;
import com.example.sofit.model.ModelExercise;
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
        //createDrawer(this);
        setContentView(R.layout.activity_add_exercise);

        setTitle("Add Exercise");

        //Sacar los extras
        Bundle extras = getIntent().getExtras();
        predefinedExercise =(ModelExercise) extras.getParcelable("predefinedExercise");
        session = extras.getString("sessionId");

        Button predefinedExerciseButton = (Button) findViewById(R.id.button_select_predefined_exercise);
        //Comprobar si proviene de select predefied exercises
        //Si es que si, rellenamos automaticamente el formulario.
        if (predefinedExercise != null) {
            fillFormAddExercise();
        }

        predefinedExerciseButton.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, SelectPredefinedExercises.class);
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
                startActivity(new Intent(AddExercise.this, Session.class));
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
        Picasso.get().load(predefinedExercise.getImage()).into(imageViewExercise);
        //Set title

        System.out.println("Ex obj name: "+predefinedExercise.getName());
        editTextExerciseTitle.setText(predefinedExercise.getName());
        System.out.println("EditText"+editTextExerciseTitle.getText());
    }


    private void clickOnItem(){
        Exercise exercise = new Exercise();
        exercise.setName(String.valueOf(R.id.editTextExerciseTitle));
        //exercise.setImage(String.valueOf(R.id.imageView2));ser
        ExerciseDataSource exerciseDataSource =
                new ExerciseDataSource(getApplicationContext());
        exerciseDataSource.open();
        exerciseDataSource.createExercise(exercise);
        exerciseDataSource.close();
        startActivity(new Intent(AddExercise.this, Session.class));
    }

    private boolean validarCampos(){
        if(R.id.TextEdit_series>0 && R.id.TextEdit_repetitions>0 && R.id.TextEdit_weight>0){
            return true;
        }


        return false;
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