package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Table_kouma extends Activity {

    private ListView mainView;
    private SQLiteDatabase db;
    private DatabaseHelper mDbHelper;
    private ArrayList<HashMap<String, String>> scList = new ArrayList<HashMap<String, String>>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_kouma);
        setDatabase();
        makeList();
        setMainView();
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

    private void makeList() {

        //インテントを取得したあと作品名を取得
        Intent intent = getIntent();
        String workname = intent.getStringExtra("東方紅魔郷");

        Cursor c = db.query(workname, null, null, null, null, null, null);
        c.moveToFirst();

        for(int i = 0; i < c.getCount(); i++, c.moveToNext()) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("Number", c.getString(1));
            temp.put("scName", c.getString(2));
            scList.add(temp);
        }
    }

    private void setMainView() {
        mainView = (ListView)findViewById(R.id.mainView);
        SimpleAdapter adapter = new SimpleAdapter(this, scList, R.layout.listview_layout,
                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
        mainView.setAdapter(adapter);
    }
}