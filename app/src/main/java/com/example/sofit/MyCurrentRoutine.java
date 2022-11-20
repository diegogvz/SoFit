package com.example.sofit;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sofit.adapters.ListSessionViewAdapter;
import com.example.sofit.data.SessionDataSource;

import java.util.ArrayList;
import java.util.List;

public class MyCurrentRoutine extends BaseActivity {

    List<com.example.sofit.model.Session> sessions = new ArrayList<com.example.sofit.model.Session>();
    private RecyclerView listDiasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_current_routine);
        setTitle("My current routine");
        createDrawer(this);

    }


    public void cargarSesiones() {
        SessionDataSource sds = new SessionDataSource(getApplicationContext());
        sds.open();
        sessions = sds.getAllSessions();
        if (sessions.isEmpty()) {
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

        listDiasView = findViewById(R.id.recyclerView);
        listDiasView.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        listDiasView.setLayoutManager(layoutManager);

        ListSessionViewAdapter lpAdapter = new ListSessionViewAdapter(sessions, new ListSessionViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(com.example.sofit.model.Session item) {
                /* Change current routine to the one clicked */
                Intent i = new Intent(MyCurrentRoutine.this, Session.class);

                i.putExtra("idSession", item.getName());
                System.out.println("\n Name of session "+item.getName());
                startActivity(i);
            }
        });

        listDiasView.setAdapter(lpAdapter);

        Button b = findViewById(R.id.my_current_routine_add_session);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCurrentRoutine.this, AddSession.class));
            }
        });
    }
}