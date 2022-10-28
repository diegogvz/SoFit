package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListDiasViewAdapter;
import com.example.sofit.model.Dia;

import java.util.ArrayList;

public class RecyclerRutinaActual extends AppCompatActivity {

    ArrayList<Dia> dias=new ArrayList<Dia>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_rutina_actual);

        dias.add(new Dia("Lunes"));
        dias.add(new Dia("Martes"));
        dias.add(new Dia("Miercoles"));
        dias.add(new Dia("Jueves"));
        dias.add(new Dia("Viernes"));
        dias.add(new Dia("Sabado"));
        dias.add(new Dia("Domingo"));

        listDiasView=(RecyclerView) findViewById(R.id.recyclerView);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListDiasViewAdapter lpAdapter=new ListDiasViewAdapter(dias,
                new ListDiasViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Dia item) {
                        clikonItem(item);
                    }
                });

        listDiasView.setAdapter(lpAdapter);
    }

    public void clikonItem (Dia dia){
        startActivity(new Intent(RecyclerRutinaActual.this,AnadirSesion.class));
    }



    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
// Inflate the menu
        getMenuInflater().inflate(R.menu.menu_editperfil, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_editPerfil_inicio) {
            startActivity(new Intent(RecyclerRutinaActual.this,RecyclerMisRutinas.class));
        }
        if(item.getItemId()==R.id.menuItem_editPerfil_perfil){
            startActivity(new Intent(RecyclerRutinaActual.this,MiPerfilPrincipal.class));
        }
        return super.onOptionsItemSelected(item);

    }
}