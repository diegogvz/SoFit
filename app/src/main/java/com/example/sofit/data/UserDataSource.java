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
    private final String[] allColumns = {MyDBHelper.COLUMNA_NOMBRE_USUARIO, MyDBHelper.COLUMNA_EDAD_USUARIO,
    MyDBHelper.COLUMNA_ALTURA_USUARIO, MyDBHelper.COLUMNA_PESO_USUARIO, MyDBHelper.COLUMNA_SEXO_USUARIO};

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

        values.put(MyDBHelper.COLUMNA_NOMBRE_USUARIO, userToInsert.getName());
        values.put(MyDBHelper.COLUMNA_EDAD_USUARIO, userToInsert.getAge());
        values.put(MyDBHelper.COLUMNA_ALTURA_USUARIO, userToInsert.getHeight());
        values.put(MyDBHelper.COLUMNA_PESO_USUARIO, userToInsert.getWeight());
        values.put(MyDBHelper.COLUMNA_SEXO_USUARIO, userToInsert.isSex());


        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLA_USUARIO, null, values);

        return insertId;
    }

    /**
     * Obtiene todas las valoraciones andadidas por los usuarios.
     *
     * @return Lista de objetos de tipo User
     */
    public ArrayList<User> getAllValorations() {
        // Lista que almacenara el resultado
        ArrayList<User> userList = new ArrayList<User>();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLA_USUARIO, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final User user = new User();
            user.setName(cursor.getString(0));
            user.setSex(cursor.getString(1));
            user.setAge(cursor.getInt(2));
            user.setWeight(cursor.getInt(3));
            user.setHeight(cursor.getInt(4));

            userList.add(user);
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return userList;
    }
}
