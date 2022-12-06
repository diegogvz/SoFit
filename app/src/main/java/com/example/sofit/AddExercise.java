package com.example.sofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.Exercise;

public class AddExercise extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);

        setTitle("Add Exercise");

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
                startActivity(new Intent(AddExercise.this, AddSession.class));
            }
        });
    }

    private void clickOnItem(){
        Exercise exercise = new Exercise();
        exercise.setName(String.valueOf(R.id.editTextName));
        exercise.setImage(String.valueOf(R.id.imageView2));
        ExerciseDataSource exerciseDataSource =
                new ExerciseDataSource(getApplicationContext());
        exerciseDataSource.open();
        exerciseDataSource.createExercise(exercise);
        exerciseDataSource.close();
        startActivity(new Intent(AddExercise.this, AddSession.class));
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.menu_misrutinas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItem_misRutinas_misRutinas) {
            startActivity(new Intent(AddExercise.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(AddExercise.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(AddExercise.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}