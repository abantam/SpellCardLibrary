package com.example.spellcardlibrary.app;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by takeshi on 2015/12/07.
 */
public class ListPagerAdapter2 extends FragmentPagerAdapter {

    public ListPagerAdapter2(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {

        //作品名をタブの位置から判断しbundleに入れる
        Bundle bundle = new Bundle();
        switch(position) {
            case 0:
                bundle.putString("title", "東方紅魔郷");
                break;
            case 1:
                bundle.putString("title", "東方妖々夢");
                break;
            case 2:
                bundle.putString("title", "東方萃夢想");
                break;
            case 3:
                bundle.putString("title", "東方永夜抄");
                break;
            case 4:
                bundle.putString("title", "東方花映塚");
                break;
            case 5:
                bundle.putString("title", "東方文花帖");
                break;
            case 6:
                bundle.putString("title", "東方風神録");
                break;
            case 7:
                bundle.putString("title", "東方緋想天");
                break;
            case 8:
                bundle.putString("title", "東方地霊殿");
                break;
            case 9:
                bundle.putString("title", "東方星蓮船");
                break;
            case 10:
                bundle.putString("title", "東方非想天則");
                break;
            case 11:
                bundle.putString("title", "ダブルスポイラー");
                break;
            case 12:
                bundle.putString("title", "妖精大戦争");
                break;
            case 13:
                bundle.putString("title", "東方神霊廟");
                break;
            case 14:
                bundle.putString("title", "東方心綺楼");
                break;
            case 15:
                bundle.putString("title", "東方輝針城");
                break;
            case 16:
                bundle.putString("title", "弾幕アマノジャク");
                break;
        }

        //fragmentの生成
        BaseTable fragment = new BaseTable();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 17;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "touhoukoumakyou";
    }

}
