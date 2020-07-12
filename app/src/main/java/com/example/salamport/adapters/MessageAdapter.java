package com.example.salamport.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.salamport.R;
import com.example.salamport.activities.AccountActivity;
import com.example.salamport.models.Message;

import java.util.ArrayList;

public class MessageAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Message> messages;

    public MessageAdapter(Context context, ArrayList<Message> messages) {
        this.context = context;
        this.messages = messages;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = layoutInflater.inflate(R.layout.message, parent, false);
        final Message message = getMessage(position);
        TextView messageTextView = view.findViewById(R.id.messageText);

        // проверяем от кого отпралено сообщение(владельца или пользователя)
        if (message.getTo()== AccountActivity.user.getId()) {
            // Выравниваем сообщение по левому краю
            messageTextView.setGravity(Gravity.LEFT);
        } else {
            // Выравниваем сообщение по правому краю
            messageTextView.setGravity(Gravity.RIGHT);
        }
        messageTextView.setText(message.getText());

        return view;
    }

    public Message getMessage(int position) {
        return messages.get(position);
    }
}
