package com.example.spellcardlibrary.app;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

/*TabListenerの実装：http://t-horikiri.hatenablog.jp/entry/20121204/1354604306
* TabListenerのカスタマイズ：http://yan-note.blogspot.jp/2012/10/android-fragmenttab.html
* ViewPagerの実装：http://furudate.hatenablog.com/entry/2013/06/10/232244*/

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

    //メニューアイテム識別用ID
    private static final int credit_ID = 0;

    //ViewPagerを使用する際に使用するアダプター
    private ListPagerAdapter pagerAdapter;

    //スワイプでタブを切り替えるためのViewPager
    private ViewPager mViewPager;

    //リソースから持ってきた作品名を格納した配列
    private String[] titles;

    private FragmentManager manager;

    private ActionBar actionBar;
    private final int TAB_COUNT = 17;//タブの総数
    private final String BUNDLE_KEY = "title";//作品名を入れるBundleのキー

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //FragmentManagerを生成
        manager = getSupportFragmentManager();

        //PagerAdapterを生成
        mViewPager = (ViewPager) findViewById(R.id.pager);
        ListPagerAdapter adapter = new ListPagerAdapter(manager);
        mViewPager.setAdapter(adapter);

        //スワイプしたときにもActionbarのタブ（NavigationItem）を常に表示させる処理
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });

        //作品名を取得
        titles = getResources().getStringArray(R.array.titles);

        //ActionBarを作成
        actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        //タブを生成
        for (String title : titles) {
            ActionBar.Tab tab = actionBar.newTab();
            tab.setText(title);
            tab.setTabListener(this);
            actionBar.addTab(tab);
        }

        //初回起動時に0番目のタブを表示する
        actionBar.getTabAt(0).select();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    //オプションメニューの作成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);

        //menu.add(Menu.NONE, R.id.action_search, Menu.NONE, "検索");
        //menu.add(Menu.NONE, R.id.action_settings, Menu.NONE, "設定");
        menu.add(Menu.NONE, R.id.action_credit, Menu.NONE, R.string.action_credit);

        return super.onCreateOptionsMenu(menu);
    }

    //メニューが選択された時の処理
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent();

        //addした時のIDで識別
        switch (item.getItemId()) {
//            case R.id.action_search:
//                //openSearch();
//                return true;
//            case R.id.action_settings:
//                //openSettings();
//                return true;
            case R.id.action_credit:
                startActivity(new Intent(this, Credit.class));
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    //スワイプさせるためのPagerAdapterクラス
    class ListPagerAdapter extends FragmentPagerAdapter {

        public ListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.support.v4.app.Fragment getItem(int position) {

            //作品名をタブの位置から判断しbundleに入れる
            Bundle bundle = new Bundle();
            bundle.putString(BUNDLE_KEY, titles[position]);

            //fragmentの生成
            BaseTable fragment = new BaseTable();
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return TAB_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

    }

}



