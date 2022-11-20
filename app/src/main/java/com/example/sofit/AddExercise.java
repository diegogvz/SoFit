package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AddExercise extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        setTitle("Add Exercise");
        Bundle extras = getIntent().getExtras();
        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddExercise.this, Session.class);
                i.putExtra("idSession",extras.getString("idSession"));
                if(validarCampos())
                    startActivity(i);
            }
        });

        Button btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(AddExercise.this, Session.class);
                i.putExtra("idSession",extras.getString("idSession"));
                if(validarCampos())
                    startActivity(i);
            }
        });
    }

    private boolean validarCampos(){
        if(R.id.TextEdit_series>0 && R.id.TextEdit_repetitions>0 && R.id.TextEdit_weight>0){
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItem_my_profile) {
            startActivity(new Intent(AddExercise.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_my_routines){
            startActivity(new Intent(AddExercise.this, MyProfile.class));
        }
        if (id==R.id.menuItem_my_current_routine){
            startActivity(new Intent(AddExercise.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}