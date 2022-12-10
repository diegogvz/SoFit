package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
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


    private void fillFormAddExercise() {
        //Load gif
        Picasso.get().load(predefinedExercise.getImage()).into(imageViewExercise);
        //Set title

        System.out.println("Ex obj name: "+predefinedExercise.getName());
        editTextExerciseTitle.setText(predefinedExercise.getName());
        System.out.println("EditText"+editTextExerciseTitle.getText());
    }

    private boolean validarCampos(){
        if(R.id.TextEdit_series>0 && R.id.TextEdit_repetitions>0 && R.id.TextEdit_weight>0){
            return true;
        }

        return false;
    }

}