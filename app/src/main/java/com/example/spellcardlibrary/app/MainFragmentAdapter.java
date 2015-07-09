package com.example.spellcardlibrary.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by admin on 2015/07/06.
 */
public class MainFragmentAdapter extends FragmentPagerAdapter {

    public MainFragmentAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:

            default:

        }

        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "a";
    }
}
