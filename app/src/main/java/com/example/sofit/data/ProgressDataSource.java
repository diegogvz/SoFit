package com.example.sofit.data;

import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.ModelProgress;

import java.util.ArrayList;
import java.util.List;

public class ProgressDataSource extends DataSource {

    /**
     * Constructor.
     *
     * @param context
     */
    public ProgressDataSource(Context context) {
        //el último parámetro es la versión
        dbHelper = new MyDBHelper(context, null, null, 1);
    }
    /**
     * Columnas de la tabla
     */
    private final String[] allColumns =
            {
                MyDBHelper.COL_PROGRESS_WEIGHT,
                MyDBHelper.COL_PROGRESS_FAT,
                MyDBHelper.COL_PROGRESS_MUSCLE,
                MyDBHelper.COL_PROGRESS_WATER,
                MyDBHelper.COL_PROGRESS_USER
            };

    public List<ModelProgress> getProgressData() {
        // Lista que almacenara el resultado
        ArrayList<ModelProgress> rutinaList = new ArrayList<>();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLE_PROGRESS, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final ModelProgress rutina = new ModelProgress();
            rutina.setWeight(cursor.getFloat(0));
            rutina.setFat(cursor.getFloat(1));
            rutina.setMuscle(cursor.getFloat(2));
            rutina.setWater(cursor.getFloat(3));
            rutinaList.add(rutina);
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return rutinaList;
    }
}
