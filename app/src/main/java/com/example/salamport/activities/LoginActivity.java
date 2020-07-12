package com.example.salamport.activities;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salamport.R;
import com.example.salamport.services.HttpSendService;

public class LoginActivity extends AppCompatActivity {

    private EditText emailField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailField = findViewById(R.id.emailField);
    }

    public void sendEmail (View view){
        HttpSendService.loginUser(emailField.getText().toString(),LoginActivity.this);
    }
}
