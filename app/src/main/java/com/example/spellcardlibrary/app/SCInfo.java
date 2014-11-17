package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


public class SCInfo extends Activity {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scinfo);
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }

}
