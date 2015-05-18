package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class Credit extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        TextView version = (TextView)findViewById(R.id.version);

    }


}
