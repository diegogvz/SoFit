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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.Routine;

public class CreateRoutine extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView mImage;
    Uri mImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        setTitle("Create Routine");

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

        mImage = findViewById(R.id.imageView3);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("image", null);

        if (mImageUri != null) {
            mImage.setImageURI(Uri.parse(mImageUri));
        } else {
            mImage.setImageResource(R.drawable.routine);
        }

        ImageButton btnPhoto = (ImageButton) findViewById(R.id.imageButton4);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                imageSelect();
            }
        });
    }

    private void clickOnItem(){
        Routine routine = new Routine();
        EditText nombre = (EditText)findViewById(R.id.editTextNombreRutina);
        routine.setNombreRutina(nombre.getText().toString());
        routine.setUser(" ");
        routine.setImage(String.valueOf(R.id.imageView3));
        RoutineDataSource routineDataSource =
                new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        routineDataSource.createRoutine(routine);
        routineDataSource.close();
        startActivity(new Intent(CreateRoutine.this, MyRoutines.class));
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
                    SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(this);
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