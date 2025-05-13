package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity8 extends AppCompatActivity {

    DatabaseHelper db;
    String name,year;
    private ArrayAdapter listAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);

        int[] months = new int[12];
        int[] monthss = new int[12];
        String[] monthsss = new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String[] numbers = new String[] {"1-", "2-", "3-", "4-", "5-", "6-", "7-", "8-", "9-", "10-", "11-", "12-"};

        ArrayList<String> history= new ArrayList<>();

        ListView listView = findViewById(R.id.listview);
        db = new DatabaseHelper(this);

        Intent receivedIntent = getIntent();

        name = receivedIntent.getStringExtra("name");
        year = receivedIntent.getStringExtra("year");

        getSupportActionBar().setTitle(name + " - " + year);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data = db.getDates();




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
            boolean x=false;

             if(year.equals(arrOfStr[0])) {
                 switch (arrOfStr[1]) {
                     case "01":
                         if (data.getString(1).equals(name))
                             months[0] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[0] += 1;
                         }
                         break;
                     case "02":
                         if (data.getString(1).equals(name))
                             months[1] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[1] += 1;
                         }
                         break;
                     case "03":
                         if (data.getString(1).equals(name))
                             months[2] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[2] += 1;
                         }
                         break;
                     case "04":
                         if (data.getString(1).equals(name))
                             months[3] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[3] += 1;
                         }
                         break;
                     case "05":
                         if (data.getString(1).equals(name))
                             months[4] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[4] += 1;
                         }
                         break;
                     case "06":
                         if (data.getString(1).equals(name))
                             months[5] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                            x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[5] += 1;
                         }
                         break;
                     case "07":
                         if (data.getString(1).equals(name))
                             months[6] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[6] += 1;
                         }
                         break;
                     case "08":
                         if (data.getString(1).equals(name))
                             months[7] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[7] += 1;
                         }
                         break;
                     case "09":
                         if (data.getString(1).equals(name))
                             months[8] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[8] += 1;
                         }
                         break;
                     case "10":
                         if (data.getString(1).equals(name))
                             months[9] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[9] += 1;
                         }
                         break;
                     case "11":
                         if (data.getString(1).equals(name))
                             months[10] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[10] += 1;
                         }
                         break;
                     case "12":
                         if (data.getString(1).equals(name))
                             months[11] += 1;
                         for(int i=0; i<history.size()-1; i++)
                         {
                             if(history.get(i).equals(arrOfStr[1]) && history.get(i+1).equals(arrOfStr[2]))
                             {
                                 x = true;
                             }
                         }
                         if(x==true)
                         {
                             x = false;
                         }
                         else
                         {
                             history.add(arrOfStr[1]);
                             history.add(arrOfStr[2]);
                             monthss[11] += 1;
                         }
                         break;
                     default:
                         // code block

                 }
             }
        }

        for (int i=0; i<12; i++)
        {
            theList.add(numbers[i] + " " + monthsss[i] + "  " + months[i] + " Times out of " + monthss[i] + " Times");
        }

        listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, theList);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String date = parent.getItemAtPosition(position).toString();
                Intent editScreenIntent = new Intent(MainActivity8.this, MainActivity12.class);
                editScreenIntent.putExtra("name",name);
                editScreenIntent.putExtra("date",date);
                editScreenIntent.putExtra("year",year);
                startActivity(editScreenIntent);
                //finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}