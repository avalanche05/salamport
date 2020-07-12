package com.example.salamport.services;

import com.example.salamport.models.Message;
import com.example.salamport.models.User;

import java.util.ArrayList;

import okhttp3.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MessageService {
    @POST("message/all")
    Call<ArrayList<User>> allMessage(@Query("idUser") long idUser);

    @POST("message/send")
    Call<Response> sendMessage(@Body Message message);

    @POST("message/get")
    Call<ArrayList<Message>> getMessage(@Query("from") long from, @Query("to") long to);
}
