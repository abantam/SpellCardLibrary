package com.example.spellcardlibrary.app;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import java.util.ArrayList;

public class MainActivity extends ActivityGroup {

    //各タブの起動するアクティビティを保持したクラス
    private Class classlist[] = {
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
            Table_amnjk.class,
    };

    //各タブに割り当てられたアクティビティを起動するためのインテント群
    private ArrayList<Intent> intentList = new ArrayList<Intent>();

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

        //タブホストのインスタンス化
        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup(this.getLocalActivityManager());

        //タブに作品名を割り当てる
        TabHost.TabSpec spec;
        Intent i;
        for(int it = 0; it < classlist.length; it++) {

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
    }
}
