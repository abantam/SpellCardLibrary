package com.example.spellcardlibrary.app;

import android.app.Activity;
import android.app.ListFragment;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*FragmentからActivityにコールバックする方法：http://y-anz-m.blogspot.jp/2012/06/fragment-activity.html*/

public class BaseTable extends ListFragment {

    private ListView mainView;//リストビュー
    private static SQLiteDatabase db;//データベースを格納
    private DatabaseHelper mDbHelper;
    private ArrayList<HashMap<String, String>> scList = new ArrayList<HashMap<String, String>>();
    private String title;//作品名

    public interface OnOkBtnClickListener {
        public void onOkClicked();
    }

    private OnOkBtnClickListener mListener;

    public BaseTable() {}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if(activity instanceof OnOkBtnClickListener == false) {
            throw new ClassCastException();
        }

        mListener = ((OnOkBtnClickListener) activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scList = new ArrayList<HashMap<String, String>>();
        //setDatabase();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //データベースを操作するためのカーソルを作成
        Cursor c = db.query(title, null, null, null, null, null, null);
        c.moveToFirst();

        //カーソルを一つずづ動かして一時変数にデータを格納
        for(int i = 0; i < c.getCount(); i++, c.moveToNext()) {
            HashMap<String, String> temp = new HashMap<String, String>();
            temp.put("Number", c.getString(1));
            temp.put("scName", c.getString(2));
            scList.add(temp);
        }

        //アダプターにデータを格納してListViewにセット
        SimpleAdapter adapter = new SimpleAdapter(getActivity(), scList, R.layout.listview_layout,
                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
        setListAdapter(adapter);

        return inflater.inflate(R.layout.table, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        db.close();
        super.onDestroy();
    }


    //assetsからデータベースをコピー
    private void setDatabase() {
        mDbHelper = new DatabaseHelper(getActivity());
        try {
            mDbHelper.createEmptyDatabase();
            db = mDbHelper.openDatabase();
        }catch(IOException ioe) {
            throw new Error("Unable to create database");
        }catch(SQLException sqle) {
            throw sqle;
        }
    }

    //コピーしたデータベースからリストを作成
//    private void makeList() {
//
//        //インテントを取得したあと作品名を取得
////        Intent intent = getIntent();
////        workname = intent.getStringExtra("作品名");
//
//        //データベース内のカーソルを設定
//        Cursor c = db.query(workname, null, null, null, null, null, null);
//        c.moveToFirst();
//
//
//
//            //カーソルを1つずつ動かして一時変数にデータを格納
//            for (int i = 0; i < c.getCount(); i++, c.moveToNext()) {
//                HashMap<String, String> temp = new HashMap<String, String>();
//                if(workname.equals("東方萃夢想") || workname.equals("東方花映塚") || workname.equals("東方緋想天") || workname.equals("東方非想天則") || workname.equals("東方心綺楼")) {
//                    temp.put("Number", null);
//                    temp.put("scName", c.getString(1));
//                }else {
//                    temp.put("Number", c.getString(1));
//                    temp.put("scName", c.getString(2));
//                }
//
//                scList.add(temp);
//            }
//
//    }

    //リストビューをセットしてリストを表示
//    private void setMainView() {
//        mainView = (ListView)findViewById(R.id.mainView);
//        SimpleAdapter adapter = new SimpleAdapter(this, scList, R.layout.listview_layout,
//                new String[]{"Number", "scName"}, new int[]{R.id.number, R.id.scname});
//        mainView.setAdapter(adapter);
//
//        /*
//        端末の画面サイズに合わせてフォントサイズを最適化する
//        参考文献：http://cleanings.j
//         */
//        /*WindowManager wm = (WindowManager)getSystemService(WINDOW_SERVICE);
//        Display disp = wm.getDefaultDisplay();
//        float width = disp.getWidth();
//        float scale = width / 480;
//        TextView text = (TextView)findViewById(R.id.)*/
//
//        //リストビューを選択したときに画面遷移
//        mainView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(BaseTable.this, SCInfo.class);
//                i.putExtra("query", "SELECT * FROM " + workname + " WHERE _id = " + (position + 1));
//                startActivity(i);
//            }
//        });
//    }

    //データベースを返す
    public static SQLiteDatabase getDatabase() {
        return db;
    }

    //スクロール制御
    private void scrollController(TextView tv) {
        tv.setSingleLine();
        tv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String s) {
        title = s;
    }
}
