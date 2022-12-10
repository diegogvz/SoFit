package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.Exercise;
import com.google.android.material.snackbar.Snackbar;

public class AddExercise extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        Bundle extras = getIntent().getExtras();
        setTitle(extras.getString("idSession"));
        Spinner sp = (Spinner)findViewById(R.id.spinner5);
        String[] sessions = {extras.getString("idSession")};
        sp.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,sessions));
        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validarCampos()){
                    Intent i = new Intent(AddExercise.this, Session.class);
                    i.putExtra("idSession",extras.getString("idSession"));
                    String name = String.valueOf(((EditText)findViewById(R.id.textEdit_nameExercise)).getText());
                    addTheExercise(new Exercise(name," "), (String) sp.getSelectedItem());
                    startActivity(i);
                }
            }
        });

        Button btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(AddExercise.this, Session.class);
                i.putExtra("idSession",extras.getString("idSession"));
                startActivity(i);
            }
        });
    }

    private void addTheExercise(Exercise exercise, String idSession) {

        ExerciseDataSource eds = new ExerciseDataSource(getApplicationContext());
        eds.open();
        eds.addExercise(exercise,idSession);
        eds.close();

    }

    private boolean validarCampos(){

        if(((EditText)findViewById(R.id.textEdit_nameExercise)).getText().toString().isEmpty()
            || ((EditText)findViewById(R.id.TextEdit_repetitions)).getText().toString().isEmpty()
                || ((EditText)findViewById(R.id.TextEdit_series)).getText().toString().isEmpty()
                    || ((EditText)findViewById(R.id.TextEdit_weight)).getText().toString().isEmpty())
        {
            Snackbar.make(findViewById(R.id.tableLayoutAddExercise),"Enter all the data",Snackbar.LENGTH_LONG).show();
            return false;
        }

        if(Integer.parseInt(((EditText)findViewById(R.id.TextEdit_repetitions)).getText().toString())<=0
                || Integer.parseInt(((EditText)findViewById(R.id.TextEdit_series)).getText().toString())<=0
                || Integer.parseInt(((EditText)findViewById(R.id.TextEdit_weight)).getText().toString())<=0)
        {
            Snackbar.make(findViewById(R.id.tableLayoutAddExercise),"Enter all the data",Snackbar.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

}