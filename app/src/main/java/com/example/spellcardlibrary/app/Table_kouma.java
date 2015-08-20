package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.example.spellcardlibrary.app.SerializedIntent;

/**
 * Created by admin on 2015/07/09.
 */
public class Table_kouma extends ListFragment {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;
    private ArrayList<HashMap<String, String>> scList;
    private String title;//作品名

    public Table_kouma() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scList = new ArrayList<HashMap<String, String>>();
        setDatabase();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.table, container, false);

        //Activityから作品名を受け取る
//        SerializedIntent intent = (SerializedIntent)getArguments().getSerializable("title");
//        title = intent.getStringExtra("title");
//
//        Cursor c = db.query(title, null, null, null, null, null, null);
//        c.moveToFirst();
//
//        //カーソルを1つずつ動かして一時変数にデータを格納
//        for (int i = 0; i < c.getCount(); i++, c.moveToNext()) {
//            HashMap<String, String> temp = new HashMap<String, String>();
////            if(workname.equals("東方萃夢想") || workname.equals("東方花映塚") || workname.equals("東方緋想天") || workname.equals("東方非想天則") || workname.equals("東方心綺楼")) {
////                temp.put("Number", null);
////                temp.put("scName", c.getString(1));
////            }else {
//            temp.put("Number", c.getString(1));
//            temp.put("scName", c.getString(2));
////            }
//
//            scList.add(temp);
//        }
//
//        SimpleAdapter adapter = new SimpleAdapter(getActivity(), scList, R.layout.listview_layout,
//                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
//        setListAdapter(adapter);

        return inflater.inflate(R.layout.table, container, false);
    }

    //ActivityとこのFragmentとの紐付けを行う
//    @Override
//    public void onAttach(Activity activity) {
//        super.onAttach(activity);
//        try {
//            TabListener listener = (TabListener)activity;
//        }catch(ClassCastException e) {
//
//        }
//    }

    @Override
    public void onStart() {
        super.onStart();
    }

    //データベースにアクセス
    private void setDatabase() {
        mDbHelper = new DatabaseHelper(getActivity());
        try {
            mDbHelper.createEmptyDatabase();
            db = mDbHelper.openDatabase();
        }catch(IOException ioe) {

        }catch(SQLException sqle) {

        }
    }
}
