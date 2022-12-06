package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.sofit.model.Exercise;
import com.example.sofit.remote.ApiUtils;
import com.example.sofit.remote.ExerciseDBAPI;
import com.example.sofit.server.ServerDataMapper;
import com.example.sofit.server.exerciselist.ExerciseData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExercise extends BaseActivity {
    private String predefinedExerciseName;
    private String forSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        setTitle("Add Exercise");
        Bundle extras = getIntent().getExtras();
        predefinedExerciseName=extras.getString("predefinedExercise");
        if(predefinedExerciseName!=null){
            requestExerciseAndFillForm();
        }
        forSession=extras.getString("sessionId");


        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        Button predefinedExerciseButton=(Button) findViewById(R.id.button_select_predefined_exercise);

        btnAceptar.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, Session.class);
            i.putExtra("idSession",forSession);
            if(validarCampos())
                startActivity(i);
        });

        predefinedExerciseButton.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this,SelectPredefinedExercises.class);
            startActivity(i);
        });

        Button btnCancel = (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent(AddExercise.this, Session.class);
                i.putExtra("idSession",extras.getString("idSession"));
                if(validarCampos())
                    startActivity(i);
            }
        });
    }


    private void requestExerciseAndFillForm(ExerciseDBAPI ExerciseDBAPIClient) {
        //Create the call to the api
        Call<ExerciseData> call = ExerciseDBAPIClient.getListExercises(ApiUtils.API_KEY, ApiUtils.HOST);

        // Wait asynchronously for it to end to fill the recycler
        call.enqueue(new Callback<ExerciseData>() {
            @Override
            public void onResponse(Call<ExerciseData> call, Response<ExerciseData> response) {
                switch (response.code()) {
                    case 200:
                        //Get the mapped data as list (THE JSON IS A LIST [] WITH NO NAME)
                        ExerciseData data = response.body();
                        //Convert the mapped data to domain
                        List<Exercise> exercises = ServerDataMapper.convertExerciseDataToDomain(data);
                        //Use the array to fill the session
                        fillFormAddExercise(exercises);
                        break;
                    default:
                        call.cancel();
                        break;
                }
            }

            @Override
            public void onFailure(Call<ExerciseData> call, Throwable t) {
                Log.e("List - error", t.toString());
            }
        });
    }

    private void fillFormAddExercise(List<Exercise> exercises) {

    }

    private boolean validarCampos(){
        if(R.id.TextEdit_series>0 && R.id.TextEdit_repetitions>0 && R.id.TextEdit_weight>0){
            return true;
        }

        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItem_my_profile) {
            startActivity(new Intent(AddExercise.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_my_routines){
            startActivity(new Intent(AddExercise.this, MyProfile.class));
        }
        if (id==R.id.menuItem_my_current_routine){
            startActivity(new Intent(AddExercise.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}