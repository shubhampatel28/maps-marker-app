package edu.sjsu.android.hw4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocationsDB extends SQLiteOpenHelper {
    static final String _ID = "_id";
    static final String LATITUDE = "latitude";
    static final String LONGITUDE = "longitude";
    static final String ZOOMLEVEL = "zoomLevel";

    private SQLiteDatabase db;
    static final String DATABASE_NAME = "Location";
    static final String LOCATIONS_TABLE_NAME = "locationInfo";
    private static final int DATABASE_VERSION = 1;
    static final String CREATE_DB_TABLE = " CREATE TABLE " + LOCATIONS_TABLE_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " + " latitude DOUBLE, " + " longitude DOUBLE, " + " zoomLevel TEXT);";


    public LocationsDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.db = getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + LOCATIONS_TABLE_NAME);
//        onCreate(db);
    }

    public long insert(ContentValues contentValues) {
        long id = db.insert(LOCATIONS_TABLE_NAME, null, contentValues);
        return id;
    }

    public int delete() {
        int del = db.delete(LOCATIONS_TABLE_NAME, null, null);
        return del;
    }

    public Cursor getLocations() {
        return db.query(LOCATIONS_TABLE_NAME, new String[]{_ID, LATITUDE, LONGITUDE, ZOOMLEVEL}, null, null, null, null, null);
    }

}
