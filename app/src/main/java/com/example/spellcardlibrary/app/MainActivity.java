package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TextView;

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
        TabHost.TabSpec spec;
        for(String wn : workname) {
            spec = host.newTabSpec(wn);
            spec.setContent(R.id.test);
            spec.setIndicator(wn);
            host.addTab(spec);
        }

    }
}
