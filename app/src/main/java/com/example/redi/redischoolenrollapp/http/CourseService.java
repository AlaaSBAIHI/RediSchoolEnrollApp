package com.example.redi.redischoolenrollapp.http;

import com.example.redi.redischoolenrollapp.entities.Course;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ReDI on 2/17/2017.
 */

public interface CourseService {


    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })

    @FormUrlEncoded
    @POST("course/add/")
    Call<Course> addCourse(@Field("name") String name,
                           @Field("description") String description,
                           @Field("url") String url);

}
