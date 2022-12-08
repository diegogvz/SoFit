package com.example.sofit;


import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.ModelExercise;
import com.example.sofit.remote.ApiUtils;
import com.example.sofit.remote.ExerciseDBAPI;
import com.example.sofit.server.ServerDataMapper;
import com.example.sofit.server.exerciselist.ExerciseData;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.callback.Callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Session extends BaseActivity {

    private List<com.example.sofit.model.ModelExercise> exercises;
    private ExerciseDataSource exerciseDataSource;
    private List<com.example.sofit.model.ModelExercise> exerciseList;
    private RecyclerView exerciseRecycler;
    private String session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialization
        setContentView(R.layout.activity_session);
        exerciseList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();


        //Get the session we're in
        session = "";
        if (extras != null) {
            session = extras.getString("idSession");
        }
        setTitle(session + " Session");

        //Drawer
        createDrawer(this);


        //Prepare the database
        exerciseDataSource = new ExerciseDataSource(getApplicationContext());


        //Get data from the database
        exerciseDataSource.open();
        exercises = exerciseDataSource.getExercisesForSession(session);
        exerciseDataSource.close();

        //----Init the recycler----
        exerciseRecycler = findViewById(R.id.recycler_predefinedExercises);

        //Set the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        exerciseRecycler.setLayoutManager(layoutManager);

        //Set has fixed size, increases performance for
        //Recyclers that get items added or removed frequently
        exerciseRecycler.setHasFixedSize(true);

        //--------------------------

        //Get data from API and fill the recycler
        requestAllExercises(ApiUtils.createExerciseDBAPI());


        //Create the event for the button to add new exercise
        createEventAddExercise();

    }

    private void createEventAddExercise() {
        Button b = findViewById(R.id.btn_session_addExercise);
        b.setOnClickListener(view -> {
            Intent i = new Intent(Session.this, AddExercise.class);
            i.putExtra("idSession", session);
            startActivity(i);
        });
    }


    private void fillRecycler(List<com.example.sofit.model.ModelExercise> exercises) {


        List<String> exercisesButtons = new ArrayList<>();
        for (com.example.sofit.model.ModelExercise ex : exercises) {
            exercisesButtons.add(ex.getName());
        }
        ListaEjerciciosViewAdapter lpAdapter = new ListaEjerciciosViewAdapter(exercisesButtons, item -> {
            Intent i = new Intent(Session.this, Exercise.class);
            i.putExtra("exerciseId", item);
            startActivity(i);
        });

        exerciseRecycler.setAdapter(lpAdapter);


    }

    public void requestAllExercises(ExerciseDBAPI ExerciseDBAPIClient) {
        //Create the call to the api
        Call<List<ExerciseData>> call = ExerciseDBAPIClient.getListExercises(ApiUtils.API_KEY, ApiUtils.HOST);

        // Wait asynchronously for it to end to fill the recycler
        call.enqueue(new Callback<List<ExerciseData>>() {
            @Override
            public void onResponse(Call<List<ExerciseData>> call, Response<List<ExerciseData>> response) {
                switch (response.code()) {
                    case 200:
                        //Get the mapped data as list (THE JSON IS A LIST [] WITH NO NAME)
                        List<ExerciseData> data = response.body();
                        //Convert the mapped data to domain
                        List<ModelExercise> exercises = ServerDataMapper.convertExerciseDataListToDomain(data);
                        //Use the array to fill the session
                        fillRecycler(exercises);
                        break;
                    default:
                        call.cancel();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<ExerciseData>> call, Throwable t) {
                Log.e("List - error", t.toString());
            }
        });
    }

}