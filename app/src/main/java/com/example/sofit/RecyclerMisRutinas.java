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

import com.example.sofit.adapters.ListRutinasViewAdapter;
import com.example.sofit.model.Rutina;

import java.util.ArrayList;

public class RecyclerMisRutinas extends AppCompatActivity {

    ArrayList<Rutina> rutinas = new ArrayList<Rutina>();
    private RecyclerView listRutinasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_mis_rutinas);

        setTitle("Mis Rutinas");

        rutinas.add(new Rutina("Fuerza"));
        rutinas.add(new Rutina("Resistencia"));

        listRutinasView = (RecyclerView) findViewById(R.id.recylcerViewRutinas);
        listRutinasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listRutinasView.setLayoutManager(layoutManager);

        ListRutinasViewAdapter lpAdapter = new ListRutinasViewAdapter(rutinas,
                new ListRutinasViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Rutina rutina) {
                        clickonItem(rutina);
                    }
                });
        listRutinasView.setAdapter(lpAdapter);

        Button btnCrear = (Button) findViewById(R.id.btnCrearRutia);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecyclerMisRutinas.this,CrearRutinas.class));
            }
        });
    }

    public void clickonItem(Rutina rutina){
        
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu){
        getMenuInflater().inflate(R.menu.menu_misrutinas, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(RecyclerMisRutinas.this, RecyclerMisRutinas.class));
        }
        if(id == R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(RecyclerMisRutinas.this, MiPerfilPrincipal.class));
        }
        return super.onOptionsItemSelected(item);
    }
}