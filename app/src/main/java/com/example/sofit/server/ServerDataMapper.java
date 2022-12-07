package com.example.sofit.server;

import com.example.sofit.model.Exercise;
import com.example.sofit.server.exerciselist.ExerciseData;

import java.util.ArrayList;
import java.util.List;

public class ServerDataMapper {


    /**
     * Converts the output from JSON to our domain
     *
     * @param data
     * @return Exercise - domain
     */
    public static List<Exercise> convertExerciseDataToDomain(List<ExerciseData> data) {
        List<Exercise> exercises=new ArrayList<>();
        for (ExerciseData exerciseData : data) {
            exercises.add(new Exercise(exerciseData.getName(),exerciseData.getGifUrl()));
        }
        return exercises;
    }
}
