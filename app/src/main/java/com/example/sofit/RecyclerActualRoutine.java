package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListDiasViewAdapter;
import com.example.sofit.model.Day;

import java.util.ArrayList;

public class RecyclerActualRoutine extends AppCompatActivity {

    ArrayList<Day> days =new ArrayList<Day>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_actual_routine);

        days.add(new Day("Lunes"));
        days.add(new Day("Martes"));
        days.add(new Day("Miercoles"));
        days.add(new Day("Jueves"));
        days.add(new Day("Viernes"));
        days.add(new Day("Sabado"));
        days.add(new Day("Domingo"));

        listDiasView=(RecyclerView) findViewById(R.id.recyclerView);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListDiasViewAdapter lpAdapter=new ListDiasViewAdapter(days,
                new ListDiasViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Day item) {
                        clikonItem(item);
                    }
                });

        listDiasView.setAdapter(lpAdapter);
    }

    public void clikonItem (Day day){
        startActivity(new Intent(RecyclerActualRoutine.this, AddSession.class));
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
            startActivity(new Intent(RecyclerActualRoutine.this, RecyclerMyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(RecyclerActualRoutine.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(RecyclerActualRoutine.this, RecyclerActualRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}