package com.example.salamport.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salamport.R;
import com.example.salamport.models.User;
import com.example.salamport.services.HttpSendService;

public class AccountActivity extends AppCompatActivity {

    public static User user = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = (User) getIntent().getSerializableExtra("user");

        setContentView(R.layout.activity_main);
        findViewById(R.id.avatarImageView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountActivity.this, FileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void toChats(View view){
        HttpSendService.getChats(user.getId(),AccountActivity.this);
    }

    public void toUsersList(View view){
        HttpSendService.allUsers(AccountActivity.this);
    }

}
