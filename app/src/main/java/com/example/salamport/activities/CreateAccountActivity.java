package com.example.salamport.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.salamport.R;
import com.example.salamport.models.User;
import com.example.salamport.services.HttpSendService;

public class CreateAccountActivity extends AppCompatActivity {

    private EditText firstNameField;
    private EditText lastNameField;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        firstNameField = findViewById(R.id.firstNameField);
        lastNameField = findViewById(R.id.lastNameField);
    }

    public void createAccount(View view){
        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String email = getIntent().getStringExtra("email");
        HttpSendService.createAccount(new User(firstName,lastName,email),CreateAccountActivity.this);
    }
}
