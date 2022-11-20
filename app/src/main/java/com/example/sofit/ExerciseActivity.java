package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.adapters.ListaSerieViewAdapter;
import com.example.sofit.data.SeriesDataSource;
import com.example.sofit.model.Exercise;
import com.example.sofit.model.Serie;

import java.util.List;

public class ExerciseActivity extends AppCompatActivity {
    private Exercise exercise;
    private RecyclerView seriesRecycler;
    SeriesDataSource seriesDataSource;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seriesDataSource=new SeriesDataSource(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        String exercise="";
        if (extras != null) {
            exercise = extras.getString("exerciseId");
        }
        setTitle(exercise);
        setContentView(R.layout.exercise);
        seriesRecycler=findViewById(R.id.seriesRecycler);

        seriesDataSource.open();
        List<Serie> series=seriesDataSource.getSeriesForExercise(exercise);
        seriesDataSource.close();

        ListaSerieViewAdapter listaSerieViewAdapter=new ListaSerieViewAdapter(series);
        seriesRecycler.setAdapter(listaSerieViewAdapter);




    }

}
