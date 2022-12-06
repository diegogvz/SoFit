package com.example.sofit.model;

public class Session {
    private String name;
    private String routine;
    private String image;

    public Session(){

    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public Session(String name, String routine, String image){
        this.name=name;
        this.routine=routine;
        this.image = image;
    }

    public void setImage(String image){ this.image = image; }

    public String getImage(){ return this.image; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
