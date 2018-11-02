package com.example.kalpeshsoni.systemdesign1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Data.db";
    public static final String TABLE_NAME = "Design";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Customer_Name";
    public static final String COL_3 = "Contact_Number";
    public static final String COL_4 = "Priority";
    public static final String COL_5 = "Communication_Data";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT, CUSTOMER_NAME TEXT, CONTACT_NUMBER INTEGER, PRIORITY INTEGER, COMMUNICATION_DATA TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
        }

    public boolean insertData(String Customer_Name, String Contact_Number, String Priority, String Communication_Data) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, Customer_Name);
        contentValues.put(COL_3, Contact_Number);
        contentValues.put(COL_4, Priority);
        contentValues.put(COL_5, Communication_Data);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
        {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}
