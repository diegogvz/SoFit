package com.example.sofit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.sofit.model.Routine;

import java.util.ArrayList;

public class RoutineDataSource extends DataSource{

    /**
     * Columnas de la tabla
     */
    private final String[] allColumns = {MyDBHelper.COLUMNA_NOMBRE_RUTINA, MyDBHelper.COLUMNA_NOMBRE_EJERCICIOS};

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

        values.put(MyDBHelper.COLUMNA_NOMBRE_RUTINA, routineToInsert.getNombre_rutina());
        values.put(MyDBHelper.COLUMNA_NOMBRE_EJERCICIOS, routineToInsert.getNombre_ejercicio());


        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLA_RUTINAS, null, values);

        return insertId;
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

        Cursor cursor = database.query(MyDBHelper.TABLA_RUTINAS, allColumns,
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
