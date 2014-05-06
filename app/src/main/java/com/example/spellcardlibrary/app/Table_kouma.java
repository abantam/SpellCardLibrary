package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Table_kouma extends Activity {

    private ListView mainView;
    private SQLiteDatabase db;
    private ArrayList<HashMap<String, String>> scList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_kouma);

        //データベースのオブジェクトを取得
        db = new DatabaseHelper(this).getReadableDatabase();

        //インテントを取得したあと作品名を取得
        Intent intent = getIntent();
        String workname = intent.getStringExtra("東方紅魔郷");

        makeList("SELECT * FROM" + workname);
    }

    private void makeList(String query) {
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();

        for(int i = 0; i < c.getCount(); i++) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("Number", c.getString(1));
            temp.put("scName", c.getString(2));
            scList.add(temp);
        }
    }

    private void setMainView() {
        mainView = (ListView)findViewById(R.id.mainView);
        SimpleAdapter adapter = new SimpleAdapter(this, scList, android.R.layout.simple_list_item_1,
                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
        mainView.setAdapter(adapter);
    }
}
