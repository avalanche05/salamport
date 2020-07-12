package com.example.salamport.services;

import com.example.salamport.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {
    @POST("user/login")
    Call<Boolean> loginUser(@Query("email") String email);

    @POST("user/code")
    Call<User> sendCode(@Query("code") String code);

    @POST("user/create")
    Call<User> createAccount(@Body User user);

    @POST("user/all")
    Call<ArrayList<User>> allUser();

}
