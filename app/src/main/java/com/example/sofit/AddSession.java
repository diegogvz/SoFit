package com.example.sofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListaEjerciciosViewAdapter;
import com.example.sofit.data.SessionDataSource;
import com.example.sofit.model.Session;

import java.util.ArrayList;

public class AddSession extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    Uri imageUri;

    private ArrayList<String> ejercicios = new ArrayList<>();
    private RecyclerView listEjerciciosView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_session);
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

        ImageButton btnPhoto = (ImageButton) findViewById(R.id.imageButton2);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openGallery();
            }
        });

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
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestedCode, int resultCode, Intent data) {
        super.onActivityResult(requestedCode, resultCode, data);

        if (resultCode == RESULT_OK && requestedCode == PICK_IMAGE) {
            imageUri = data.getData();
            ImageView photo = (ImageView) findViewById(R.id.imageView5);
            photo.setImageURI(imageUri);
        }
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
            startActivity(new Intent(AddSession.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_misRutinas_perfil){
            startActivity(new Intent(AddSession.this, MyProfile.class));
        }
        if (id==R.id.menuItem_misRutinas_rutinas){
            startActivity(new Intent(AddSession.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}
