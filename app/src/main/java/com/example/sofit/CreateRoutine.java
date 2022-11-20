package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.Routine;

public class CreateRoutine extends AppCompatActivity {

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
    }

    private void clickOnItem(){
        Routine routine = new Routine();
        routine.setNombre_rutina(String.valueOf(R.id.editTextNombreRutina));
        RoutineDataSource routineDataSource =
                new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        routineDataSource.createRoutine(routine);
        routineDataSource.close();
        startActivity(new Intent(CreateRoutine.this, MyRoutines.class));
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
            startActivity(new Intent(CreateRoutine.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(CreateRoutine.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(CreateRoutine.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}