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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.Routine;

public class CreateRoutine extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        setTitle("Create Routine");

        ImageButton btnPhoto = (ImageButton) findViewById(R.id.imageButton4);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openGallery();
            }
        });

        Button btnAceptar = (Button) findViewById(R.id.btnAceptar);
        EditText name = (EditText) findViewById(R.id.editTextNombreRutina);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(name.getText().equals(" ")))
                    clickOnItem();
                else
                    Toast.makeText(getApplicationContext(),
                            getString(R.string.complete), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickOnItem(){
        Routine routine = new Routine();
        routine.setNombre_rutina(String.valueOf(R.id.editTextNombreRutina));
        routine.setImage(String.valueOf(R.id.imageView3));
        RoutineDataSource routineDataSource =
                new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        routineDataSource.createRoutine(routine);
        routineDataSource.close();
        startActivity(new Intent(CreateRoutine.this, MyRoutines.class));
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestedCode, int resultCode, Intent data) {
        super.onActivityResult(requestedCode, resultCode, data);
        if (resultCode == RESULT_OK && requestedCode == PICK_IMAGE) {
            imageUri = data.getData();
            ImageView photo = (ImageView) findViewById(R.id.imageView3);
            photo.setImageURI(imageUri);
        }
    }
}