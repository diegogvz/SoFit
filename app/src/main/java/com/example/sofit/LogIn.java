package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.data.SessionDataSource;
import com.example.sofit.model.Exercise;
import com.example.sofit.model.Session;

import java.util.List;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);

        setTitle("Log In");
        printDataForTesting2();
        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarCampos()) {
                    startActivity(new Intent(LogIn.this, MyRoutines.class));
                }
            }

            private boolean validarCampos() {
                EditText nombre = (EditText) findViewById(R.id.editTextNombre);
                if (nombre != null)
                    return true;
                else
                    return false;
            }
        });

    }
    private void printDataForTesting(){
        ExerciseDataSource exerciseDataSource = new ExerciseDataSource(getApplicationContext());
        exerciseDataSource.open();
        List<Exercise> exerciseList = exerciseDataSource.getAllExercises();

        for (Exercise exercise : exerciseList) {
            System.out.println("\n\n--------------------"+exercise);
        }
        exerciseDataSource.close();
    }

    private void printDataForTesting2(){
        SessionDataSource sessionDataSource = new SessionDataSource(getApplicationContext());
        sessionDataSource.open();
        List<Session> sessionList = sessionDataSource.getAllSessions();

        for (Session session : sessionList) {
            System.out.println("\n\n--------------------"+session);
        }
        sessionDataSource.close();
    }
}