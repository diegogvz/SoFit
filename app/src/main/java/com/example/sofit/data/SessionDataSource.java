package com.example.sofit.data;

import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class SessionDataSource extends DataSource{
    private final String[] allColumns =
            {MyDBHelper.COL_EXERCISES_NAME,
                    MyDBHelper.COL_EXERCISES_NAME,
                    MyDBHelper.COL_EXERCISES_IMG
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
    public List<Exercise> getSessionsForRoutine(String routineId){
        ArrayList<Exercise> exercises = new ArrayList<>();
        String whereClause = "ROUTINE_ID = ?";
        String[] whereArgs = new String[] {
                routineId
        };
        Cursor cursor = database.query(MyDBHelper.TABLE_SESSIONS, allColumns,
                whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Exercise exercise = new Exercise();
            exercise.setName(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return exercises;
    }

}
