package com.example.spellcardlibrary.app;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

/**
 * Created by admin on 2015/07/10.
 * 蜿り�譁�鍵�喇ttp://yan-note.blogspot.jp/2012/10/android-fragmenttab.html
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

        //FragmentManager縺九ｉFragment繧呈爾縺�
        mFragment = mActivity.getFragmentManager().findFragmentByTag(mTag);
    }

    //繧ｿ繝悶′驕ｸ謚槭＆繧後◆譎ゅ�蜃ｦ逅�
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
    }

    //繧ｿ繝悶�驕ｸ謚槭′隗｣髯､縺輔ｌ縺滓凾縺ｮ蜃ｦ逅�
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
        if(mFragment != null) {
            FragmentManager fm = mActivity.getFragmentManager();
            fm.beginTransaction().detach(mFragment).commit();
        }
    }

    //繧ｿ繝悶′莠悟ｺｦ逶ｮ莉･髯阪↓驕ｸ謚槭＆繧後◆譎ゅ�蜃ｦ逅�
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
