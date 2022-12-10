package com.example.sofit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.Routine;

import java.util.ArrayList;

public class RoutineDataSource extends DataSource{

    /**
     * Columnas de la tabla
     */
    private final String[] allColumns = {MyDBHelper.COL_ROUTINE_NAME, MyDBHelper.COL_EXERCISES_NAME,
    MyDBHelper.COL_ROUTINE_IMG};

    /**
     * Constructor.
     *
     * @param context
     */
    public RoutineDataSource(Context context) {
        //el último parámetro es la versión
        dbHelper = new MyDBHelper(context, null, null, 1);
    }

    /**
     * Recibe la película y crea el registro en la base de datos.
     *
     * @param routineToInsert
     * @return
     */
    public long createRoutine(Routine routineToInsert) {
        // Establecemos los valores que se insertaran
        ContentValues values = new ContentValues();

        values.put(MyDBHelper.COL_ROUTINE_NAME, routineToInsert.getNombre_rutina());
        values.put(MyDBHelper.COL_EXERCISES_NAME, routineToInsert.getNombre_ejercicio());
        values.put(MyDBHelper.COL_ROUTINE_IMG, routineToInsert.getImage());


        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLE_ROUTINES, null, values);


        return insertId;
    }

    public void deleteRoutine(Routine routineToDelete) {

        // Insertamos la valoracion
        database.execSQL("DELETE FROM " + MyDBHelper.TABLE_ROUTINES + " WHERE name = '" + routineToDelete.getNombre_rutina()+"'");
    }

    /**
     * Obtiene todas las valoraciones andadidas por los usuarios.
     *
     * @return Lista de objetos de tipo Rutina
     */
    public ArrayList<Routine> getAllValorations() {
        // Lista que almacenara el resultado
        ArrayList<Routine> rutinaList = new ArrayList<Routine>();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLE_ROUTINES, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final Routine rutina = new Routine();
            rutina.setNombre_rutina(cursor.getString(0));
            rutina.setNombre_ejercicio(cursor.getString(1));

            rutinaList.add(rutina);
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return rutinaList;
    }

}
