package com.example.ornella.appmeteo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.MessageFormat;


public class Database extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "statistiche.db";

    private static final int SCHEMA_VERSION = 1;

    public Database(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE statistiche";
        sql += "(_id INTEGER PRIMARY KEY,";
        sql += "location TEXT NOT NULL,";
        sql += "date TEXT,";
        sql += "temperature TEXT);";

        db.execSQL(sql);

    }

     public void insertMeteo(SQLiteDatabase db, String location, String date, String temperature) {

        ContentValues v = new ContentValues();
        v.put(TemperaturaTable.LOCATION, location);
        v.put(TemperaturaTable.DATE, date);
        v.put(TemperaturaTable.TEMPERATURE, temperature);
        db.insert(TemperaturaTable.TABLE_NAME, null, v);

      }

     public Cursor getLocation() {

        return (getReadableDatabase().query(
                TemperaturaTable.TABLE_NAME,
                TemperaturaTable.COLUMNS,
                null,
                null,
                null,
                null,
                TemperaturaTable.LOCATION));


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }

}