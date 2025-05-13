package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class MainActivity12 extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
   // EditText search;

    String name,date,year;

   // private String selecteddate;
   // private LayoutInflater ActivityMainBinding;
    ListAdapter listAdapter;
    ArrayList<User> userArrayList = new ArrayList<>();
    //ArrayList<User> userArrayList2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);


       // getSupportActionBar().setTitle(selecteddate);

        listView = findViewById(R.id.listview);

        //search = findViewById(R.id.search);

        /*
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                userArrayList2.clear();
                for (int i=0; i < userArrayList.size(); i++)
                {
                    if(userArrayList.get(i).name.toLowerCase().contains(s.toString().toLowerCase()))
                    {
                        userArrayList2.add(userArrayList.get(i));
                    }
                }
                listAdapter = new ListAdapter(MainActivity12.this,userArrayList2);
                listView.setAdapter(listAdapter);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        */

        Intent receivedIntent = getIntent();

        name = receivedIntent.getStringExtra("name");
        date = receivedIntent.getStringExtra("date");
        year = receivedIntent.getStringExtra("year");


        ArrayList<String> theList = new ArrayList<>();

        ListView listView = findViewById(R.id.listview);
        db = new DatabaseHelper(this);
        Cursor data = db.getDates();


        String[] d = date.split("-",2);
        String dd = "";

        if(d[0].toString().length() < 2) {
            dd += "0" + d[0].toString();
        }
        else
            dd=d[0];

        while(data.moveToNext()){

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

            if (data.getString(1).equals(name) && arrOfStr[1].equals(dd) && arrOfStr[0].equals(year)) {
                User user = new User(data.getString(2),data.getString(0),"");
                userArrayList.add(user);
            }
           // year=arrOfStr[0];

            listAdapter = new ListAdapter(MainActivity12.this,userArrayList);
            listView.setAdapter(listAdapter);
        }

        getSupportActionBar().setTitle(name + "  " + d[0]+"-"+year);

    }
}