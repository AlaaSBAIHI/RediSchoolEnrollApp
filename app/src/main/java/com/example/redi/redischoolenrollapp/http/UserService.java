package com.example.redi.redischoolenrollapp.http;

import com.example.redi.redischoolenrollapp.entities.User;
import com.example.redi.redischoolenrollapp.entities.UserType;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ReDI on 2/5/2017.
 */

public interface UserService {
    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })

    @FormUrlEncoded
    @POST("api/user/signUp/")
    Call<User> signUp(@Field("firstName") String firstName, @Field("lastName") String lastName,
                      @Field("userType") UserType userType, @Field("email") String email,
                      @Field("password") String password, @Field("passwordConfirm") String passwordConfirm,
                      @Field("address") String address, @Field("description") String description);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/x-www-form-urlencoded"
    })
    @FormUrlEncoded
    @POST("api/user/login/")
    Call<User> login(@Field("email") String email, @Field("password") String password);


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @GET("api/user/email")
    Call<User> getUserByEmail(@Query("email") String email);


}
