package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.User;

import java.util.ArrayList;

public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        setTitle("Log In");

        Button btnEntrar = (Button) findViewById(R.id.btnEntrar);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickonItem();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        boolean used = seeIfUsed();
        if (used){
            startActivity(new Intent(LogIn.this,MyRoutines.class));
        }
    }

    private boolean seeIfUsed() {
        UserDataSource usd = new UserDataSource(getApplicationContext());
        usd.open();
        ArrayList<User> au  = usd.getAllValorations();
        return !au.isEmpty();
    }


    private void clickonItem(){
        EditText name = (EditText) findViewById(R.id.editTextNombre);
        EditText sex = (EditText) findViewById(R.id.editTextSex);
        EditText weight = (EditText) findViewById(R.id.editTextWeight);
        EditText height = (EditText) findViewById(R.id.editTextHeight);
        EditText age = (EditText) findViewById(R.id.editTextAge);

        UserDataSource userDataSource = new UserDataSource(getApplicationContext());
        userDataSource.open();

        if(!(name.getText().equals(" ")) && !(sex.getText().equals(" ")) && !(weight.getText().equals(" ")) &&
                !(height.getText().equals(" ")) && !(age.getText().equals(" "))) {
            User user = new User(name.getText().toString(), sex.getText().toString(), Integer.parseInt(weight.getText().toString()),
                    Integer.parseInt(height.getText().toString()), Integer.parseInt(age.getText().toString()));
            userDataSource.createUser(user);
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.complete), Toast.LENGTH_SHORT).show();
        }
        userDataSource.close();
        startActivity(new Intent(LogIn.this, MyRoutines.class));
    }
}