package com.example.sofit;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListRutinasViewAdapter;
import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.Routine;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MyRoutines extends BaseActivity {

    ArrayList<Routine> rutinas = new ArrayList<Routine>();
    private RecyclerView listRutinasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routines);

        setTitle("My Routines");
        createDrawer(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

        loadRoutines();

        FloatingActionButton btnCrear = (FloatingActionButton) findViewById(R.id.btnCrearRutina);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listRutinasView = (RecyclerView) findViewById(R.id.recylcerViewRutinas);


        listRutinasView.setHasFixedSize(true);
        listRutinasView.setLayoutManager(layoutManager);

        ListRutinasViewAdapter lpAdapter = new ListRutinasViewAdapter(rutinas,
                rutina -> clickOnItem(rutina),
                rutina -> deleteItem(rutina)
        );

        listRutinasView.setAdapter(lpAdapter);

        btnCrear.setOnClickListener(
                view -> startActivity(new Intent(MyRoutines.this, CreateRoutine.class))
        );

        btnCrear.setOnClickListener(
                view -> startActivity(new Intent(MyRoutines.this, CreateRoutine.class))
        );
    }

    private void loadRoutines() {
        RoutineDataSource routineDataSource = new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        rutinas = routineDataSource.getAllValorations();
        routineDataSource.close();
    }

    public void clickOnItem(Routine rutina) {
        Intent i = new Intent(MyRoutines.this, MyCurrentRoutine.class);
        i.putExtra("routine", rutina.getNombre_rutina());
        startActivity(i);
    }

    public void deleteItem(Routine rutina) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyRoutines.this);
        builder.setMessage("Do you want to delete this routine?").setPositiveButton("Yes",
                (dialog, which) -> {
                    deleteRoutineFromDataBase(rutina);
                    startActivity(new Intent(MyRoutines.this, MyRoutines.class));
                }).setNegativeButton("NO",
                (dialog, which) -> {
                    //Nothing
                }).show();
    }

    private void deleteRoutineFromDataBase(Routine rutina) {

        RoutineDataSource routineDataSource = new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        routineDataSource.deleteRoutine(rutina);
        routineDataSource.close();
    }

}