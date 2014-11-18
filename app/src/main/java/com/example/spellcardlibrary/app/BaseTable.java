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


public class BaseTable extends Activity {

    private ListView mainView;//リストビュー
    private SQLiteDatabase db;//データベースを格納
    private DatabaseHelper mDbHelper;
    private ArrayList<HashMap<String, String>> scList = new ArrayList<HashMap<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_table);
        setDatabase();
        makeList();
        setMainView();
    }

    @Override
    public void onDestroy() {
        db.close();
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

    //コピーしたデータベースからリストを作成
    private void makeList() {

        //インテントを取得したあと作品名を取得
        Intent intent = getIntent();
        String workname = intent.getStringExtra("作品名");

        //データベース内のカーソルを設定
        Cursor c = db.query(workname, null, null, null, null, null, null);
        c.moveToFirst();

        //カーソルを1つずつ動かして一時変数にデータを格納
        for(int i = 0; i < c.getCount(); i++, c.moveToNext()) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("Number", c.getString(1));
            temp.put("scName", c.getString(2));
            scList.add(temp);
        }
    }

    //リストビューをセットしてリストを表示
    private void setMainView() {
        mainView = (ListView)findViewById(R.id.mainView);
        SimpleAdapter adapter = new SimpleAdapter(this, scList, R.layout.listview_layout,
                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
        mainView.setAdapter(adapter);

//        mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(Table_kouma.this, SCInfo.class);
//                i.putExtra("scName", position);
//                startActivity(i);
//            }
//        });
    }


}