package com.example.sofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

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
    Uri imageUri;

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

        ImageButton btnPhoto = (ImageButton) findViewById(R.id.imageButton3);
        btnPhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openGallery();
            }
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

    @Override
    protected void onActivityResult(int requestedCode, int resultCode, Intent data) {
        super.onActivityResult(requestedCode, resultCode, data);
        if (resultCode == RESULT_OK && requestedCode == PICK_IMAGE) {
            imageUri = data.getData();
            ImageView photo = (ImageView) findViewById(R.id.imageView2);
            photo.setImageURI(imageUri);
        }
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }
}