package com.example.sofit.model;

public class Session {
    private String name;
    private String routine;

    public Session(){

    }

    public String getRoutine() {
        return routine;
    }

    public void setRoutine(String routine) {
        this.routine = routine;
    }

    public Session(String name, String routine){
        this.name=name;
        this.routine=routine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
