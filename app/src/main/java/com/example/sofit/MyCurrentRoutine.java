package com.example.sofit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListSessionViewAdapter;
import com.example.sofit.data.SessionDataSource;
import com.example.sofit.model.Session;

import java.util.ArrayList;
import java.util.List;

public class MyCurrentRoutine extends BaseActivity {

    List<Session> sessions =new ArrayList<Session>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_current_routine);
        setTitle("My current routine");
        createDrawer(this);

    }


    public void cargarSesiones(){
        SessionDataSource sds = new SessionDataSource(getApplicationContext());
        sessions = sds.getAllSessions();
        if(sessions.isEmpty()){
//            crearNotificationChannel();
//            NotificationManager mNotificationManager =
//                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "M_CH_ID");
//            mBuilder.setSmallIcon(R.drawable.ic_launcher_background)
//                    .setContentTitle("NO HAY SESIONES EN ESTA RUTINA")
//                    .setContentText("Añada una sesión a la rutina si así lo desea");
//            mNotificationManager.notify(001,mBuilder.build());
            }
        sds.close();
    }
    private void crearNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "CANAL";
            String description = "DESCRIPCION CANAL";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("M_CH_ID", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();

       cargarSesiones();

        listDiasView=(RecyclerView) findViewById(R.id.recyclerView);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListSessionViewAdapter lpAdapter=new ListSessionViewAdapter(sessions,
                new ListSessionViewAdapter.OnItemClickListener(){
                    @Override
                    public void onItemClick(Session item) {
                        /* Change current routine to the one clicked */
                        startActivity(new Intent(MyCurrentRoutine.this,SessionActivity.class));
                        Intent i=new Intent(MyCurrentRoutine.this, SessionActivity.class);
                        System.out.println(item.getName());
                        i.putExtra("idSession","Strength");
                        startActivity(i);
                    }
                });

        listDiasView.setAdapter(lpAdapter);

        Button b = (Button)findViewById(R.id.my_current_routine_add_session);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCurrentRoutine.this, AddSession.class));
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
            startActivity(new Intent(MyCurrentRoutine.this, MyRoutines.class));
        }
        if (id==R.id.menuItem_my_routines){
            startActivity(new Intent(MyCurrentRoutine.this, MyProfile.class));
        }
        if (id==R.id.menuItem_my_current_routine){
            startActivity(new Intent(MyCurrentRoutine.this, MyCurrentRoutine.class));
        }

        return super.onOptionsItemSelected(item);

    }
}