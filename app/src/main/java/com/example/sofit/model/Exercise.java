package com.example.sofit.model;

import java.io.Serializable;

public class Exercise implements Serializable {
    private int reps;
    private String name;

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
