package com.example.sofit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.Exercise;
import com.example.sofit.model.Routine;

import java.util.ArrayList;
import java.util.List;

public class ExerciseDataSource extends DataSource{
    private final String[] allColumns = {
                    MyDBHelper.COL_EXERCISES_NAME,
                    MyDBHelper.COL_EXERCISES_IMG,
                    MyDBHelper.COL_EXERCISES_SESSION,
            };
    /**
     * Constructor.
     *
     * @param context
     */
    public ExerciseDataSource(Context context) {
        //el último parámetro es la versión
        dbHelper = new MyDBHelper(context, null, null, 3);
    }

    /**
     * Recibe la película y crea el registro en la base de datos.
     *
     * @param exerciseToInsert
     * @return
     */
    public long createExercise(Exercise exerciseToInsert) {
        // Establecemos los valores que se insertaran
        ContentValues values = new ContentValues();

        values.put(MyDBHelper.COL_EXERCISES_NAME, exerciseToInsert.getName());
        values.put(MyDBHelper.COL_EXERCISES_IMG, exerciseToInsert.getImage());


        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLE_ROUTINES, null, values);

        return insertId;
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
            final Exercise exercise = new Exercise(cursor.getString(0), cursor.getString(1));
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
            Exercise exercise = new Exercise(cursor.getString(0), cursor.getString(1));
            exercises.add(exercise);
            cursor.moveToNext();
        }
        cursor.close();
        return exercises;
    }
    public void deleteSession(ModelExercise exerciseToDelete) {

        // Insertamos la valoracion
        database.execSQL("DELETE FROM " + MyDBHelper.TABLE_EXERCISES + " WHERE name = '" + exerciseToDelete.getName()+"'");
    }
}
