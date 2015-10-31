package com.example.spellcardlibrary.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.util.Log;

/**
 * Created by admin on 2015/07/10.
 * 参考文献：http://yan-note.blogspot.jp/2012/10/android-fragmenttab.html
 */
public class TabListener<T extends Fragment> implements ActionBar.TabListener {

    private Fragment mFragment;
    private final Activity mActivity;
    private final String mTag;
    private final Class<T> mClass;

    public TabListener(Activity activity, String tag, Class<T> clz) {
        mActivity = activity;
        mTag = tag;
        mClass = clz;

        //FragmentManagerからFragmentを探す
        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
    }

    //タブが選択された時の処理
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(mFragment == null) {
            mFragment = Fragment.instantiate(mActivity, mClass.getName());
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().add(R.id.parentLL, mFragment, mTag).commit();
        }else {
            if(mFragment.isDetached()) {
                FragmentManager fm = mActivity.getFragmentManager();
                fm.beginTransaction().attach(mFragment).commit();
            }
        }

        //Activityにデータベースの問い合わせをする
        if(mFragment instanceof BaseTable) {

        }
    }

    //タブの選択が解除された時の処理
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(mFragment != null) {
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().detach(mFragment).commit();
        }
    }

    //タブが2度目以降に選択された時の処理
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
