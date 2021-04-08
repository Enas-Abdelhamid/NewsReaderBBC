package com.example.newsreaderbbc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDBhelper extends SQLiteOpenHelper {

    public static final String databaseName = "newsReader.db";
    public static final String tableName = "favourite_articles";
    public static final String column1 = "articleID";
    public static final String column2 = "articleURL";


    public MyDBhelper(Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + tableName + " (articleID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " articleURL TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(db);
    }

    public boolean addData(String contentMSG) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column2, contentMSG);

        long result = db.insert(tableName, null, contentValues);


        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Cursor getListContents(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + tableName, null);
        return data;
    }
}