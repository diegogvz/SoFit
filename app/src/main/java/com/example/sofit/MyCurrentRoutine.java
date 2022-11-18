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

import com.example.sofit.adapters.ListSessionViewAdapter;
import com.example.sofit.data.SessionDataSource;
import com.example.sofit.model.Session;

import java.util.ArrayList;
import java.util.List;

public class MyCurrentRoutine extends AppCompatActivity {

    List<Session> sessions =new ArrayList<Session>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_current_routine);
        setTitle("My current routine");

//        sessions.add(new Session("Monday"));
//        sessions.add(new Session("Tuesday"));
//        sessions.add(new Session("Wednesday"));
//        sessions.add(new Session("Thursday"));
//        sessions.add(new Session("Leg"));
//        sessions.add(new Session("Arms"));
//        sessions.add(new Session("Chest"));

    }


    public void cargarSesiones(){
        SessionDataSource sessionDataSource = new SessionDataSource(getApplicationContext());
        sessionDataSource.open();
        sessionDataSource.createSession(new Session("Una rutina"));
        sessionDataSource.createSession(new Session("Otra rutina"));
        sessionDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();

        cargarSesiones();

        SessionDataSource sds = new SessionDataSource(getApplicationContext());
        sessions = sds.getAllSessions();
        sds.close();

        listDiasView=(RecyclerView) findViewById(R.id.recyclerView);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListSessionViewAdapter lpAdapter=new ListSessionViewAdapter(sessions,
                new ListSessionViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Session item) {
                        /* Change current routine to the one clicked */
                        startActivity(new Intent(MyCurrentRoutine.this,ExercisesOfADay.class));
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