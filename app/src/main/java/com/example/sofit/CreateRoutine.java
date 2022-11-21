package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.Routine;
import com.google.android.material.snackbar.Snackbar;

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
                if (name.getText().toString().trim().isEmpty())
                    Snackbar.make(findViewById(R.id.main_layout), "Enter all the data",
                            Snackbar.LENGTH_LONG).show();
                else
                    clickOnItem(name);

            }
        });
    }

    private void clickOnItem(EditText name) {
        UserDataSource userDataSource=new UserDataSource(getApplicationContext());
        userDataSource.open();
        Routine routine = new Routine();
        routine.setName(name.getText().toString());
        routine.setUserId(userDataSource.getAllUsers().get(0).getName());
        userDataSource.close();
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
        if (id == R.id.menuItem_my_routines) {
            startActivity(new Intent(CreateRoutine.this, MyProfile.class));
        }
        if (id == R.id.menuItem_my_current_routine) {
            startActivity(new Intent(CreateRoutine.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}