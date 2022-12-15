package com.example.sofit;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.data.SeriesDataSource;

public class Exercise extends BaseActivity {
    SeriesDataSource seriesDataSource;
    private RecyclerView seriesRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        seriesDataSource = new SeriesDataSource(getApplicationContext());
        Bundle extras = getIntent().getExtras();



        String exercise = "";
        if (extras != null) {
            exercise = extras.getString("exerciseId");
        }
        setTitle(exercise+" Exercise");
        setContentView(R.layout.activity_exercise);

        createDrawer(this);
        seriesRecycler = findViewById(R.id.seriesRecycler);

//        seriesDataSource.open();
//        List<Serie> series = seriesDataSource.getSeriesForExercise(exercise);
//        seriesDataSource.close();
//
//        ListaSerieViewAdapter listaSerieViewAdapter = new ListaSerieViewAdapter(series);
//        seriesRecycler.setAdapter(listaSerieViewAdapter);
    }

}
