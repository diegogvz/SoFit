package com.example.sofit.remote;

import retrofit2.Retrofit;

public class ApiUtils {
    public static String BASE_URL="https://exercisedb.p.rapidapi.com/";
    public static String API_KEY = "1f4acb0541msh6b1318bd54e47cep1d3c29jsncfa976a645b5";
    public static String HOST = "exercisedb.p.rapidapi.com";

    public static ExerciseDBAPI createExerciseDBAPI() {
        Retrofit retrofit= RetrofitClient.getClient(ApiUtils.BASE_URL);

        return retrofit.create(ExerciseDBAPI.class);
    }


}
