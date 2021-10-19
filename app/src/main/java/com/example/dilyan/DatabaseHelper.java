package com.example.dilyan;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    //table name
    public static final String TABLE_NAME = "NOTES";

    //table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String NOTE = "note";

    //db info
    static final String DB_NAME = "DILYAN_APP.DB";

    //db version
    static final int DB_VERSION = 1;

    //create table query
    private static final String CREATE_TABLE = "create table " +
            TABLE_NAME + "(" + _ID +
            " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
            " TEXT NOT NULL, " + NOTE + " TEXT);";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME + ";");
        onCreate(db);
    }
}
