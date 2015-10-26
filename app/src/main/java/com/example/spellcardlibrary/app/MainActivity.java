package com.example.spellcardlibrary.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    //メニューアイテム識別用ID
    private static final int credit_ID = 0;

    //各タブに割り当てられたアクティビティを起動するためのインテント群
    private ArrayList<Intent> intentList = new ArrayList<Intent>();

    //スワイプでタブを切り替えるためのViewPager
    ViewPager mViewPager;

    //リソースから持ってきた作品名を格納した配列
    private String[] titles;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //作品名を取得
        titles = getResources().getStringArray(R.array.titles);

        //ActionBarを作成
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //タブをセット
        FragmentManager manager = getFragmentManager();

//        Bundle bundle = new Bundle();
//        bundle.putString("title", "東方紅魔郷");

        BaseTable fragment = new BaseTable();
        //fragment.setArguments(bundle);

        FragmentTransaction t = manager.beginTransaction();
        t.add(R.id.parentLL, fragment, "東方紅魔郷");
        t.commit();
        actionBar.addTab(actionBar.newTab().setText("東方紅魔郷").setTabListener(new TabListener<BaseTable>(this, "東方紅魔郷", BaseTable.class)));

        BaseTable youmu = new BaseTable();
        t = manager.beginTransaction();
        t.add(R.id.parentLL, youmu, "東方妖々夢");
        t.commit();
        actionBar.addTab(actionBar.newTab().setText("東方妖々夢").setTabListener(new TabListener<BaseTable>(this, "東方妖々夢", BaseTable.class)));

//        for(String title : titles) {
//            Bundle bundle = new Bundle();
//            bundle.putString("title", title);
//
//            BaseTable fragment = new BaseTable();
//            fragment.setArguments(bundle);
//
//            FragmentTransaction t = manager.beginTransaction();
//            t.add(R.id.parentLL, fragment, title);
//            t.commit();
//
//            actionBar.addTab(actionBar.newTab().setText(title).setTabListener(new TabListener<Table_kouma>(this, title, Table_kouma.class)));
//        }

        //PagerAdapterを生成
//        MainFragmentAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
//        mViewPager = (ViewPager)findViewById(R.id.pager);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        android.app.FragmentManager manager = getFragmentManager();
//        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
//        final MainFragmentAdapter adapter = new MainFragmentAdapter(manager);
//        viewPager.setAdapter(adapter);

//        //タブホストのインスタンス化
//        TabHost host = (TabHost)findViewById(R.id.tabhost);
//        host.setup(this.getLocalActivityManager());
//
//        //タブに作品名を割り当てる
//        TabHost.TabSpec spec;
//        Intent i;
//        for(int it = 0; it < workname.length; it++) {
//
//            //インテントを作成
//            i = new Intent(this, BaseTable.class);
//            i.putExtra("作品名", workname[it]);
//            intentList.add(i);
//
//            //タブを作成しインテントを設定
//            spec = host.newTabSpec(workname[it]);
//            spec.setContent(intentList.get(it));
//            spec.setIndicator(workname[it]);
//            host.addTab(spec);
//        }

//        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
//            @Override
//            public void onTabChanged(String tabId) {
//                mViewPager.setCurrentItem(Integer.valueOf(tabId));
//            }
//        });
//
//        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                super.onPageSelected(position):
//                host.setCurrentTab(position);
//            }
//        });

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


    //オプションメニューの作成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        menu.add(Menu.NONE, R.id.action_search, Menu.NONE, "検索");
        menu.add(Menu.NONE, R.id.action_settings, Menu.NONE, "設定");
        menu.add(Menu.NONE, R.id.action_credit, Menu.NONE, R.string.action_credit);

        return super.onCreateOptionsMenu(menu);
    }

    //メニューが選択された時の処理
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent();

        //addした時のIDで識別
        switch (item.getItemId()) {
            case R.id.action_search:
                //openSearch();
                return true;
            case R.id.action_settings:
                //openSettings();
                return true;
            case R.id.action_credit:
                startActivity(new Intent(this, Credit.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
