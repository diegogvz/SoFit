package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class SessionActivity extends BaseActivity {

    List<Exercise> exercises;
    ExerciseDataSource exerciseDataSource;
    private RecyclerView listaExsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);

        exerciseDataSource = new ExerciseDataSource(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        String session="";
        if (extras != null) {
            session = extras.getString("idSession");
        }
        setTitle(session);

        createDrawer(this);
        exerciseDataSource.open();
        exercises = exerciseDataSource
                .getExercisesForSession(session);
        exerciseDataSource.close();
        exercises.add(new Exercise("Squads - hardcoded"));
        exercises.add(new Exercise("Leg press - hardcoded"));
        exercises.add(new Exercise("Cardio - hardcoded"));
        exercises.add(new Exercise("Stretching - hardcoded"));

        listaExsView = (RecyclerView) findViewById(R.id.recyclerView);
        listaExsView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaExsView.setLayoutManager(layoutManager);

        List<String> exercisesButtons = new ArrayList<>();
        for (Exercise ex : exercises) {
            exercisesButtons.add(ex.getName());
        }
        ListaEjerciciosViewAdapter lpAdapter = new ListaEjerciciosViewAdapter(exercisesButtons,
                new ListaEjerciciosViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        Intent i = new Intent(SessionActivity.this, ExerciseActivity.class);
                        i.putExtra("exerciseId", item);
                        startActivity(i);
                    }
                });

        listaExsView.setAdapter(lpAdapter);
    }
}