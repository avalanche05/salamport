package com.example.salamport.services;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

import com.example.salamport.R;
import com.example.salamport.activities.AccountActivity;
import com.example.salamport.activities.ChatActivity;
import com.example.salamport.activities.CreateAccountActivity;
import com.example.salamport.activities.MessageActivity;
import com.example.salamport.activities.SendCodeActivity;
import com.example.salamport.activities.UsersListActivity;
import com.example.salamport.models.Message;
import com.example.salamport.models.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpSendService {

//    public static void sendImage(MultipartBody.Part image, long idUser){
//        Call<Boolean> booleanCallback = imageService.updateProfile(image);
//        booleanCallback.enqueue(new Callback<Boolean>() {
//            @Override
//            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<Boolean> call, Throwable t) {
//
//            }
//        });
//    }

    public static void loginUser(String email, final Activity activity) {
        Call<Boolean> responseCall = userService.loginUser(email);
        responseCall.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.body()) {
                    Intent i = new Intent(activity, SendCodeActivity.class);
                    activity.startActivity(i);
                } else {
                    Toast.makeText(activity, R.string.send_message_error, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static void sendCode(String code, final Activity activity) {
        Call<User> booleanCall = userService.sendCode(code);
        booleanCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() == null) {
                    Toast.makeText(activity, "Неверный код", Toast.LENGTH_SHORT).show();
                } else {
                    if (response.body().getFirstName() == null) {
                        Intent i = new Intent(activity, CreateAccountActivity.class);
                        i.putExtra("email", response.body().getEmail());
                        activity.startActivity(i);
                    } else {
                        Intent i = new Intent(activity, AccountActivity.class);
                        i.putExtra("user", response.body());
                        activity.startActivity(i);
                    }
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public static void createAccount(User user, final Activity activity) {
        Call<User> userCall = userService.createAccount(user);
        userCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent i = new Intent(activity, AccountActivity.class);
                User user = response.body();
                i.putExtra("user", user);
                activity.startActivity(i);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public static void getChats(long idUser, final Activity activity){
        Call<ArrayList<User>> arrayListCall = messageService.allMessage(idUser);
        arrayListCall.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                ChatActivity.users.clear();
                if (response.body()!= null){
                    ChatActivity.users.addAll(response.body());
                }

                activity.startActivity(new Intent(activity,ChatActivity.class));
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }

    public static void allUsers(final Activity activity){
        Call<ArrayList<User>> arrayListCall = userService.allUser();
        arrayListCall.enqueue(new Callback<ArrayList<User>>() {
            @Override
            public void onResponse(Call<ArrayList<User>> call, Response<ArrayList<User>> response) {
                UsersListActivity.users.clear();
                UsersListActivity.users.addAll(response.body());
                activity.startActivity(new Intent(activity,UsersListActivity.class));
            }

            @Override
            public void onFailure(Call<ArrayList<User>> call, Throwable t) {

            }
        });
    }

    public static void sendMessage(Message message){
        Call<okhttp3.Response> responseCall = messageService.sendMessage(message);
        responseCall.enqueue(new Callback<okhttp3.Response>() {
            @Override
            public void onResponse(Call<okhttp3.Response> call, Response<okhttp3.Response> response) {

            }

            @Override
            public void onFailure(Call<okhttp3.Response> call, Throwable t) {

            }
        });
    }

    public static void getMessage(long from, long to){
        Call<ArrayList<Message>> arrayListCall = messageService.getMessage(from, to);
        arrayListCall.enqueue(new Callback<ArrayList<Message>>() {
            @Override
            public void onResponse(Call<ArrayList<Message>> call, Response<ArrayList<Message>> response) {
                MessageActivity.messages.clear();
                MessageActivity.messages.addAll(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Message>> call, Throwable t) {

            }
        });
    }

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://salamport.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private static ImageService imageService = retrofit.create(ImageService.class);
    private static UserService userService = retrofit.create(UserService.class);
    private static MessageService messageService = retrofit.create(MessageService.class);
}
