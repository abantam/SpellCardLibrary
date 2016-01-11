package com.example.spellcardlibrary.app;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*FragmentからActivityにコールバックする方法：http://y-anz-m.blogspot.jp/2012/06/fragment-activity.html*/

public class BaseTable extends ListFragment {

    private static SQLiteDatabase db;//データベースを格納
    private DatabaseHelper mDbHelper;
    private ArrayList<HashMap<String, String>> scList = new ArrayList<HashMap<String, String>>();
    private String title;//作品名

    public BaseTable() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        title = getArguments().getString("title");

        //データベースを操作するためのカーソルを作成
        Cursor c = db.query(title, null, null, null, null, null, null);
        c.moveToFirst();

        //カーソルを一つずづ動かして一時変数にデータを格納
        for(int i = 0; i < c.getCount(); i++, c.moveToNext()) {
            HashMap<String, String> temp = new HashMap<String, String>();
            if(title.equals("東方萃夢想") || title.equals("東方花映塚") || title.equals("東方緋想天") || title.equals("東方非想天則") || title.equals("東方心綺楼")) {
                temp.put("scName", c.getString(1));
            }else {
                temp.put("Number", c.getString(1));
                temp.put("scName", c.getString(2));
            }
            scList.add(temp);
        }

        //アダプターにデータを格納してListViewにセット
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), scList, R.layout.listview_layout,
                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
        setListAdapter(adapter);

        return inflater.inflate(R.layout.table, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //リストをクリックしたら詳細画面のActivityに遷移
        getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClassName("com.example.spellcardlibrary.app", "com.example.spellcardlibrary.app.SCInfo");
                intent.putExtra("info", "SELECT * FROM " + title + "");
                startActivity(intent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }


    //assetsからデータベースをコピー
    private void setDatabase() {
        mDbHelper = new DatabaseHelper(getActivity());
        try {
            mDbHelper.createEmptyDatabase();
            db = mDbHelper.openDatabase();
        }catch(IOException ioe) {
            throw new Error("Unable to create database");
        }catch(SQLException sqle) {
            throw sqle;
        }
    }

    //スクロール制御
    private void scrollController(TextView tv) {
        tv.setSingleLine();
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String s) {
        title = s;
    }
}
