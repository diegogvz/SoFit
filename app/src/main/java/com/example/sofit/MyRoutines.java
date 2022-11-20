package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListRutinasViewAdapter;
import com.example.sofit.data.RoutineDataSource;
import com.example.sofit.model.Routine;

import java.util.ArrayList;

public class MyRoutines extends BaseActivity {

    ArrayList<Routine> rutinas = new ArrayList<Routine>();
    private RecyclerView listRutinasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_routines);

        setTitle("My Routines");
        createDrawer(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        chargeRoutines();

        listRutinasView = (RecyclerView) findViewById(R.id.recylcerViewRutinas);
        listRutinasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listRutinasView.setLayoutManager(layoutManager);

        ListRutinasViewAdapter lpAdapter = new ListRutinasViewAdapter(rutinas,
                new ListRutinasViewAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Routine rutina) {
                        clickonItem(rutina);
                    }
                });
        listRutinasView.setAdapter(lpAdapter);

        Button btnCrear = (Button) findViewById(R.id.btnCrearRutia);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyRoutines.this, CreateRoutine.class));
            }
        });
    }

    private void chargeRoutines(){
        RoutineDataSource routineDataSource = new RoutineDataSource(getApplicationContext());
        routineDataSource.open();
        rutinas = routineDataSource.getAllValorations();
        routineDataSource.close();
    }

    public void clickonItem(Routine rutina){
        Intent i = new Intent(MyRoutines.this, MyCurrentRoutine.class);
        i.putExtra("routine", rutina.getNombre_rutina());
        startActivity(i);
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
            startActivity(new Intent(MyRoutines.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(MyRoutines.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(MyRoutines.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}