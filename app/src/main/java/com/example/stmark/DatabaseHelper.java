package com.example.stmark;

import static android.content.ContentValues.TAG;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String COL1 = "Name";
    public static final String COL2 = "Mobile";

    public static final String TABLE_NAME2 = "list_dates";
    public static final String COL4 = "name";
    public static final String COL5 = "date";
    public static final String COL6 = "present";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (NAME TEXT, " + " MOBILE TEXT, " + " QR TEXT)";
        db.execSQL(createTable);

        createTable = "CREATE TABLE " + TABLE_NAME2 + " (date TEXT, " + " name TEXT, " + " present TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(new StringBuilder().append("DROP IF TABLE EXISTS ").append(TABLE_NAME).toString());
        db.execSQL(new StringBuilder().append("DROP IF TABLE EXISTS ").append(TABLE_NAME2).toString());
        onCreate(db);
    }

    public boolean addData(String item1,String item2) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item1);
        contentValues.put(COL2, item2);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean addDate(String item1,String item2,String item3) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL4, item1);
        contentValues.put(COL5, item2);
        contentValues.put(COL6, item3);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDates(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2;
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Returns only the ID that matches the name passed in
     * @param name
     * @return
     */

    public Cursor getItemID(String name){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + COL2 + " FROM " + TABLE_NAME +
                " WHERE " + COL1 + " = '" + name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newname
     * @param newmobile
     * @param oldmobile
     * @return
     */
    public void updateName(String newname, String newmobile, String oldmobile,String oldname ){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL1 + " = '" + newname + "' WHERE  " + COL2 + " = '" + oldmobile + "'";
        db.execSQL(query);
        query = "UPDATE " + TABLE_NAME2 + " SET " + COL1 + " = '" + newname + "' WHERE  " + COL1 + " = '" + oldname + "'";
        db.execSQL(query);
        query = "UPDATE " + TABLE_NAME + " SET " + COL2 + " = '" + newmobile + "' WHERE  " + COL2 + " = '" + oldmobile + "'";
        db.execSQL(query);


    }

    /**
     * Delete from database
     * @param name
     * @param mobile
     */
    public void deleteName(String name,String mobile){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL1 + " = '" + name + "'" +
                " AND " + COL2 + " = '" + mobile + "'";
        db.execSQL(query);
    }

    public void deletedate(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE "
                + COL6 + " = '" + date + "'";
        db.execSQL(query);
    }

}
