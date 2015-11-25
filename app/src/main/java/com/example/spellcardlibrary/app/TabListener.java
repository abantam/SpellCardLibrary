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
    private FragmentManager fm;
    //private final Activity mActivity;
    private String mTag;
    //private final Class<T> mClass;

//    public TabListener(Activity activity, String tag, Class<T> clz) {
//        mActivity = activity;
//        mTag = tag;
//        mClass = clz;
//
//        //FragmentManagerからFragmentを探す
//        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
//    }

    public TabListener(Fragment fragment, FragmentManager manager, String tag) {
        mFragment = fragment;
        fm = manager;
        mTag = tag;
    }

    //タブが選択された時の処理
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        String tabText = tab.getText().toString();
Log.v(mTag, mTag);
        //選択したタブのテキストとタグが一致したら処理を行う
        //if(tabText == mTag) {
            //Fragmentがまだない場合
            if (mFragment == null) {
                fm.beginTransaction().add(R.id.parentLL, mFragment, mTag).commit();
            } else {
                if (mFragment.isDetached()) {
                    fm.beginTransaction().attach(mFragment).commit();
                }
            }
        //}

    }

    //タブの選択が解除された時の処理
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(mFragment != null) {
            fm.beginTransaction().detach(mFragment).commit();
        }
    }

    //タブが2度目以降に選択された時の処理
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        //onTabSelected(tab, ft);
    }
}
