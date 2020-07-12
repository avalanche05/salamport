package com.example.salamport.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.salamport.R;
import com.example.salamport.services.HttpSendService;


public class SendCodeActivity extends AppCompatActivity {

    private EditText codeField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_code);

        codeField = findViewById(R.id.codeField);
        codeField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(codeField.getText().toString().length() == 6){
                    HttpSendService.sendCode(codeField.getText().toString(),SendCodeActivity.this);
                }
            }
        });
    }
}
