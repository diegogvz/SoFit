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
    private final String[] allColumns = {MyDBHelper.COL_USER_NAME, MyDBHelper.COL_USER_HEIGHT,
    MyDBHelper.COL_USER_WEIGHT, MyDBHelper.COL_USER_AGE, MyDBHelper.COL_USER_SEX, MyDBHelper.COL_USER_IMG};

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
        values.put(MyDBHelper.COL_USER_HEIGHT, userToInsert.getHeight());
        values.put(MyDBHelper.COL_USER_WEIGHT, userToInsert.getWeight());
        values.put(MyDBHelper.COL_USER_AGE, userToInsert.getAge());
        values.put(MyDBHelper.COL_USER_SEX, userToInsert.getSex());
        values.put(MyDBHelper.COL_USER_IMG, userToInsert.getImage());


        // Insertamos la valoracion
        long insertId =
                database.insert(MyDBHelper.TABLE_USER, null, values);

        return insertId;
    }

    /**
     * Obtiene todas las valoraciones andadidas por los usuarios.
     *
     * @return Lista de objetos de tipo User
     */
    public ArrayList<User> getAllUsers() {
        // Lista que almacenara el resultado
        ArrayList<User> userList = new ArrayList<User>();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLE_USER, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            final User user = new User();
            user.setName(cursor.getString(0));
            user.setHeight(cursor.getInt(2));
            user.setWeight(cursor.getInt(3));
            user.setAge(cursor.getInt(1));
            user.setSex(cursor.getString(4));
            user.setImage(cursor.getString(5));

            userList.add(user);
            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return userList;
    }

    public User getUserData(){
        User user = new User();
        //hacemos una query porque queremos devolver un cursor

        Cursor cursor = database.query(MyDBHelper.TABLE_USER, allColumns,
                null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user.setName(cursor.getString(0));
            user.setHeight(cursor.getInt(2));
            user.setWeight(cursor.getInt(3));
            user.setAge(cursor.getInt(1));
            user.setSex(cursor.getString(4));
            user.setImage(cursor.getString(5));

            cursor.moveToNext();
        }

        cursor.close();
        // Una vez obtenidos todos los datos y cerrado el cursor, devolvemos la
        // lista.

        return user;
    }

    public void updateData(String name, int Height, int Weight, int Age, String sex){
        ContentValues values = new ContentValues();

        values.put(MyDBHelper.COL_USER_HEIGHT, Age);
        values.put(MyDBHelper.COL_USER_WEIGHT, Height);
        values.put(MyDBHelper.COL_USER_AGE, Weight);
        values.put(MyDBHelper.COL_USER_SEX, sex);

        database.update(MyDBHelper.TABLE_USER, values, "NAME = ?", new String[]{name});
    }
}
