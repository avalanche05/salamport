package com.example.salamport.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.salamport.R;
import com.example.salamport.models.User;

import java.util.ArrayList;


// адаптер для отображения массива заказов на ListView
public class ChatAdapter extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<User> users;

    public ChatAdapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
        this.layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int position) {
        return users.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = layoutInflater.inflate(R.layout.chat, parent, false);
        final User user = getUser(position);

        ((ImageView) view.findViewById(R.id.messageAvatar)).setImageDrawable(context.getDrawable(R.drawable.default_image));
        ((TextView) view.findViewById(R.id.firstName)).setText(user.getFirstName());
        ((TextView) view.findViewById(R.id.lastName)).setText(user.getLastName());

        return view;
    }

    public User getUser(int position) {
        return users.get(position);
    }
}
