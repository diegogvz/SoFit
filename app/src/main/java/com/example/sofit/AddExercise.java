package com.example.sofit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.ModelExercise;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class AddExercise extends BaseActivity {
    private ModelExercise predefinedExercise;
    private String session;
    private EditText editTextExerciseTitle;
    private ImageView imageViewExercise;
    private static final int PICK_IMAGE = 1;
    private ImageView imageView;
    Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        setTitle("Add Exercise");


        //Sacar los extras
        Bundle extras = getIntent().getExtras();
        predefinedExercise = extras.getParcelable("predefinedExercise");
        session = extras.getString("idSession");


        Button predefinedExerciseButton = findViewById(R.id.button_select_predefined_exercise);
        //Comprobar si proviene de select predefied exercises
        //Si es que si, rellenamos automaticamente el formulario.
        if (predefinedExercise != null) {
            fillFormAddExercise();
        }

        predefinedExerciseButton.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, SelectPredefinedExercises.class);
            startActivity(i);
        });

        Button btnAccept =findViewById(R.id.buttonAceptarEjercicio);
        btnAccept.setOnClickListener(view -> {
            if(validateCampos()){
                clickOnAccept();
            }
        });

        Button btnCancel = findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, Session.class);
            i.putExtra("idSession", getIntent().getExtras().getString("idSession"));
            startActivity(i);
        });

        imageView = findViewById(R.id.imageView_exercise);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("image", null);

        if (mImageUri != null) {
            imageView.setImageURI(Uri.parse(mImageUri));
        } else {
            imageView.setImageResource(R.drawable.exercise);
        }


    }

    private void fillFormAddExercise() {
        //Load gif
        Picasso.get().load(predefinedExercise.getImage()).into(imageViewExercise);
        //Set title
        editTextExerciseTitle.setText(predefinedExercise.getName());
    }


    private void clickOnAccept(){
        ModelExercise exercise = new ModelExercise();

        exercise.setName(String.valueOf(((EditText)findViewById(R.id.editTextExerciseTitle)).getText()));
        exercise.setImage(String.valueOf(R.id.imageView_exercise));
        ExerciseDataSource exerciseDataSource =
                new ExerciseDataSource(getApplicationContext());
        exerciseDataSource.open();
        String whichSession = getIntent().getExtras().getString("idSession");
        exerciseDataSource.createExercise(exercise,whichSession);
        exerciseDataSource.close();
        Intent i = new Intent(AddExercise.this, Session.class);
        i.putExtra("idSession", getIntent().getExtras().getString("idSession"));
        startActivity(i);
    }

    private boolean validateCampos(){
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


}