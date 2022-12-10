package com.example.sofit;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.ModelExercise;
import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.ModelExercise;
import com.example.sofit.remote.ApiUtils;
import com.example.sofit.remote.ExerciseDBAPI;
import com.example.sofit.server.ServerDataMapper;
import com.example.sofit.server.exerciselist.ExerciseData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Session extends BaseActivity {

    private List<ModelExercise> exercises;
    private ExerciseDataSource exerciseDataSource;
    private List<ModelExercise> exerciseList;
    private RecyclerView exerciseRecycler;
    private String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialization
        setContentView(R.layout.activity_session);
        Bundle extras = getIntent().getExtras();


        //Get the session we're in
        session = "";
        if (extras != null) {
            session = extras.getString("idSession");
        }
        setTitle(session + " Session");

        //Get the exercises from database
        loadExercises();


        //Drawer
        createDrawer(this);


        //Prepare the database
        exerciseDataSource = new ExerciseDataSource(getApplicationContext());


        //Get data from the database
        exerciseDataSource.open();
        exercises = exerciseDataSource.getExercisesForSession(session);
        exerciseDataSource.close();

        //----Init the recycler----
        exerciseRecycler = findViewById(R.id.recycler_sessionExercises);
        //Get the recycler
        exerciseRecycler = findViewById(R.id.recycler_predefinedExercises);

        //Set the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        exerciseRecycler.setLayoutManager(layoutManager);

        //Set has fixed size, increases performance for
        //Recyclers that get items added or removed frequently
        exerciseRecycler.setHasFixedSize(true);

        //--------------------------

        //Get data from database and fill the exercise recycler
        fillRecycler();


        //Create the event for the button to add new exercise
        createEventAddExercise();

    }

    private void createEventAddExercise() {
        FloatingActionButton b = (FloatingActionButton) findViewById(R.id.btn_session_addExercise);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(Session.this, AddExercise.class);
                i.putExtra("idSession", session);
                startActivity(i);
            }
        });
    }


    private void fillRecycler(List<com.example.sofit.model.ModelExercise> exercises) {


        List<ModelExercise> exercisesButtons = new ArrayList<>();
        for (ModelExercise ex : exercises) {
            exercisesButtons.add(ex);
        }
        ListaEjerciciosViewAdapter lpAdapter = new ListaEjerciciosViewAdapter(exercisesButtons,
                (ListaEjerciciosViewAdapter.OnItemClickListener) item -> {
                    Intent i = new Intent(Session.this, ModelExercise.class);
                    i.putExtra("exerciseId", item);
                    startActivity(i);
                }, (ListaEjerciciosViewAdapter.DeleteListener) item -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(Session.this);
            builder.setMessage("Do you want to delete this routine?").setPositiveButton("Yes",
                    (dialog, which) -> {
                        deleteExercise(item);
                        startActivity(new Intent(Session.this, Session.class));
                    }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            }).show();
        });

        exerciseRecycler.setAdapter(lpAdapter);


    }

    private void loadExercises() {
        ExerciseDataSource routineDataSource = new ExerciseDataSource(getApplicationContext());
        routineDataSource.open();
        exercises = routineDataSource.getExercisesForSession(session);
        routineDataSource.close();
        private void deleteExercise (ModelExercise item){
            ExerciseDataSource eds = new ExerciseDataSource(getApplicationContext());
            eds.open();
            eds.deleteSession(item);
            eds.close();
        }


    }
}