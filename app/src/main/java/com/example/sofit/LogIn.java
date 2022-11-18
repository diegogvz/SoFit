package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.User;

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
            User user = new User(name.getText().toString(), sex.getText().toString(), Integer.parseInt(String.valueOf(weight.getText())),
                    Integer.parseInt(String.valueOf(height.getText())), Integer.parseInt(String.valueOf(age.getText())));
            userDataSource.createUser(user);
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.complete), Toast.LENGTH_SHORT).show();
        }
        startActivity(new Intent(LogIn.this, MyRoutines.class));
    }
}