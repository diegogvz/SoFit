package com.example.sofit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditarPerfil extends AppCompatActivity {
    String[] edades;
    String[] pesos;
    String[]alturas;
    String[]sexos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_perfil);

        edades = new String[120];
        for(int i=0;i< edades.length;i++){
            edades[i]=i+"";
        }
        Spinner spEdades=(Spinner) findViewById(R.id.spEdad);
        spEdades.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,edades));

        pesos = new String[300];
        for(int i=0;i< pesos.length;i++){
            pesos[i]=i+" Kg";
        }
        Spinner spPesos=(Spinner) findViewById(R.id.spPeso);
        spPesos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,pesos));

        alturas = new String[150];
        int counter = 0;
        for(int i=100;i<alturas.length;i++){
            alturas[counter]=i+" cm";
            counter++;
        }
        Spinner spAlturas=(Spinner) findViewById(R.id.spAltura);
        spAlturas.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,alturas));

        sexos=new String[]{"Maculino","Femenino"};
        Spinner spSexos=(Spinner) findViewById(R.id.spSexo);
        spSexos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,sexos));

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
            startActivity(new Intent(EditarPerfil.this,MiPerfilPrincipal.class));
        }
        return super.onOptionsItemSelected(item);
    }
}