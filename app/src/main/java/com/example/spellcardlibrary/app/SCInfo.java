package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;


public class SCInfo extends Activity {

    private SQLiteDatabase db;
    private DatabaseHelper mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scinfo);

        //インテントを取得
        Intent i = getIntent();
        String title = i.getStringExtra("title");
        int number = i.getIntExtra("number", 1);

        //BaseTableからデータベースを取得
        setDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + title + " WHERE _id=" + number, null);
        c.moveToFirst();

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(c.getString(2));

        TextView charname = (TextView)findViewById(R.id.charnameValue);
        charname.setText(c.getString(3));

        TextView difficulty = (TextView)findViewById(R.id.difficultyValue);
        difficulty.setText(c.getString(4));

        TextView stage = (TextView)findViewById(R.id.stageValue);
        stage.setText(c.getString(5));

        TextView time = (TextView)findViewById(R.id.timeValue);
        time.setText(c.getString(6));

        TextView maxSCB = (TextView)findViewById(R.id.maxSCBValue);
        maxSCB.setText(c.getString(7));

        TextView redSCB = (TextView)findViewById(R.id.redSCBValue);
        redSCB.setText(c.getString(8));
    }

    @Override
    public void onDestroy() {
        //db.close();
        super.onDestroy();
    }

    //assetsからデータベースをコピー
    private void setDatabase() {
        mDbHelper = new DatabaseHelper(this);
        try {
            mDbHelper.createEmptyDatabase();
            db = mDbHelper.openDatabase();
        }catch(IOException ioe) {
            throw new Error("Unable to create database");
        }catch(SQLException sqle) {
            throw sqle;
        }
    }

}
