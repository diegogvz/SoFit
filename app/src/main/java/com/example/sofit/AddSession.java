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

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;

import java.util.ArrayList;

public class AddSession extends AppCompatActivity {

    private ArrayList<String> ejercicios = new ArrayList<>();
    private RecyclerView listEjerciciosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);

        Button btnConfirm = (Button) findViewById(R.id.btn_addsession_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSession.this, MyCurrentRoutine.class));
            }
        });

        Button btnAddExecise = (Button) findViewById(R.id.btn_addsession_addExecise);
        btnAddExecise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddSession.this, AddExercise.class));
            }
        });

        setTitle("Add new session");

        ejercicios.add("exercise1");
        ejercicios.add("exercise2");
        ejercicios.add("exercise3");

        listEjerciciosView =(RecyclerView) findViewById(R.id.recyclerView_anadirsesion);
        listEjerciciosView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listEjerciciosView.setLayoutManager(layoutManager);

        ListaEjerciciosViewAdapter lpAdapter=new ListaEjerciciosViewAdapter(ejercicios,
                new ListaEjerciciosViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(String ejercicio) {
                        //clikonItem(ejercicio);
                    }


                });

        listEjerciciosView.setAdapter(lpAdapter);
    }

    private void clikonItem(String item) {

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
            startActivity(new Intent(AddSession.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(AddSession.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(AddSession.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}
