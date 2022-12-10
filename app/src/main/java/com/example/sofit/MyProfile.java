package com.example.sofit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.User;

public class MyProfile extends BaseActivity {
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        setTitle("My Profile");
        createDrawer(this);



        //Get the views
        Button btnCambiarDatos = (Button)findViewById(R.id.button_actualizar_datos);
        Button btnVerProgreso = (Button)findViewById(R.id.button_verProgreso);
        TextView textViewSexo=(TextView) findViewById(R.id.profile_textView_sex);
        TextView textViewHeight=(TextView) findViewById(R.id.profile_textView_height);
        TextView textViewWeight=(TextView) findViewById(R.id.profile_textView_weight);

        //Set the listeners
        btnCambiarDatos.setOnClickListener(
                view -> startActivity(new Intent(MyProfile.this, EditProfile.class))
        );
        btnVerProgreso.setOnClickListener(
                view -> startActivity(new Intent(MyProfile.this, MyProgress.class))
        );

        //Get the user data from database
        getUserData();

        //Set the data of the user
        textViewSexo.setText(user.getSex());
        textViewHeight.setText(user.getHeight()+ " cm");
        textViewWeight.setText(user.getWeight()+" kg");
    }
    private void getUserData(){
        UserDataSource userDataSource=new UserDataSource(getApplicationContext());
        userDataSource.open();
        user=userDataSource.getUserData();
        userDataSource.close();
    }
}