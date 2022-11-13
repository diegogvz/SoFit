package com.example.sofit.model;

public class Routine {
    String nombre_rutina;
    String nombre_ejercicio;

    public Routine(){

    }
    public Routine(String nombre_rutina, String nombre_ejercicio) {
        this.nombre_rutina = nombre_rutina;
        this.nombre_ejercicio = nombre_ejercicio;
    }

    public String getNombre_rutina() {
        return nombre_rutina;
    }

    public void setNombre_rutina(String nombre_rutina) {
        this.nombre_rutina = nombre_rutina;
    }

    public String getNombre_ejercicio() {
        return nombre_ejercicio;
    }

    public void setNombre_ejercicio(String nombre_ejercicio) {
        this.nombre_ejercicio = nombre_ejercicio;
    }
}
