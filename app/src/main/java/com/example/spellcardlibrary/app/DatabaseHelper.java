package com.example.spellcardlibrary.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 参考文献：http://y-anz-m.blogspot.jp/2011/01/android-sqline-database.html
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    //データベース名
    private final static String DB_NAME = "database";

    //assetに格納されているデータベース名
    private final static String DB_NAME_ASSET = "database.db";

    //データベースのコピー先のパス
    private final static String DB_PATH = "/data/data/com.example.spellcardlibrary.app/database/";

    //データベースのオブジェクトを格納するフィールド
    private SQLiteDatabase mDatabase;

    //データベースのバージョン
    private final static int DB_VERSION = 17;

    private Context mContext;
    private File mDatabasePath;

    //ヘルパークラスのコンストラクタを呼び出す
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mContext = context;
        mDatabasePath = mContext.getDatabasePath(DB_NAME);
    }

    //assetに格納されたデータベースをコピーするため空のデータベースを作成する。
    public void createEmptyDatabase() throws IOException {
        boolean dbExist = checkDatabaseExists();
        if(dbExist); //すでにデータベースは作成されている
        else {
            getReadableDatabase(); //データベースをオープンする
            try {
                copyDatabaseFromAsset();
                String dbPath = mDatabasePath.getAbsolutePath();
                SQLiteDatabase checkDb = null;
                try {
                    checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
                }catch(SQLiteException e) {
                    //何らかの理由でデータベースがオープンできない
                }
                if(checkDb != null) {
                    checkDb.setVersion(DB_VERSION);
                    checkDb.close();
                }
            } catch(IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    //再コピーを防ぐためにすでにデータベースがあるかどうか判定する。
    private boolean checkDatabaseExists() {

        //データベースのパスを取得
        String dbPath = mDatabasePath.getAbsolutePath();

        SQLiteDatabase checkDb = null;
        try {
            //checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e) {
            //何らかの理由でデータベースがオープンできない
        }

        //データベースはまだ存在しない
        if(checkDb == null) return false;
        else return true;

//        int oldVersion = checkDb.getVersion();
//        int newVersion = DB_VERSION;
//
//        //データベースは存在していて最新
//        if(oldVersion == newVersion) {
//            checkDb.close();
//            return true;
//        }else {
//            //データベースは存在しているが最新ではないので削除
//            File f = new File(dbPath);
//            f.delete();
//            return false;
//        }
    }

    private void copyDatabaseFromAsset() throws IOException {
        // asset 内のデータベースファイルにアクセス
        InputStream mInput = mContext.getAssets().open(DB_NAME_ASSET);

        // デフォルトのデータベースパスに作成した空のDB
        OutputStream mOutput = new FileOutputStream(mDatabasePath);

        // コピー
        byte[] buffer = new byte[1024];
        int size;
        while ((size = mInput.read(buffer)) > 0) {
            mOutput.write(buffer, 0, size);
        }

        // Close the streams
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    //データベースをオープンする。オープンできない場合はSQLiteExceptionを投げる。
    public SQLiteDatabase openDatabase() throws SQLiteException {
        return getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

