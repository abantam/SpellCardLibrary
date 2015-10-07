package com.example.spellcardlibrary.app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2015/07/09.
 */
public class Table_tensoku extends BaseTable {

    public Table_tensoku() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("“Œ•û”ñ‘z“V‘¥");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public String getTitle() {return super.getTitle();}

    @Override
    public void setTitle(String title) {super.setTitle(title);}

}
