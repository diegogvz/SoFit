package com.example.sofit.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * MyDHelper
 */
public class MyDBHelper extends SQLiteOpenHelper {

    /**
     * Nombre y version de la base de datos
     */
    private static final String DATABASE_NAME = "sofit.db";
    private static final int DATABASE_VERSION = 1;

    /**
     * Nombre de la tabla usuario y sus columnas
     * nombre_usuario;correo_usuario;contraseña_usuario;altura_usuario;
     * peso_usuario;edad_usuario;sexo_usuario;nombre_rutina(FOREIGN_KEY)
     */
    public static final String TABLA_USUARIO = "tabla_usuario";

    public static final String COLUMNA_NOMBRE_USUARIO = "nombre_usuario";
    public static final String COLUMNA_ALTURA_USUARIO = "altura_usuario";
    public static final String COLUMNA_PESO_USUARIO = "peso_usuario";
    public static final String COLUMNA_EDAD_USUARIO = "edad_usuario";
    public static final String COLUMNA_SEXO_USUARIO = "sexo_usuario";


    /**
     * Nombre de la tabla rutinas y sus columnas
     * nombre_rutina;nombre_sesion(FOREIGN KEY)
     */
    public static final String TABLA_RUTINAS = "tabla_rutinas";

    public static final String COLUMNA_NOMBRE_RUTINA = "nombre_rutina";

    /**
     * Nombre de la tabla sesiones y sus columnas
     * nombre_sesion;nombre_ejercicio(FOREIGN_KEY)
     */
    public static final String TABLA_SESIONES = "tabla_sesiones";

    public static final String COLUMNA_NOMBRE_SESION = "nombre_sesion";

    /**
     * Nombre de la tabla ejercicios y sus columnas
     * nombre_ejercicios;series_ejercicios;repeticiones_ejercicios;peso_ejercicios;imagen_ejercicios
     */
    public static final String TABLA_EJERCICIOS = "tabla_ejercicios";

    public static final String COLUMNA_NOMBRE_EJERCICIOS = "nombre_ejercicios";
    public static final String COLUMNA_SERIES_EJERCICIOS = "series_ejercicios";
    public static final String COLUMNA_REPETICIONES_EJERCICIOS = "repeticiones_ejercicios";
    public static final String COLUMNA_PESO_EJERCICIOS = "peso_ejercicios";
    public static final String COLUMNA_IMAGEN_EJERCICIOS = "imagen_ejercicios";

    /**
     * Nombre de la tabla progreso y sus columnas
     * peso_progreso;grasa_progreso;masa_progreso;agua_progreso
     */
    public static final String TABLA_PROGRESO = "tabla_progreso";

    public static final String COLUMNA_PESO_PROGRESO = "peso_progreso";
    public static final String COLUMNA_GRASA_PROGRSO = "grasa_progreso";
    public static final String COLUMNA_MASA_PROGRESO = "masa_progreso";
    public static final String COLUMNA_AGUA_PROGRESO = "agua_progreso";


    /**
     * Script para crear la base datos en SQL
     */
    private static final String CREATE_TABLA_USUARIO = "create table if not exists " + TABLA_USUARIO
            + "( " +
            COLUMNA_NOMBRE_USUARIO + " " + "text not null, " +
            COLUMNA_ALTURA_USUARIO + " integer not null, " +
            COLUMNA_PESO_USUARIO + " integer not null, " +
            COLUMNA_EDAD_USUARIO + " integer not null, " +
            COLUMNA_SEXO_USUARIO + " text not null, " +
            COLUMNA_NOMBRE_RUTINA + " text not null" +
            ");";

    private static final String CREATE_TABLA_RUTINAS = " create table if not exists " + TABLA_RUTINAS
            + "( " +
            COLUMNA_NOMBRE_RUTINA + " " + "text primary key not null, " +
            COLUMNA_NOMBRE_SESION + "text not null" +
            ");";


    private static final String CREATE_TABLA_SESIONES = "create table if not exists " + TABLA_SESIONES
            + "( " +
            COLUMNA_NOMBRE_SESION + " " + "text primary key not null, " +
            COLUMNA_NOMBRE_EJERCICIOS + " text not null" +
            ");";

    private static final String CREATE_TABLA_EJERCICIOS = " create table if not exists " + TABLA_EJERCICIOS
            + "( " +
            COLUMNA_NOMBRE_EJERCICIOS + " " + "text primary key not null, " +
            COLUMNA_SERIES_EJERCICIOS + " integer not null, " +
            COLUMNA_REPETICIONES_EJERCICIOS + " integer not null, " +
            COLUMNA_PESO_EJERCICIOS + " integer not null, " +
            COLUMNA_IMAGEN_EJERCICIOS + " integer not null" +
            ");";

    private static final String CREATE_TABLA_PROGRESO = " create table if not exists " + TABLA_PROGRESO
            + "( " +
            COLUMNA_NOMBRE_EJERCICIOS + " " + "text primary key not null, " +
            COLUMNA_SERIES_EJERCICIOS + " integer not null, " +
            COLUMNA_REPETICIONES_EJERCICIOS + " integer not null, " +
            COLUMNA_PESO_EJERCICIOS + " integer not null" +
            ");";

    /**
     * Script para borrar la base de datos (SQL)
     */
    private static final String DATABASE_DROP_USUARIO = "DROP TABLE IF EXISTS " + TABLA_USUARIO;
    private static final String DATABASE_DROP_RUTINAS = "DROP TABLE IF EXISTS " + TABLA_RUTINAS;
    private static final String DATABASE_DROP_SESIONES = "DROP TABLE IF EXISTS " + TABLA_SESIONES;
    private static final String DATABASE_DROP_EJERCICIOS = "DROP TABLE IF EXISTS " + TABLA_EJERCICIOS;
    private static final String DATABASE_DROP_PROGRSO = "DROP TABLE IF EXISTS " + TABLA_PROGRESO;




    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //invocamos execSQL pq no devuelve ningún tipo de dataset
        db.execSQL(CREATE_TABLA_USUARIO);
        db.execSQL(CREATE_TABLA_RUTINAS);
        db.execSQL(CREATE_TABLA_SESIONES);
        db.execSQL(CREATE_TABLA_EJERCICIOS);
        db.execSQL(CREATE_TABLA_PROGRESO);

        Log.i("ONCREATE", "EJECUTO CREACION");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP_USUARIO);
        db.execSQL(DATABASE_DROP_RUTINAS);
        db.execSQL(DATABASE_DROP_SESIONES);
        db.execSQL(DATABASE_DROP_EJERCICIOS);
        db.execSQL(DATABASE_DROP_PROGRSO);
        this.onCreate(db);

    }
}
