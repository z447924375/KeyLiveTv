package com.keyliveapp.keylivetv.search.history;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.keyliveapp.keylivetv.baseclass.BaseToast;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by dllo on 16/11/3.
 */

public class DBtools {

    private SQLiteDatabase searchDB;
    private Context mContext;

    public DBtools(Context context) {
        this.mContext = context;
    }

    public void createOrOpenDatabase(String DBname) {
        /**
         * 建数据库
         */

        searchDB = mContext.openOrCreateDatabase(DBname, MODE_PRIVATE, null);

    }

    public void createOrOpenTable(String tableName) {
        /**
         * 建表格
         */

        String createTable = "create table if not exists " + tableName + "(_id integer primary key autoincrement, word text)";

        searchDB.execSQL(createTable);
    }

    public void dropTable(String tableName) {
        /**
         * 删除表
         */

        String dropTable = "drop table if exists " + tableName;

        searchDB.execSQL(dropTable);
    }

    public void insert(String tableName, String columnsName, String word) {
        /**
         * 插入表
         */

        delete(tableName, columnsName, word);

        String insertExecSQL = "insert into " + tableName + "(" + columnsName + ") values(?)";

        searchDB.execSQL(insertExecSQL, new Object[]{word});

    }

    public void delete(String tableName, String columnsName, String word) {
        /**
         * 删除表内容
         */

        String deleteExecSQL = "delete from " + tableName + " where " + columnsName + " = ?";

        searchDB.execSQL(deleteExecSQL, new String[]{word});

        rawQueue();

    }

    public ArrayList<String> rawQueue(String tableName) {

        ArrayList<String> words = new ArrayList<>();

        Cursor cursor = searchDB.rawQuery("select * from " + tableName, null);

        if (0 != cursor.getCount()) {
            cursor.moveToLast();
            String wordd = cursor.getString(cursor.getColumnIndex("word"));
            words.add(wordd);
            int ids = cursor.getInt(cursor.getColumnIndex("_id"));
            while (cursor.moveToPrevious()) {
                String word = cursor.getString(cursor.getColumnIndex("word"));
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
                words.add(word);
            }
        } else {
            BaseToast.showToast(mContext, "表为空");
        }


        return words;
    }

    public void rawQueue() {

        Cursor cursor = searchDB.rawQuery("select * from search_table", null);

        if (0 != cursor.getCount()) {
            cursor.moveToLast();
            String words = cursor.getString(cursor.getColumnIndex("word"));
            int ids = cursor.getInt(cursor.getColumnIndex("_id"));
            while (cursor.moveToPrevious()) {
                String word = cursor.getString(cursor.getColumnIndex("word"));
                int id = cursor.getInt(cursor.getColumnIndex("_id"));
            }
        } else {
            BaseToast.showToast(mContext, "表为空");
        }
    }
}





