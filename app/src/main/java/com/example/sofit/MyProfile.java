package com.example.sofit;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.sofit.data.UserDataSource;
import com.example.sofit.model.User;

public class MyProfile extends BaseActivity {

    private static final int PICK_IMAGE = 1;
    private ImageView mImage;
    Uri mImageUri;
    private User user;
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

        mImage = findViewById(R.id.imageView);
        //Get the user data from database
        getUserData();

        //Set the data of the user
        textViewSexo.setText(user.getSex());
        textViewHeight.setText(user.getHeight()+ " cm");
        textViewWeight.setText(user.getWeight()+" kg");
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String mImageUri = preferences.getString("image", null);

        if (mImageUri != null) {
            mImage.setImageURI(Uri.parse(mImageUri));
        } else {
            mImage.setImageResource(R.drawable.sofit);
        }

        Button btnImg = (Button) findViewById(R.id.button_fotoPerfil);
        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSelect();
            }
        });
    }
    private void getUserData(){
        UserDataSource userDataSource=new UserDataSource(getApplicationContext());
        userDataSource.open();
        user=userDataSource.getUserData();
        userDataSource.close();
    }
    public void imageSelect() {
        permissionsCheck();
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
        } else {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
        }
        intent.setType("image/*");
        startActivityForResult(Intent.createChooser(intent, "Elige una foto"), PICK_IMAGE);
    }

    public void permissionsCheck() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            return;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    mImageUri=data.getData();
                    SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("image", String.valueOf(mImageUri));
                    editor.commit();
                    mImage.setImageURI(mImageUri);
                    mImage.invalidate();
                }
            }
        }
    }
}