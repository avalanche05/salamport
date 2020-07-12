package com.example.salamport.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salamport.R;
import com.example.salamport.adapters.MessageAdapter;
import com.example.salamport.models.Message;
import com.example.salamport.services.HttpSendService;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    public static ArrayList<Message> messages = new ArrayList<>();
    private EditText messageField;
    private ListView messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        messageList = findViewById(R.id.messagesList);
        messageField = findViewById(R.id.messgaeField);

        MessageAdapter messageAdapter = new MessageAdapter(MessageActivity.this, messages);
        messageList.setAdapter(messageAdapter);

    }

    public void sendMessage(View view) {
        HttpSendService.sendMessage(new Message(messageField.getText().toString(), getIntent().getLongExtra("from", 0), getIntent().getLongExtra("to", 0)));
        HttpSendService.getMessage(getIntent().getLongExtra("from", 0), getIntent().getLongExtra("to", 0));
        messageField.setText("");
    }
}
