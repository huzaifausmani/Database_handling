package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "personality.db";
    private static final String TABLE_NAME = "personalities";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_INTROVERT = "introvert";
    private static final String COLUMN_EXTROVERT = "extrovert";
    private static final String COLUMN_AMBIVERT = "ambivert";
    private static final String COLUMN_PSYCHOPATH = "psychopath";
    private static final String COLUMN_SOCIOPATH = "sociopath";

    public Database(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String creation = "create table if not exists " + TABLE_NAME + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " text, "
                + COLUMN_INTROVERT + " integer, "
                + COLUMN_EXTROVERT + "integer, "
                + COLUMN_AMBIVERT + "integer, "
                + COLUMN_PSYCHOPATH + "integer, "
                + COLUMN_SOCIOPATH + "integer )";
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        String drop = "drop table if exists " + TABLE_NAME;
        db.execSQL(drop);
        onCreate(db);
    }

    public void insertOne(Personality personality){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, personality.name);
        contentValues.put(COLUMN_INTROVERT, personality.introvert);
        contentValues.put(COLUMN_EXTROVERT, personality.extrovert);
        contentValues.put(COLUMN_AMBIVERT, personality.ambivert);
        contentValues.put(COLUMN_PSYCHOPATH, personality.psychopath);
        contentValues.put(COLUMN_SOCIOPATH, personality.sociopath);

        db.insert(TABLE_NAME, null, contentValues);
        db.close();
    }
}
