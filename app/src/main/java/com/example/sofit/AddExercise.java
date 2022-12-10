package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.sofit.model.ModelExercise;
import com.squareup.picasso.Picasso;

public class AddExercise extends BaseActivity {
    private ModelExercise predefinedExercise;
    private String session;
    private EditText editTextExerciseTitle;
    private ImageView imageViewExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exercise);
        createDrawer(this);
        setTitle("Add Exercise");


        //Sacar los extras
        Bundle extras = getIntent().getExtras();
        predefinedExercise =(ModelExercise) extras.getParcelable("predefinedExercise");
        session = extras.getString("sessionId");


        //Sacar los views
        imageViewExercise = (ImageView) findViewById(R.id.imageViewExercise);
        editTextExerciseTitle = (EditText) findViewById(R.id.editTextExerciseTitle);
        Button btnAceptar = (Button) findViewById(R.id.buttonAceptarEjercicio);
        Button predefinedExerciseButton = (Button) findViewById(R.id.button_select_predefined_exercise);
        Button btnCancel = (Button) findViewById(R.id.buttonCancel);


        //Comprobar si proviene de select predefied exercises
        //Si es que si, rellenamos automaticamente el formulario.
        if (predefinedExercise != null) {
            fillFormAddExercise();
        }

        //Crear los listeners de los buttons
        btnAceptar.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, Session.class);
            i.putExtra("idSession", session);
            if (validarCampos()) startActivity(i);
        });

        predefinedExerciseButton.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, SelectPredefinedExercises.class);
            startActivity(i);
        });

        btnCancel.setOnClickListener(view -> {
            Intent i = new Intent(AddExercise.this, Session.class);
            i.putExtra("idSession", extras.getString("idSession"));
            if (validarCampos()) startActivity(i);
        });
    }


    private void fillFormAddExercise() {
        //Load gif
        Picasso.get().load(predefinedExercise.getImage()).into(imageViewExercise);
        //Set title

        System.out.println("Ex obj name: "+predefinedExercise.getName());
        editTextExerciseTitle.setText(predefinedExercise.getName());
        System.out.println("EditText"+editTextExerciseTitle.getText());
    }

    private boolean validarCampos() {
        return R.id.addData_EditText_weight > 0 && R.id.addData_EditText_fat > 0 && R.id.addData_EditText_muscle > 0;
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menuItem_my_profile) {
            startActivity(new Intent(AddExercise.this, MyRoutines.class));
        }
        if (id == R.id.menuItem_my_routines) {
            startActivity(new Intent(AddExercise.this, MyProfile.class));
        }
        if (id == R.id.menuItem_my_current_routine) {
            startActivity(new Intent(AddExercise.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}