package com.example.sofit.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.sofit.model.User;

import java.util.ArrayList;

public class UserDataSource extends DataSource{
    /**
     * Columnas de la tabla
     */
    private final String[] allColumns = {MyDBHelper.COL_USER_NAME, MyDBHelper.COL_USER_AGE,
    MyDBHelper.COL_USER_HEIGHT, MyDBHelper.COL_PROGRESS_WEIGHT, MyDBHelper.COL_USER_AGE};

    /**
     * Constructor.
     *
     * @param context
     */
    public UserDataSource(Context context) {
        //el último parámetro es la versión
        dbHelper = new MyDBHelper(context, null, null, 1);
    }

    /**
     * Recibe el usuario y crea el registro en la base de datos.
     *
     * @param userToInsert
     * @return
     */
    public long createUser(User userToInsert) {
        // Establecemos los valores que se insertaran
        ContentValues values = new ContentValues();

        values.put(MyDBHelper.COL_USER_NAME, userToInsert.getName());
        values.put(MyDBHelper.COL_USER_AGE, userToInsert.getAge());
        values.put(MyDBHelper.COL_USER_HEIGHT, userToInsert.getHeight());
        values.put(MyDBHelper.COL_USER_WEIGHT, userToInsert.getWeight());
        values.put(MyDBHelper.COL_USER_SEX, userToInsert.getSex());


        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLE_USER, null, values);

        return insertId;
    }

    /**
     * Obtener el usuario de la app
     *
     * @return Objecto usuario con los datos
     */
    public User getUserData() {
        Cursor cursor = database.query(MyDBHelper.TABLE_USER, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        final User user = new User();
        user.setName(cursor.getString(0));
        user.setSex(cursor.getString(1));
        user.setAge(cursor.getInt(2));
        user.setWeight(cursor.getInt(3));
        user.setHeight(cursor.getInt(4));
        cursor.close();

        return user;
    }
}
