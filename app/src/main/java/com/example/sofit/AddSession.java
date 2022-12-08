package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.SessionDataSource;
import com.example.sofit.model.Exercise;
import com.example.sofit.model.Session;

import java.util.ArrayList;

public class AddSession extends BaseActivity {

    private ArrayList<Exercise> ejercicios = new ArrayList<>();
    private RecyclerView listEjerciciosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
        createDrawer(this);

    }

    private void addingSession(Session s){
        SessionDataSource sds = new SessionDataSource(getApplicationContext());
        sds.open();
        sds.createSession(s);
        sds.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Button btnConfirm = (Button) findViewById(R.id.btn_addsession_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText et = (EditText)findViewById(R.id.editTextTituloSesion) ;
                if(!et.getText().toString().isEmpty()){
                    Session s = new Session();
                    s.setName(et.getText().toString());
                    s.setRoutine("");
                    addingSession(s);

                }
                startActivity(new Intent(AddSession.this, MyCurrentRoutine.class));
            }
        });

        Button btnAddExecise = (Button) findViewById(R.id.btn_addsession_addExecise);
        btnAddExecise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AddSession.this, AddExercise.class);
                startActivity(i);
            }
        });

        setTitle("Add new session");

//        ejercicios.add("exercise1 - hardcoded");
//        ejercicios.add("exercise2 - hardcoded");
//        ejercicios.add("exercise3 - hardcoded");

        listEjerciciosView =(RecyclerView) findViewById(R.id.recyclerView_anadirsesion);
        listEjerciciosView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listEjerciciosView.setLayoutManager(layoutManager);

        ListaEjerciciosViewAdapter lpAdapter=new ListaEjerciciosViewAdapter(ejercicios,
                new ListaEjerciciosViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Exercise ejercicio) {
                        //clikonItem(ejercicio);
                    }
               },new ListaEjerciciosViewAdapter.DeleteListener(){
            @Override
            public void deleteItem(Exercise ejercicio) {
                //clikonItem(ejercicio);
            }
        });

        listEjerciciosView.setAdapter(lpAdapter);
    }

}
