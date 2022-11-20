package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;

import java.util.ArrayList;

public class SessionActivity extends AppCompatActivity {

    List<Exercise> exercises;
    ExerciseDataSource exerciseDataSource;
    private RecyclerView listaExsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises_of_aday);
        exerciseDataSource = new ExerciseDataSource(getApplicationContext());
        Bundle extras = getIntent().getExtras();
        String session="";
        if (extras != null) {
            session = extras.getString("idSession");
        }
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

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
// Inflate the menu
        getMenuInflater().inflate(R.menu.menu_misrutinas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_misRutinas_misRutinas) {
            startActivity(new Intent(SessionActivity.this, AddSession.class));
        }
        if (id == R.id.menuItem_misRutinas_perfil) {
            startActivity(new Intent(SessionActivity.this, MyProfile.class));
        }
        if (id == R.id.menuItem_misRutinas_rutinas) {
            startActivity(new Intent(SessionActivity.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}