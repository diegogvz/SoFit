package com.example.sofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.sofit.model.Exercise;
import com.example.sofit.remote.ApiUtils;
import com.example.sofit.remote.ExerciseDBAPI;
import com.example.sofit.server.ServerDataMapper;
import com.example.sofit.server.exerciselist.ExerciseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectPredefinedExercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_predefined_exercise);
    }

    public void requestBodyParts(ExerciseDBAPI ExerciseDBAPIClient, String bodyPart) {
        //Create the call to the api
        Call<List<String>> call = ExerciseDBAPIClient.getListExercisesByBodyPart(bodyPart,ApiUtils.API_KEY, ApiUtils.HOST);

        // Wait asynchronously for it to end to fill the recycler
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                switch (response.code()) {
                    case 200:
                        //Get the mapped data as list (THE JSON IS A LIST [] WITH NO NAME)
                        List<String> bodyParts = response.body();
                        //Use the array to fill the session
                        fillSpinner(bodyParts);
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

    public void requestExercisesByBodyPart(ExerciseDBAPI ExerciseDBAPIClient) {
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
                        List<Exercise> exercises = ServerDataMapper.convertExerciseDataToDomain(data);
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