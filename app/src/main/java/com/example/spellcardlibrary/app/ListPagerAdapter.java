package com.example.spellcardlibrary.app;

import android.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by takeshi on 2015/12/07.
 */
public class ListPagerAdapter extends FragmentPagerAdapter {

    public ListPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return new android.support.v4.app.Fragment();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }

}
