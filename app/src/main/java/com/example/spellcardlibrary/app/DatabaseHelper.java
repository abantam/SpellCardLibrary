package com.example.spellcardlibrary.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //データベース名
    private final static String DB_NAME = "database.db";

    //データベースのコピー先のパス
    private final static String DB_PATH = "/data/data/com.example.spellcardlibrary.app/database/";

    //データベースのオブジェクトを格納するフィールド
    private SQLiteDatabase mDatabase;

    //データベースのバージョン
    private final static int DB_VERSION = 1;

    //ヘルパークラスのコンストラクタを呼び出す
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
