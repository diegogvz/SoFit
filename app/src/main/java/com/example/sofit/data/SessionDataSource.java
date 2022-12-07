package com.example.sofit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.Session;

import java.util.ArrayList;
import java.util.List;

public class SessionDataSource extends DataSource{
    private final String[] allColumns =
            {MyDBHelper.COL_SESSIONS_NAME,
                    MyDBHelper.COL_EXERCISES_NAME
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

    public long createSession(Session session){
        ContentValues values =new ContentValues();
        values.put(MyDBHelper.COL_SESSIONS_NAME,session.getName());
        values.put(MyDBHelper.COL_SESSIONS_ROUTINE,session.getRoutine());
        open();
        long insertId = database.insert(MyDBHelper.TABLE_SESSIONS,null,values);
        close();
        return insertId;
    }

    public List<Session> getAllSessions(){
        List<Session> sessionsList = new ArrayList<>();
        open();
        Cursor cursor = database.query(MyDBHelper.TABLE_SESSIONS, allColumns,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Session session = new Session();

            session.setName(cursor.getString(0));
            session.setRoutine(cursor.getString(1));

            sessionsList.add(session);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return sessionsList;
    }
    public List<Session> getSessionsForRoutine(String routineId){
        List<Session> sessionsList = new ArrayList<>();
        String whereClause = "ROUTINE_ID = ?";
        String[] whereArgs = new String[] {
                routineId
        };
        Cursor cursor = database.query(MyDBHelper.TABLE_SESSIONS, allColumns,
                whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Session session = new Session();

            session.setName(cursor.getString(0));
            session.setRoutine(cursor.getString(1));

            sessionsList.add(session);
            cursor.moveToNext();
        }
        cursor.close();
        return sessionsList;
    }
}
