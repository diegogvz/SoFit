package com.example.sofit;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListSessionViewAdapter;
import com.example.sofit.data.SessionDataSource;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MyCurrentRoutine extends BaseActivity {

    List<com.example.sofit.model.Session> sessions = new ArrayList<com.example.sofit.model.Session>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_current_routine);
        setTitle("My current routine");
        createDrawer(this);

    }


    public void cargarSesiones() {
        SessionDataSource sds = new SessionDataSource(getApplicationContext());
        sds.open();
        sessions = sds.getSessionsForRoutine(getIntent().getExtras().getString("routineId"));
        sds.close();
    }



    @Override
    protected void onResume() {
        super.onResume();

        cargarSesiones();

        listDiasView = findViewById(R.id.recycler_sessionExercises);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListSessionViewAdapter lpAdapter = new ListSessionViewAdapter(sessions, new ListSessionViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(com.example.sofit.model.Session item) {
                /* Change current routine to the one clicked */
                Intent i = new Intent(MyCurrentRoutine.this, Session.class);

                i.putExtra("idSession", item.getName());
                System.out.println("\n Name of session "+item.getName());
                startActivity(i);
            }
        },new ListSessionViewAdapter.DeleteListener() {
            @Override
            public void deleteItem(com.example.sofit.model.Session item) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MyCurrentRoutine.this);
                builder.setMessage("Do you want to delete this routine?").setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteSession(item);
                        startActivity(new Intent(MyCurrentRoutine.this,MyCurrentRoutine.class));
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();
            }
        });

        listDiasView.setAdapter(lpAdapter);

        FloatingActionButton b = findViewById(R.id.my_current_routine_add_session);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MyCurrentRoutine.this, AddSession.class);
                i.putExtra("routineId",getIntent().getExtras().getString("routineId"));
                startActivity(i);
            }
        });
    }

    private void deleteSession(com.example.sofit.model.Session item) {
        SessionDataSource sds = new SessionDataSource(getApplicationContext());
        sds.open();
        sds.deleteSession(item);
        sds.close();
    }
}