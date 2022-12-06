package com.example.sofit.model;

public class Routine {
    String nombre_rutina;
    String nombre_ejercicio;
    String image;

    public Routine(){

    }
    public Routine(String nombre_rutina, String nombre_ejercicio,String image) {
        this.nombre_rutina = nombre_rutina;
        this.nombre_ejercicio = nombre_ejercicio;
        this.image = image;
    }

    public void setImage(String image){ this.image = image; }

    public String getImage(){return this.image;}

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
