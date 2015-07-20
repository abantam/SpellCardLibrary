package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.io.IOException;

/**
 * Created by admin on 2015/07/09.
 */
public class Table_kouma extends ListFragment {

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;
    private static final String[] data={"a", "b"};

    public Table_kouma() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setDatabase();
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
//    private void setDatabase() {
//        mDbHelper = new DatabaseHelper(this);
//        try {
//            mDbHelper.createEmptyDatabase();
//            db = mDbHelper.openDatabase();
//        }catch(IOException ioe) {
//
//        }catch(SQLException sqle) {
//
//        }
//    }
}
