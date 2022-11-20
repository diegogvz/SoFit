package com.example.sofit.data;

import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDataSource extends DataSource{
    private final String[] allColumns = {
                    MyDBHelper.COL_EXERCISES_NAME,
                    MyDBHelper.COL_EXERCISES_IMG,
                    MyDBHelper.COL_EXERCISES_SESSION
            };
    /**
     * Constructor.
     *
     * @param context
     */
    public ExerciseDataSource(Context context) {
        //el último parámetro es la versión
        dbHelper = new MyDBHelper(context, null, null, 1);
    }

    public List<Exercise> getExercisesForSession(String sessionId){
        ArrayList<Exercise> exercises = new ArrayList<>();
        String whereClause = "SESSION_ID = ?";
        String[] whereArgs = new String[] {
                sessionId
        };
        Cursor cursor = database.query(MyDBHelper.TABLE_EXERCISES, allColumns,
                whereClause, whereArgs, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Exercise exercise = new Exercise(cursor.getString(0));
            exercises.add(exercise);
            cursor.moveToNext();
        }
        cursor.close();

        return exercises;
    }
    public List<Exercise> getAllExercises(){
        ArrayList<Exercise> exercises = new ArrayList<>();
        Cursor cursor = database.query(MyDBHelper.TABLE_EXERCISES, null,
                null, null, null, null, null);

        System.out.println(cursor.getCount());
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exercise exercise = new Exercise(cursor.getString(0));
            exercises.add(exercise);
            cursor.moveToNext();
        }
        cursor.close();
        return exercises;
    }

}
