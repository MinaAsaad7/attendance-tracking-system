package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity13 extends AppCompatActivity {

    DatabaseHelper db;
    Button b1;
    int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);

        getSupportActionBar().setTitle("About");


        b1=findViewById(R.id.button4);

        db = new DatabaseHelper(this);

        // Add data to these arrays for first time memory load only.

        String[] names = new String[] {};
        String[] numbers = new String[] {};

        Cursor data = db.getData();
        if(data.getCount() != 0)
        {
            b1.setEnabled(false);
        }

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(int i=0; i<names.length; i++)
                {
                    String e1 = names[i];
                    e1 = e1.toLowerCase();
                    e1 = capitalizeWord(e1);
                    AddData(e1,numbers[i]);

                }
                Toast.makeText(MainActivity13.this, "Successfully Loaded ! ", Toast.LENGTH_LONG).show();

                b1.setEnabled(false);
            }
        });
    }

    public void AddData(String e1,String e2)
    {
        boolean insertData = db.addData(e1,e2);
        /*
        if(insertData==true){
            Toast.makeText(this, e1 + " Successfully Inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Something went wrong :( ", Toast.LENGTH_SHORT).show();
        }
        */
    }

    public static String capitalizeWord(String str){
        String words[]=str.split("\\s");
        String capitalizeWord="";
        for(String w:words){
            String first=w.substring(0,1);
            String afterfirst=w.substring(1);
            capitalizeWord+=first.toUpperCase()+afterfirst+" ";
        }
        return capitalizeWord.trim();
    }
}