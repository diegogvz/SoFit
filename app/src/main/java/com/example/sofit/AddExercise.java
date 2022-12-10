package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.ModelExercise;

public class AddExercise extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        setTitle("Add Exercise");
        Bundle extras = getIntent().getExtras();
        Spinner sp = (Spinner)findViewById(R.id.spinner5);
        String[] sessions = {extras.getString("idSession")};
        sp.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item,sessions));

        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddExercise.this, Session.class);
                i.putExtra("idSession", sp.getSelectedItem().toString());
                if(validarCampos())
                    addExercise(new ModelExercise(((EditText)findViewById(R.id.textEdit_nameExercise)).getText().toString(),""));
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

    private void addExercise(ModelExercise modelExercise) {
        ExerciseDataSource eds = new ExerciseDataSource(getApplicationContext());
        eds.open();
        eds.addExercise(modelExercise,getIntent().getExtras().getString("idSession"));
        eds.close();
    }


//    private void fillFormAddExercise() {
//        //Load gif
//        Picasso.get().load(predefinedExercise.getImage()).into(imageViewExercise);
//        //Set title
//
//        System.out.println("Ex obj name: "+predefinedExercise.getName());
//        editTextExerciseTitle.setText(predefinedExercise.getName());
//        System.out.println("EditText"+editTextExerciseTitle.getText());
//    }

    private boolean validarCampos(){
        if(R.id.TextEdit_series>0 && R.id.TextEdit_repetitions>0 && R.id.TextEdit_weight>0){
            return true;
        }

        return false;
    }

}