package com.example.spellcardlibrary.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
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
import java.util.HashMap;

/*TabListenerの実装：http://t-horikiri.hatenablog.jp/entry/20121204/1354604306
* TabListenerのカスタマイズ：http://yan-note.blogspot.jp/2012/10/android-fragmenttab.html*/

public class MainActivity extends Activity implements ActionBar.TabListener {

    //メニューアイテム識別用ID
    private static final int credit_ID = 0;

    //スワイプでタブを切り替えるためのViewPager
    ViewPager mViewPager;

    //リソースから持ってきた作品名を格納した配列
    private String[] titles;

    //現在選択しているタブの番号
    private int selectedTabPosition = 0;

    //フラグメントとタブの番号の対応表
    private HashMap<Integer, String> fragmentAndTab = new HashMap<Integer, String>();

    private BaseTable mFragment;

    private ActionBar actionBar;

    private DatabaseHelper mDbHelper;
    private SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //データベースを生成
        setDatabase();

        //作品名を取得
        titles = getResources().getStringArray(R.array.titles);

        //ActionBarを作成
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //タブをセット
        FragmentManager manager = getFragmentManager();

        int count = 0;
        //タブを生成
        for(String title : titles) {
            Bundle bundle = new Bundle();
            bundle.putString("title", title);

            BaseTable fragment = new BaseTable();
            fragment.setArguments(bundle);

            FragmentTransaction t = manager.beginTransaction();
            t.add(R.id.parentLL, fragment, title);
            t.commit();

            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(title);
            tab.setTabListener(new TabListener<BaseTable>(fragment, manager, title));
            //tab.setTabListener(this);
            actionBar.addTab(tab);
            //fragmentAndTab.put(count, title);
            //count++;
        }

        //初回起動時に0番目のタブを表示する
        //actionBar.getTabAt(0).select();

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

//    @Override
//    public void onOkClicked() {
//        Bundle bundle = new Bundle();
//        bundle.putString("title", title);
//
//        BaseTable fragment = new BaseTable();
//        fragment.setArguments(bundle);
//
//        FragmentTransaction t = manager.beginTransaction();
//        t.add(R.id.parentLL, fragment, title);
//        t.commit();
//
//        actionBar.addTab(actionBar.newTab().setText(title).setTabListener(new TabListener<BaseTable>(this, title, BaseTable.class)));
//    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction transaction) {
        /*tabPositionとtitlesの添え字は一致する*/
        int tabPosition = tab.getPosition();
//        String title = titles[selectedTabPosition];
//        selectedTabPosition = tabPosition;
//        if(selectedTabPosition == tabPosition) {
//
//            Bundle bundle = new Bundle();
//            bundle.putString("title", title);
//
//            mFragment = new BaseTable();
//            mFragment.setArguments(bundle);
//
//            transaction.add(R.id.parentLL, mFragment, title);
//            actionBar.addTab(tab);
//
//        }

        switch(tabPosition) {
            case 0: makeSpellCardList(getFragmentManager(), "東方紅魔郷"); break;
        }

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction transaction) {
        //transaction.remove(mFragment);
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction transaction) {

    }

    //タブに表示するリストを生成
    private void makeSpellCardList(FragmentManager manager, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(title, title);

        BaseTable fragment = new BaseTable();
        fragment.setArguments(bundle);

        FragmentTransaction t = manager.beginTransaction();
        t.add(R.id.parentLL, fragment, title);
        t.commit();
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

    //データベースの取得
    public SQLiteDatabase getDatabase() {
        return db;
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
