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
        switch(position) {
            case 0:
                return new Table_kouma();
            case 1:
                return new Table_youmu();
            case 2:
                return new Table_suimu();
            case 3:
                return new Table_eiya();
            case 4:
                return new Table_kaei();
            case 5:
                return new Table_bunka();
            case 6:
                return new Table_fuzin();
            case 7:
                return new Table_hisou();
            case 8:
                return new Table_tirei();
            case 9:
                return new Table_seiren();
            case 10:
                return new Table_tensoku();
            case 11:
                return new Table_ds();
            case 12:
                return new Table_daisen();
            case 13:
                return new Table_sinrei();
            case 14:
                return new Table_sinki();
            case 15:
                return new Table_kisin();
            case 16:
                return new Table_amnjk();
            default:
                return null;
        }
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
