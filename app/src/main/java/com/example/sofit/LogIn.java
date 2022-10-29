package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Inicio de sesión");

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarCampos()){
                    startActivity(new Intent(LogIn.this, RecyclerMyRoutines.class));
                }
            }

            private boolean validarCampos(){
                EditText nombre = (EditText) findViewById(R.id.editTextNombre);
                if(nombre != null)
                    return true;
                else
                    return false;
            }
        });
    }
}