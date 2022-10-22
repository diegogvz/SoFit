package com.example.sofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;

import java.util.ArrayList;

public class AnadirSesion extends AppCompatActivity {

    private ArrayList<String> ejercicios = new ArrayList<>();
    private RecyclerView listEjerciciosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anadir_sesion);

        ejercicios.add("ejercicio1");
        ejercicios.add("ejercicio2");
        ejercicios.add("...");

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
        getMenuInflater().inflate(R.menu.menu_editperfil, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_editPerfil_inicio) {
            return true;
        }
        if(item.getItemId()==R.id.menuItem_editPerfil_perfil){
            startActivity(new Intent(AnadirSesion.this,MiPerfilPrincipal.class));
        }
        return super.onOptionsItemSelected(item);

    }
}
