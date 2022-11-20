package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.ExerciseDataSource;

import java.util.ArrayList;
import java.util.List;

public class Session extends BaseActivity {

    List<com.example.sofit.model.Exercise> exercises;
    ExerciseDataSource exerciseDataSource;
    private RecyclerView listaExsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session);
        Bundle extras = getIntent().getExtras();
        String session="";
        if (extras != null) {
            session = extras.getString("idSession");
        }
        setTitle(session+" Session");
        exerciseDataSource = new ExerciseDataSource(getApplicationContext());


        createDrawer(this);
        exerciseDataSource.open();
        exercises = exerciseDataSource
                .getExercisesForSession(session);
        exerciseDataSource.close();

        listaExsView = (RecyclerView) findViewById(R.id.recyclerView);
        listaExsView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaExsView.setLayoutManager(layoutManager);

        List<String> exercisesButtons = new ArrayList<>();
        for (com.example.sofit.model.Exercise ex : exercises) {
            exercisesButtons.add(ex.getName());
        }
        ListaEjerciciosViewAdapter lpAdapter = new ListaEjerciciosViewAdapter(exercisesButtons,
                new ListaEjerciciosViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String item) {
                        Intent i = new Intent(Session.this, Exercise.class);
                        i.putExtra("exerciseId", item);
                        startActivity(i);
                    }
                });

        listaExsView.setAdapter(lpAdapter);

        Button b = (Button)findViewById(R.id.btn_session_addExercise);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Session.this, AddExercise.class));
            }
        });
    }
}