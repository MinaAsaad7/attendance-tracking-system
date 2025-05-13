package com.example.stmark;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.util.Locale;


public class MainActivity3 extends AppCompatActivity {

    DatabaseHelper db;
    Button b1,b2;
    EditText t1,t2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        getSupportActionBar().hide();


        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);

        db = new DatabaseHelper(this);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(t1.length() != 0 && t2.length() !=0)
                {
                    String e1 = t1.getText().toString();
                    String e2 = t2.getText().toString();
                    e1 = e1.toLowerCase();
                    e1 = capitalizeWord(e1);
                    AddData(e1,e2);
                    finish();
                    Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(MainActivity3.this, "Complete Text Fields !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity3.this,MainActivity2.class);
                startActivity(intent);
                finish();
            }
        });



    }

    public void AddData(String e1,String e2)
    {
        boolean insertData = db.addData(e1,e2);
        if(insertData==true){
            Toast.makeText(this, e1 + " Successfully Inserted!", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Something went wrong :( ", Toast.LENGTH_SHORT).show();
        }

    }

    private int genrateid()
    {
        int min = 100000000;
        int max = 999999998;
        int r = (int) Math.floor(Math.random()*(max-min+1)+min);
        return r;
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