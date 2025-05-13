package com.example.stmark;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter {

    public ListAdapter (Context context, ArrayList<User> userArrayList)
    {
        super(context,R.layout.list_item,userArrayList);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        User user = (User) getItem(position);

        if (convertView == null){

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);

        }

        TextView one = convertView.findViewById(R.id.one);
        //TextView two = convertView.findViewById(R.id.two);
        TextView three = convertView.findViewById(R.id.three);


        one.setText(user.name);
        //two.setText(user.present);
        three.setText(user.date);


        return convertView;
    }
}
