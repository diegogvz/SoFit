package com.example.sofit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ExerciseDataSource extends DataSource{

    private final String[] allColumns =
            {MyDBHelper.COLUMNA_NOMBRE_EJERCICIOS,
                    MyDBHelper.COLUMNA_NOMBRE_EJERCICIOS,
                    MyDBHelper.COLUMNA_SERIES_EJERCICIOS,
                    MyDBHelper.COLUMNA_REPETICIONES_EJERCICIOS,
                    MyDBHelper.COLUMNA_PESO_EJERCICIOS,
                    MyDBHelper.COLUMNA_IMAGEN_EJERCICIOS
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

}
