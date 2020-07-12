package com.example.salamport.services;


import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;


public interface ImageService {
    @Multipart
    @POST("/upload")
    Call<ResponseBody> postFile(@Part MultipartBody.Part file, @Part("description") RequestBody description);
}
