package com.example.spellcardlibrary.app;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends ActivityGroup {

    //各タブの起動するアクティビティを保持したクラス
    Class classlist[] = {
            Table_kouma.class,
            Table_youmu.class,
            Table_suimu.class,
            Table_eiya.class,
            Table_kaei.class,
            Table_bunka.class,
            Table_fuzin.class,
            Table_hisou.class,
            Table_tirei.class,
            Table_seiren.class,
            Table_tensoku.class,
            Table_ds.class,
            Table_daisen.class,
            Table_sinrei.class,
            Table_sinki.class,
            Table_kisin.class,
    };

    //各タブに割り当てられたアクティビティを起動するためのインテント群
    ArrayList<Intent> intentList = new ArrayList<Intent>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //タブホストのインスタンス化
        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup(this.getLocalActivityManager());

        //タブに作品名を割り当てる
        String[] workname = getResources().getStringArray(R.array.workname);
        TabHost.TabSpec spec;
        Intent i;
        for(int it = 0; it < classlist.length; it++) {

            //インテントを作成
            i = new Intent(this, classlist[it]);
            i.getStringExtra(workname[it]);
            intentList.add(i);

            //タブを作成しインテントを設定
            spec = host.newTabSpec(workname[it]);
            spec.setContent(intentList.get(it));
            spec.setIndicator(workname[it]);
            host.addTab(spec);
        }



    }
}
