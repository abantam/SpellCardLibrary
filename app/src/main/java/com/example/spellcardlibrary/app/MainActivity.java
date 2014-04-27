package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //タブホストのインスタンス化
        TabHost host = (TabHost)findViewById(R.id.tabhost);
        host.setup();

        //タブに作品名を割り当てる
        String[] workname = getResources().getStringArray(R.array.workname);
        String[] classlist = getResources().getStringArray(R.array.classlist);
        TabHost.TabSpec spec;
        Intent i;
        int j = 0;
        for(String wn : workname) {
            spec = host.newTabSpec(wn);
            spec.setContent(new Intent(this, classlist[j].getClass()));
            spec.setIndicator(wn);
            host.addTab(spec);
            j++;
        }



    }
}
