package com.example.sofit.model;

public class Rutina {
    String nombre;

    public Rutina(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return this.nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }
}
