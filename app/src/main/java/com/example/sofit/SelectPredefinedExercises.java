package com.example.sofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.model.Exercise;
import com.example.sofit.remote.ApiUtils;
import com.example.sofit.remote.ExerciseDBAPI;
import com.example.sofit.server.ServerDataMapper;
import com.example.sofit.server.exerciselist.ExerciseData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectPredefinedExercise extends AppCompatActivity {
    Spinner bodyPartsSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_predefined_exercise);
        //Init variables
        this.bodyPartsSpinner=(Spinner) findViewById(R.id.bodyPart_spinner);

        //Init values
        requestBodyParts(ApiUtils.createExerciseDBAPI());
    }

    public void requestBodyParts(ExerciseDBAPI ExerciseDBAPIClient) {
        //Create the call to the api
        Call<List<String>> call = ExerciseDBAPIClient.getListBodyParts(ApiUtils.API_KEY, ApiUtils.HOST);

        // Wait asynchronously for it to end to fill the recycler
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                switch (response.code()) {
                    case 200:
                        //Get the mapped data as list (THE JSON IS A LIST [] WITH NO NAME)
                        List<String> bodyParts = response.body();
                        //Use the array to fill the session
                        fillBodyPartsSpinner(bodyParts);
                        break;
                    default:
                        call.cancel();
                        break;
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e("List - error", t.toString());
            }
        });
    }
    private void fillBodyPartsSpinner(List<String> bodyParts){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, bodyParts);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bodyPartsSpinner.setOnClickListener(view ->
                requestExercisesByBodyPart(ApiUtils.createExerciseDBAPI(), view.getTransitionName())
        );
    }
    public void requestExercisesByBodyPart(ExerciseDBAPI ExerciseDBAPIClient, String bodyPart) {
        //Create the call to the api
        Call<List<ExerciseData>> call = ExerciseDBAPIClient.getListExercisesByBodyPart(bodyPart, ApiUtils.API_KEY, ApiUtils.HOST);

        // Wait asynchronously for it to end to fill the recycler
        call.enqueue(new Callback<List<ExerciseData>>() {
            @Override
            public void onResponse(Call<List<ExerciseData>> call, Response<List<ExerciseData>> response) {
                switch (response.code()) {
                    case 200:
                        //Get the mapped data as list (THE JSON IS A LIST [] WITH NO NAME)
                        List<ExerciseData> data = response.body();
                        //Convert the mapped data to domain
                        List<Exercise> exercises = ServerDataMapper.convertExerciseDataToDomain(data);
                        //Use the array to fill the session
                        fillExerciseByBodyPartRecycler(exercises);



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

    private void fillExerciseByBodyPartRecycler(List<Exercise> exercises){
        List<String> exercisesButtons = new ArrayList<>();
        for (Exercise ex : exercises) {
            exercisesButtons.add(ex.getName());
        }
        ListaEjerciciosViewAdapter lpAdapter = new ListaEjerciciosViewAdapter(exercisesButtons,
                item -> {
                    Intent i = new Intent(SelectPredefinedExercise.this, AddExercise.class);
                    i.putExtra("exerciseId", item);
                    startActivity(i);
                });

        exerciseRecycler.setAdapter(lpAdapter);

    }
}