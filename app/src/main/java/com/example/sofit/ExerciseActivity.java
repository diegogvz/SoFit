package com.example.sofit;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.model.Exercise;

public class ExerciseActivity extends AppCompatActivity {
    private Exercise exercise;
    private RecyclerView seriesRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise);
        seriesRecycler=findViewById(R.id.seriesRecycler);
        //exercise=(Exercise) getIntent().getSerializableExtra("EX_OBJ");


//        setTitle(exercise.getName());
          setTitle("Ejercicio X");
    }

}
