package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Table_kouma extends Activity {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;
    //private StationCodeDao mStationCodeDao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDatabase();
        findData(1);
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }

    private void setDatabase() {
        mDbHelper = new DatabaseHelper(this);
        try {
            mDbHelper.createEmptyDatabase();
            db = mDbHelper.openDatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        } catch (SQLException sqle) {
            throw sqle;
        }
    }

    private static final String[] COLUMNS = {"*"};

    private Cursor findData(int id) {
        Cursor cursor = db.rawQuery("SELECT * FROM 東方紅魔郷 ", null);
        return cursor;
    }
}