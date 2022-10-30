package com.example.sofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SetExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exercise);

        setTitle("Añadir Ejercicio");

        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validarCampos())
                    startActivity(new Intent(SetExercise.this, AddSession.class));
            }
        });

        Button btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(SetExercise.this, AddSession.class));
            }
        });
    }

    private boolean validarCampos(){
        if(R.id.editTextSeries>0 && R.id.editTextRepetitions>0 && R.id.editTextWeight>0){
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
// Inflate the menu
        getMenuInflater().inflate(R.menu.menu_misrutinas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_misRutinas_misRutinas) {
            startActivity(new Intent(SetExercise.this, RecyclerMyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(SetExercise.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(SetExercise.this, RecyclerActualRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}