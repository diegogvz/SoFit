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
                        startActivity(new Intent(ExercisesOfADay.this,ExerciseActivity.class));
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
            startActivity(new Intent(ExercisesOfADay.this, AddSession.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(ExercisesOfADay.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(ExercisesOfADay.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}