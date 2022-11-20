package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListDiasViewAdapter;
import com.example.sofit.data.SessionDataSource;
import com.example.sofit.model.Day;

import java.util.ArrayList;

public class MyCurrentRoutine extends AppCompatActivity {
    ArrayList<Day> days =new ArrayList<Day>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_current_routine);
        setTitle("My current routine");

        days.add(new Day("Monday"));
        days.add(new Day("Tuesday"));
        days.add(new Day("Wednesday"));
        days.add(new Day("Thursday"));
        days.add(new Day("Leg"));
        days.add(new Day("Arms"));
        days.add(new Day("Chest"));

        listDiasView=(RecyclerView) findViewById(R.id.recyclerView);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListDiasViewAdapter lpAdapter=new ListDiasViewAdapter(days,
                new ListDiasViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Day item) {
                        /* Change current routine to the one clicked */
                        Intent i=new Intent(MyCurrentRoutine.this, SessionActivity.class);
                        System.out.println(item.getNombre());
                        i.putExtra("idSession","Strength");
                        startActivity(i);
                    }
                });

        listDiasView.setAdapter(lpAdapter);

        Button b = (Button)findViewById(R.id.my_current_routine_add_session);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCurrentRoutine.this, AddSession.class));
            }
        });
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
            startActivity(new Intent(MyCurrentRoutine.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(MyCurrentRoutine.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(MyCurrentRoutine.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}