package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MiPerfilPrincipal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil_principal);



        Button b = (Button)findViewById(R.id.button_actualizar_datos);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MiPerfilPrincipal.this,EditarPerfil.class));
            }
        });

        Button b2 = (Button)findViewById(R.id.button_verProgreso);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MiPerfilPrincipal.this,MainActivity.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
// Inflate the menu
        getMenuInflater().inflate(R.menu.menu_perfilprincipal, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_perfilPrincipal_Inicio) {
            startActivity(new Intent(MiPerfilPrincipal.this,RecyclerMisRutinas.class));
        }
        return super.onOptionsItemSelected(item);
    }
}