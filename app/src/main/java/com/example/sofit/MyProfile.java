package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MyProfile extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        setTitle("My Profile");
        createDrawer(this);
        Button b = (Button)findViewById(R.id.button_actualizar_datos);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile.this, EditProfile.class));
            }
        });

        Button b2 = (Button)findViewById(R.id.button_verProgreso);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyProfile.this, MyProgress.class));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
// Inflate the menu
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//noinspection SimplifiableIfStatement
        if (id == R.id.menuItem_my_profile) {
            startActivity(new Intent(MyProfile.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_my_routines){
            startActivity(new Intent(MyProfile.this, MyProfile.class));
        }
        if (id==R.id.menuItem_my_current_routine){
            startActivity(new Intent(MyProfile.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}