package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by admin on 2015/07/09.
 */
public class Table_kouma extends ListFragment {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;
    private ArrayList<HashMap<String, String>> scList;

    public Table_kouma() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setDatabase();

        Cursor c = db.query("東方紅魔郷", new String[]{"_id","スペルカード名"}, null, null, null, null, null);
        c.moveToFirst();

        //カーソルを1つずつ動かして一時変数にデータを格納
        for (int i = 0; i < c.getCount(); i++, c.moveToNext()) {
            HashMap<String, String> temp = new HashMap<String, String>();
//            if(workname.equals("東方萃夢想") || workname.equals("東方花映塚") || workname.equals("東方緋想天") || workname.equals("東方非想天則") || workname.equals("東方心綺楼")) {
//                temp.put("Number", null);
//                temp.put("scName", c.getString(1));
//            }else {
                temp.put("Number", c.getString(1));
                temp.put("scName", c.getString(2));
//            }

            scList.add(temp);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
