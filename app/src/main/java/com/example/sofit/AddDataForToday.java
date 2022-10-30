package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AddDataForToday extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_data_for_today);

        setTitle("Add data for today");

        Button b1 = (Button)findViewById(R.id.button_actualizar_datos);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddDataForToday.this, MyProgress.class));
            }
        });

        Button b2 = (Button)findViewById(R.id.button_fotoPerfil);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddDataForToday.this, MyProgress.class));
            }
        });

    }
}
