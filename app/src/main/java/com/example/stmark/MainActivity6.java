package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity6 extends AppCompatActivity {

    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        getSupportActionBar().setTitle("Scans History");


        ListView listView = (ListView) findViewById(R.id.listview);
        db = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getDates();
        if(data.getCount() == 0)
        {
            Toast.makeText(this, "There are no dates yet!",Toast.LENGTH_SHORT).show();
        }
        else{
            while(data.moveToNext()){
                if (!theList.contains(data.getString(2)))
                {
                    theList.add(data.getString(2));
                }
            }
            Collections.reverse(theList);
            ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
            listView.setAdapter(listAdapter);
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String date = adapterView.getItemAtPosition(i).toString();
                Intent editScreenIntent = new Intent(MainActivity6.this, MainActivity7.class);
                editScreenIntent.putExtra("date",date);
                startActivity(editScreenIntent);
                finish();
            }
        });


    }
}