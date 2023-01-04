package com.example.sofit.model;

public class Routine {
    String nombreRutina;
    String image;
    String user;

    public Routine(){

    }
    public Routine(String nombre_rutina,String user, String image) {
        this.nombreRutina = nombre_rutina;
        this.user=user;
        this.image = image;
    }

    public void setImage(String image){ this.image = image; }

    public String getImage(){return this.image;}

    public String getNombreRutina() {
        return nombreRutina;
    }

    public void setNombreRutina(String nombreRutina) {
        this.nombreRutina = nombreRutina;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
