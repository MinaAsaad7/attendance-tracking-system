package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.QuickContactBadge;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity7 extends AppCompatActivity {

    DatabaseHelper db;
    ListView listView;
    EditText search;

    private String selecteddate;
    private LayoutInflater ActivityMainBinding;
    ListAdapter listAdapter;
    ArrayList<User> userArrayList = new ArrayList<>();
    ArrayList<User> userArrayList2 = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Intent receivedIntent = getIntent();
        selecteddate = receivedIntent.getStringExtra("date");

        getSupportActionBar().setTitle(selecteddate);

        db = new DatabaseHelper(this);
        listView = findViewById(R.id.listview);

        search = findViewById(R.id.search);


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
                listAdapter = new ListAdapter(MainActivity7.this,userArrayList2);
                listView.setAdapter(listAdapter);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });



        Cursor data = db.getDates();

        while(data.moveToNext()){
            if(data.getString(2).equals(selecteddate))
            {
                User user = new User(data.getString(1),data.getString(0),"");
                userArrayList.add(user);
            }
        }

        listAdapter = new ListAdapter(MainActivity7.this,userArrayList);
        listView.setAdapter(listAdapter);


        FloatingActionButton b1;
        b1 = findViewById(R.id.button1);




        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you sure?");


        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                db.deletedate(selecteddate);
                finish();
                dialog.dismiss();
                Intent intent = new Intent(MainActivity7.this,MainActivity6.class);
                startActivity(intent);
                Toast.makeText(MainActivity7.this, " Successfully Deleted !", Toast.LENGTH_SHORT).show();


            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // Do nothing
                dialog.dismiss();
            }
        });

       b1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               AlertDialog alert = builder.create();
               alert.show();
           }
       });

       search.setHint("Search a total of " + userArrayList.size() + " attendants");
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(MainActivity7.this,MainActivity6.class);
        startActivity(intent);

    }


}