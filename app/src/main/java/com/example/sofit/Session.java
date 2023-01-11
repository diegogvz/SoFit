package com.example.sofit;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.ExerciseDataSource;
import com.example.sofit.model.ModelExercise;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

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


    }

    @Override
    protected void onResume() {
        super.onResume();
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
        exercises = exerciseDataSource
                .getExercisesForSession(session);
        exerciseDataSource.close();

        //----Init the recycler----
        exerciseRecycler = (RecyclerView) findViewById(R.id.recycler_sessionExercises);

        //Set the layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        exerciseRecycler.setLayoutManager(layoutManager);

        //Set has fixed size, increases performance for
        //Recyclers that get items added or removed frequently
        exerciseRecycler.setHasFixedSize(true);

        fillRecycler(exercises);

        //--------------------------

        //Get data from API and fill the recycler
        //requestAllExercises(ApiUtils.createThemoviedbApi());


        //Create the event for the button to add new exercise
        createEventAddExercise();
    }

    private void createEventAddExercise(){
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



    private void fillRecycler(List<ModelExercise> exercises) {


        List<ModelExercise> exercisesButtons = new ArrayList<>();
        for (ModelExercise ex : exercises) {
            exercisesButtons.add(ex);
        }
        ListaEjerciciosViewAdapter lpAdapter = new ListaEjerciciosViewAdapter(exercisesButtons,
                new ListaEjerciciosViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(ModelExercise item) {
                        Intent i = new Intent(Session.this, Exercise.class);
                        i.putExtra("exerciseId", item.getName());
                        i.putExtra("exercisePhoto", item.getImage());
                        startActivity(i);
                    }
                },new ListaEjerciciosViewAdapter.DeleteListener() {
            @Override
            public void deleteItem(ModelExercise item) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Session.this);
                builder.setMessage("Do you want to delete this routine?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteExercise(item);
                        Intent i = new Intent(Session.this, Session.class);
                        i.putExtra("idSession", session);
                        startActivity(i);
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        exerciseRecycler.setAdapter(lpAdapter);


    }

    private void deleteExercise(ModelExercise item) {
        ExerciseDataSource eds = new ExerciseDataSource(getApplicationContext());
        eds.open();
        eds.deleteExercise(item);
        eds.close();
    }

//    public void requestAllExercises(ExerciseDBAPI ExerciseDBAPIClient) {
//        //Create the call to the api
//        Call<List<ExerciseData>> call = ExerciseDBAPIClient.getListExercises(ApiUtils.API_KEY, ApiUtils.HOST);
//
//        // Wait asynchronously for it to end to fill the recycler
//        call.enqueue(new Callback<List<ExerciseData>>() {
//            @Override
//            public void onResponse(Call<List<ExerciseData>> call, Response<List<ExerciseData>> response) {
//                switch (response.code()) {
//                    case 200:
//                        //Get the mapped data as list (THE JSON IS A LIST [] WITH NO NAME)
//                        List<ExerciseData> data = response.body();
//                        //Convert the mapped data to domain
//                        List<ExerciseData> exercises = ServerDataMapper.convertExerciseDataToDomain(data);
//                        //Use the array to fill the session
//                        fillRecycler(exercises);
//                        break;
//                    default:
//                        call.cancel();
//                        break;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<ExerciseData>> call, Throwable t) {
//                Log.e("List - error", t.toString());
//            }
//        });
//    }

}