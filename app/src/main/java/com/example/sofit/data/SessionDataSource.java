package com.example.sofit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionDataSource extends DataSource{
    private final String[] allColumns =
            {MyDBHelper.COLUMNA_NOMBRE_SESION,
                    MyDBHelper.COLUMNA_NOMBRE_EJERCICIOS
            };
    /**
     * Constructor.
     *
     * @param context
     */
    public SessionDataSource(Context context) {
        //el último parámetro es la versión
        dbHelper = new MyDBHelper(context, null, null, 1);
    }

    public long createSession(Session sesion){
        ContentValues values =new ContentValues();
        values.put(MyDBHelper.COLUMNA_NOMBRE_SESION,sesion.getName());
        open();
        long insertId = database.insert(MyDBHelper.TABLA_SESIONES,null,values);
        close();
        return insertId;
    }

    public List<Session> getAllSessions(){
        List<Session> sessionsList = new ArrayList<>();
        open();
        Cursor cursor = database.query(MyDBHelper.TABLA_SESIONES, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Session session = new Session();

            session.setName(cursor.getString(0));

            sessionsList.add(session);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return sessionsList;
    }
}
