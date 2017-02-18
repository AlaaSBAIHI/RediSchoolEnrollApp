package com.example.redi.redischoolenrollapp.http;

import com.example.redi.redischoolenrollapp.entities.Course;
import com.example.redi.redischoolenrollapp.entities.User;

import java.util.List;
import java.util.UUID;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.Path;

/**
 * Created by ReDI on 2/14/2017.
 */

public interface UserSessionService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @PATCH("user/apply/{id}")
    Call<List<Course>> apply(@Path("id") long id, @Body List<UUID> coursesIds);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("user/{id}")
    Call<User> getUserById(@Path("id") long id);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("user/")
    Call<List<User>> getUsers();

}
