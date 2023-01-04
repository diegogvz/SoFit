package com.example.sofit;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.User;

import java.util.ArrayList;

public class EditProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        setTitle("Edit Profile");

        Button btnConfirm = (Button) findViewById(R.id.btn_editprofile_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserDataSource uds = new UserDataSource(getApplicationContext());
                uds.open();
                ArrayList<User> users = uds.getAllUsers();
                String user = users.get(0).getName();
                EditText height = findViewById(R.id.editTextHeight);
                EditText weight = findViewById(R.id.editTextWeight);
                EditText age = findViewById(R.id.editTextAge);
                EditText sex = findViewById(R.id.editTextSex);
                uds.updateData(user, Integer.parseInt(height.getText().toString()),
                        Integer.parseInt(weight.getText().toString()), Integer.parseInt(age.getText().toString()),
                        sex.getText().toString());
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
    }
}