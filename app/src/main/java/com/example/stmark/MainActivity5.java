package com.example.stmark;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MainActivity5 extends AppCompatActivity   {


    DatabaseHelper db;

    Button b1,b2;
    EditText t1,t2;
    String z1,z2;
    ImageView iv;

    private String selectedName;
    private String selectedID;
    private String qrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        getSupportActionBar().hide();


        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        iv=findViewById(R.id.imageView);

        db = new DatabaseHelper(this);



        //get the intent extra from the ListDataActivity
        Intent receivedIntent = getIntent();

        //now get the itemID we passed as an extra
        selectedID = receivedIntent.getStringExtra("mobile"); //NOTE: -1 is just the default value

        //now get the name we passed as an extra
        selectedName = receivedIntent.getStringExtra("name");

        //set the text to show the current selected name
        t1.setText(selectedName);
        t2.setText(selectedID);

        try {
            iv.setImageBitmap(encodeAsBitmap(selectedID));
        } catch (WriterException e) {
            e.printStackTrace();
        }

        z1 = selectedName;
        z2 = selectedID;

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editScreenIntent = new Intent(MainActivity5.this, MainActivity11.class);
                editScreenIntent.putExtra("qrcode",selectedID);
                startActivity(editScreenIntent);
            }
        });


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UpdateData(t1.getText().toString(),t2.getText().toString(),z2);

            }
        });



        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                DeleteData(z1,z2);
                                finish();
                                dialog.dismiss();
                                //Intent intent = new Intent(MainActivity5.this,MainActivity2.class);
                                //startActivity(intent);
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Are you sure you want to delete "+ z1.toString() + " ?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });


        Spinner s;
        s= findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.years, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setSelection(0);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(!parent.getItemAtPosition(position).toString().equals("Select a Year"))
                {
                    Intent editScreenIntent = new Intent(MainActivity5.this, MainActivity8.class);
                    editScreenIntent.putExtra("name",z1);
                    editScreenIntent.putExtra("year",parent.getItemAtPosition(position).toString());
                    startActivity(editScreenIntent);
                    s.setSelection(0);
                }
                //Toast.makeText(parent.getContext()," " + parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void UpdateData(String e1,String e2,String e3)
    {
        if(!t1.equals("")  && !t2.equals("") )
        {
            try {
                db.updateName(t1.getText().toString(),t2.getText().toString(),z2,selectedName);
                Toast.makeText(this, "successfully updated ! ", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(MainActivity5.this,MainActivity2.class);
                startActivity(intent);

            }
            catch (Exception e)
            {
                Toast.makeText(this, "Something went wrong :( ", Toast.LENGTH_SHORT).show();
            }
        }
        else
        {
            Toast.makeText(this, "Complete Text Fields !", Toast.LENGTH_SHORT).show();
        }
    }

    public void DeleteData(String e1,String e2)
    {
            try {
                db.deleteName(e1,e2);
                Toast.makeText(this, "successfully Deleted ! ", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(MainActivity5.this,MainActivity2.class);
                startActivity(intent);

            }
            catch (Exception e)
            {
                Toast.makeText(this, "Something went wrong :( ", Toast.LENGTH_SHORT).show();
            }
    }

    Bitmap encodeAsBitmap(String str) throws WriterException {
        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(str, BarcodeFormat.QR_CODE, 400, 400);

        int w = bitMatrix.getWidth();
        int h = bitMatrix.getHeight();
        int[] pixels = new int[w * h];
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                pixels[y * w + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE ;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
        return bitmap;
    }

    @Override
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(MainActivity5.this,MainActivity2.class);
        startActivity(intent);

    }
}