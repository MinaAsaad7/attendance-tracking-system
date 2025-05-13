package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity9 extends AppCompatActivity {

    DatabaseHelper db;
    String name,date,year;
    private ArrayAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9);

        ListView listView = findViewById(R.id.listview);
        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        name = receivedIntent.getStringExtra("name");
        date = receivedIntent.getStringExtra("date");



        String[] d = date.split("-",2);
        String dd = "";

        if(d[0].toString().length() < 2) {
            dd += "0" + d[0].toString();
        }
        else
            dd=d[0];


        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getDates();

        if(data.getCount() == 0)
        {
            Toast.makeText(this, "There are no dates !",Toast.LENGTH_SHORT).show();
        }
        else {
            while (data.moveToNext()) {

                String input = data.getString(2);
                SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-d");
                Date date = null;
                try {
                    date = parser.parse(input);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String formattedDate = formatter.format(date);

                String[] arrOfStr = formattedDate.split("-", 5);

                if (data.getString(1).equals(name) && arrOfStr[1].equals(dd)) {
                    theList.add(data.getString(2));
                }
                year=arrOfStr[0];
            }
            Collections.reverse(theList);
            listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
            listView.setAdapter(listAdapter);

        }

        getSupportActionBar().setTitle(name + "  " + d[0]+"-"+year);

    }
}