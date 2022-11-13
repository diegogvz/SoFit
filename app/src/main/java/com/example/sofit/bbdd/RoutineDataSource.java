package com.example.sofit.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.sofit.model.Routine;

import java.util.ArrayList;

public class RoutineDataSource {
    /**
     * Referencia para manejar la base de datos. Este objeto lo obtenemos a partir de MyDBHelper
     * y nos proporciona metodos para hacer operaciones
     * CRUD (create, read, update and delete)
     */
    private SQLiteDatabase database;
    /**
     * Referencia al helper que se encarga de crear y actualizar la base de datos.
     */
    private MyDBHelper dbHelper;
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
     * Abre una conexion para escritura con la base de datos.
     * Esto lo hace a traves del helper con la llamada a getWritableDatabase. Si la base de
     * datos no esta creada, el helper se encargara de llamar a onCreate
     *
     * @throws SQLException
     */
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();

    }

    /**
     * Cierra la conexion con la base de datos
     */
    public void close() {
        dbHelper.close();
    }

    /**
     * Recibe la película y crea el registro en la base de datos.
     * @param routineToInsert
     * @return
     */
    public long createrepartoPelicula(Routine routineToInsert) {
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
