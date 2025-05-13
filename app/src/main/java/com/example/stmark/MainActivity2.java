package com.example.stmark;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity {

    DatabaseHelper db;
    EditText search;
    private ArrayAdapter listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        getSupportActionBar().setTitle("All Users");

        search = findViewById(R.id.search);




        ListView listView = findViewById(R.id.listview);
        db = new DatabaseHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getData();
        if(data.getCount() == 0)
        {
            Toast.makeText(this, "There are no users yet!",Toast.LENGTH_SHORT).show();
            search.setHint("There are no users to search for !");
        }
        else{
            while(data.moveToNext()){
                theList.add(data.getString(0));
                java.util.Collections.sort(theList);
                listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
            search.setHint("Search a total of " + theList.size() + " users");
        }



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name = adapterView.getItemAtPosition(i).toString();
               // Toast.makeText(MainActivity2.this, name, Toast.LENGTH_SHORT).show();


                Cursor data = db.getData(); //get the id associated with that name

                String mobile = "-1";
                String qrcode = "";


                while(data.moveToNext()){
                    if(data.getString(0).equals(name))
                    {
                        mobile = data.getString(1);
                    }
                }

                if(Integer.parseInt(mobile) > -1){
                    Intent editScreenIntent = new Intent(MainActivity2.this, MainActivity5.class);
                    editScreenIntent.putExtra("mobile",mobile);
                    editScreenIntent.putExtra("name",name);
                    startActivity(editScreenIntent);
                }
                else{
                    Toast.makeText(MainActivity2.this, "\"No ID associated with that name\"", Toast.LENGTH_SHORT).show();
                }
                finish();
                //finishAffinity();
            }
        });


        FloatingActionButton b1;
        b1 = findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,MainActivity3.class);
                startActivity(intent);
                finish();
            }
        });

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                (MainActivity2.this).listAdapter.getFilter().filter(s);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    });
}
}