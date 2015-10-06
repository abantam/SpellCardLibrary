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
public class Table_kouma extends BaseTable {

    public final String title = "東方紅魔郷";

    public Table_kouma() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.setTitle(title);
//        super.setDatabase();
//        super.makeList();
//        super.setMainView();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.activity_base_table, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
