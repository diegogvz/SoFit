package com.example.sofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.sofit.adapters.ListDiasViewAdapter;
import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.model.Day;

import java.util.ArrayList;

public class ExercisesOfADay extends AppCompatActivity {

    ArrayList<String> exercises = new ArrayList<String>();
    private RecyclerView listaExsView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_of_aday);

        exercises.add("Squads");
        exercises.add("Leg press");
        exercises.add("Cardio");
        exercises.add("Stretching");

        listaExsView=(RecyclerView) findViewById(R.id.recyclerView);
        listaExsView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listaExsView.setLayoutManager(layoutManager);

        ListaEjerciciosViewAdapter lpAdapter=new ListaEjerciciosViewAdapter(exercises,
                new ListaEjerciciosViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(String item) {
                        /* Change current routine to the one clicked */
                    }
                });

        listaExsView.setAdapter(lpAdapter);
    }
}