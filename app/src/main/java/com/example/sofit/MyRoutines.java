package com.example.sofit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

        chargeRoutines();

        listRutinasView = (RecyclerView) findViewById(R.id.recylcerViewRutinas);
        listRutinasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listRutinasView.setLayoutManager(layoutManager);

        ListRutinasViewAdapter lpAdapter = new ListRutinasViewAdapter(rutinas, new ListRutinasViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Routine rutina) {
                clickonItem(rutina);
            }
        },new ListRutinasViewAdapter.DeleteListener() {
            @Override
            public void deleteRoutine(Routine rutina) {
                deleteItem(rutina);
            }
        } );
        listRutinasView.setAdapter(lpAdapter);

        FloatingActionButton btnCrear = (FloatingActionButton) findViewById(R.id.btnCrearRutina);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyRoutines.this, CreateRoutine.class));
            }
        });
    }

    private void chargeRoutines(){
        RoutineDataSource routineDataSource = new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        rutinas = routineDataSource.getAllRoutines();
        routineDataSource.close();
    }

    public void clickonItem(Routine rutina){
        Intent i = new Intent(MyRoutines.this, MyCurrentRoutine.class);
        i.putExtra("routine", rutina.getName());
        startActivity(i);
    }
    public void deleteItem(Routine rutina){
        AlertDialog.Builder builder = new AlertDialog.Builder(MyRoutines.this);
        builder.setMessage("Do you want to delete this routine?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteRoutineFromDataBase(rutina);
                startActivity(new Intent(MyRoutines.this,MyRoutines.class));
            }
        }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    private void deleteRoutineFromDataBase(Routine rutina) {

        RoutineDataSource routineDataSource = new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        routineDataSource.deleteRoutine(rutina);
        routineDataSource.close();
    }

}