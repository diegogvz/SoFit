package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfile extends AppCompatActivity {
    String[] edades;
    String[] pesos;
    String[]alturas;
    String[]sexos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");
        Button btnConfirm = (Button) findViewById(R.id.btn_editprofile_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile.this, MyProfile.class));
            }
        });
        Button btnCancel = (Button) findViewById(R.id.btn_editprofile_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EditProfile.this, MyProfile.class));
            }
        });
        edades = new String[120];
        for(int i=0;i< edades.length;i++){
            edades[i]=i+"";
        }
        Spinner spEdades=(Spinner) findViewById(R.id.EditText_EditProfile_Edad);
        spEdades.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,edades));

        pesos = new String[300];
        for(int i=0;i< pesos.length;i++){
            pesos[i]=i+" Kg";
        }
        Spinner spPesos=(Spinner) findViewById(R.id.EditText_EditProfile_Weight);
        spPesos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,pesos));

        alturas = new String[150];
        int counter = 0;
        for(int i=100;i<alturas.length;i++){
            alturas[counter]=i+" cm";
            counter++;
        }
        Spinner spAlturas=(Spinner) findViewById(R.id.EditText_EditProfile_Height);
        spAlturas.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,alturas));

        sexos=new String[]{"Maculino","Femenino"};
        Spinner spSexos=(Spinner) findViewById(R.id.EditText_EditProfile_Sexo);
        spSexos.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_dropdown_item,sexos));

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
            startActivity(new Intent(EditProfile.this, RecyclerMyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(EditProfile.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(EditProfile.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}