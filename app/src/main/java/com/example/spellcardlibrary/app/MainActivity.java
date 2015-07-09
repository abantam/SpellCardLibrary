package com.example.spellcardlibrary.app;

import android.app.ActivityGroup;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends ActivityGroup {

    //メニューアイテム識別用ID
    private static final int credit_ID = 0;

    //各タブに割り当てられたアクティビティを起動するためのインテント群
    private ArrayList<Intent> intentList = new ArrayList<Intent>();

    //スワイプでタブを切り替えるためのViewPager
    ViewPager mViewPager;

    //リソースから持ってきた作品名を格納した配列
    private String[] workname = {
            "東方紅魔郷",
            "東方妖々夢",
            "東方萃夢想",
            "東方永夜抄",
            "東方花映塚",
            "東方文花帖",
            "東方風神録",
            "東方緋想天",
            "東方地霊殿",
            "東方星蓮船",
            "東方非想天則",
            "ダブルスポイラー",
            "妖精大戦争",
            "東方神霊廟",
            "東方心綺楼",
            "東方輝針城",
            "弾幕アマノジャク",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //PagerAdapterを生成
        MainFragmentAdapter mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

//        android.app.FragmentManager manager = getFragmentManager();
//        final ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
//        final MainFragmentAdapter adapter = new MainFragmentAdapter(manager);
//        viewPager.setAdapter(adapter);

        //タブホストのインスタンス化
        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup(this.getLocalActivityManager());

        //タブに作品名を割り当てる
        TabHost.TabSpec spec;
        Intent i;
        for(int it = 0; it < workname.length; it++) {

            //インテントを作成
            i = new Intent(this, BaseTable.class);
            i.putExtra("作品名", workname[it]);
            intentList.add(i);

            //タブを作成しインテントを設定
            spec = host.newTabSpec(workname[it]);
            spec.setContent(intentList.get(it));
            spec.setIndicator(workname[it]);
            host.addTab(spec);
        }

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
        switch(item.getItemId()) {
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
