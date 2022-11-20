package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.Routine;

public class CreateRoutine extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        setTitle("Create Routine");
        createDrawer(this);

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
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
    //noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_my_profile) {
            startActivity(new Intent(CreateRoutine.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_my_routines){
            startActivity(new Intent(CreateRoutine.this, MyProfile.class));
        }
        if (id==R.id.menuItem_my_current_routine){
            startActivity(new Intent(CreateRoutine.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}