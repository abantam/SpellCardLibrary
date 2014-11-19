package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;


public class SCInfo extends Activity {

    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scinfo);

        Intent i = getIntent();
        String workname = i.getStringExtra("workname");
        String scname = i.getStringExtra("scName");

        //BaseTableからデータベースを取得
        db = BaseTable.getDatabase();
        Cursor c = db.query(workname, new String[]{"スペルカード名"}, null, null, null, null, null);
        c.moveToFirst();

        TextView name = (TextView)findViewById(R.id.name);
        name.setText(c.getString(2));
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }



}
