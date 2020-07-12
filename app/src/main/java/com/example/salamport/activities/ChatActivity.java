package com.example.salamport.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.salamport.R;
import com.example.salamport.adapters.ChatAdapter;
import com.example.salamport.models.Message;
import com.example.salamport.models.User;
import com.example.salamport.services.HttpSendService;

import java.util.ArrayList;

public class ChatActivity extends AppCompatActivity {

    private ListView usersListView;
    public static ArrayList<User> users = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        usersListView = findViewById(R.id.messagesList);

        ChatAdapter chatAdapter = new ChatAdapter(ChatActivity.this,ChatActivity.users);
        usersListView.setAdapter(chatAdapter);

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ChatActivity.this,MessageActivity.class);
                i.putExtra("from",AccountActivity.user.getId());
                i.putExtra("to", users.get(position));
                startActivity(i);
            }
        });

    }

}
