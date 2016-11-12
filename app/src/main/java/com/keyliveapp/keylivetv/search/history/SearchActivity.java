package com.keyliveapp.keylivetv.search.history;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.search.result.ResultActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by dllo on 16/11/2.
 */

public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imgBack;
    private ListView searchLv;
    private String TABLE_NAME = "search_table";
    private String COLUMNS_NAME = "word";
    private TextView tvSearch;
    private DBtools dbTools;
    //    private Button dropBtn;
//    private Button updateBtn;
//    private Button deleteBtn;
//    private Button insertBtn;
//    private Button rawQueueBtn;
//    private Button createTableBtn;
    private EditText edSearch;
    private HistoryAdapter historyAdapter;
    private ArrayList<String> words;


    @Override
    protected int setLayout() {
        return R.layout.search_activity;
    }

    @Override
    protected void initView() {

//        insertBtn = (Button) findViewById(R.id.insert_btn);
//        insertBtn.setOnClickListener(this);
//        deleteBtn = (Button) findViewById(R.id.delete_btn);
//        deleteBtn.setOnClickListener(this);
//        updateBtn = (Button) findViewById(R.id.update_btn);
//        updateBtn.setOnClickListener(this);
//        dropBtn = (Button) findViewById(R.id.drop_btn);
//        dropBtn.setOnClickListener(this);
//        createTableBtn = (Button) findViewById(R.id.create_table_btn);
//        createTableBtn.setOnClickListener(this);
//        rawQueueBtn = (Button) findViewById(R.id.rawqueue_btn);
//        rawQueueBtn.setOnClickListener(this);


        imgBack = (ImageView) findViewById(R.id.history_in_frame).findViewById(R.id.search_back);
//        imgBack.setTag(1);
        imgBack.setOnClickListener(this);

        tvSearch = (TextView) findViewById(R.id.history_in_frame).findViewById(R.id.search_btn);
//        tvSearch.setTag(3);
        tvSearch.setOnClickListener(this);

        edSearch = (EditText) findViewById(R.id.history_in_frame).findViewById(R.id.search_ed);
        edSearch.setOnClickListener(this);


        searchLv = bindView(R.id.search_lv);

        dbTools = new DBtools(this);

        String DBname = "searchDB";

        dbTools.createOrOpenDatabase(DBname);

        dbTools.createOrOpenTable(TABLE_NAME);


    }


    @Override
    protected void inidate() {

        words = dbTools.rawQueue(TABLE_NAME);
        historyAdapter = new HistoryAdapter();
        historyAdapter.setWords(words);
        searchLv.setAdapter(historyAdapter);

        searchLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String word = words.get(position);
                dbTools.insert(TABLE_NAME, COLUMNS_NAME, word);
                edSearch.setText(null);
                String utfWord = getUTF8(word);
                Intent intent = new Intent(parent.getContext(), ResultActivity.class);
                intent.putExtra("utfWord", utfWord);
                startActivity(intent);
            }
        });

        searchLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String word = words.get(position);
                dbTools.delete(TABLE_NAME, COLUMNS_NAME, word);
                historyAdapter.delete(position);
                return true;
            }
        });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        words = dbTools.rawQueue(TABLE_NAME);
        historyAdapter.setWords(words);
        historyAdapter.notifyDataSetChanged();
    }
    public String getUTF8(String str) {
        String utf8 = null;
        try {
            utf8 = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return utf8;
    }

    @Override
    public void onClick(View v) {
//        int id = (int) v.getTag();

        switch (v.getId()) {
            /**
             * 回退图标
             */
            case R.id.search_back:
                finish();
                break;
            /**
             * 搜索
             */
            case R.id.search_btn:
                String word = null;
                /**
                 * ""空串
                 * null  new不存在使用的堆内存
                 */
                if (!TextUtils.isEmpty(edSearch.getText().toString())) {
                    word = edSearch.getText().toString();
                    dbTools.insert(TABLE_NAME, COLUMNS_NAME, word);
                    edSearch.setText(null);
                    String utfWord = getUTF8(word);
                    Intent intent = new Intent(this, ResultActivity.class);
                    intent.putExtra("utfWord", utfWord);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "搜索关键词不能为空", Toast.LENGTH_SHORT);
                }
                break;

//            case R.id.insert_btn:
//                dbTools.insert(TABLE_NAME, COLUMNS_NAME, "丫头");
//                dbTools.insert(TABLE_NAME, COLUMNS_NAME, "刺猬");
//                break;
//            case R.id.delete_btn:
//                dbTools.delete(TABLE_NAME, COLUMNS_NAME, "刺猬");
//                break;
//            case R.id.update_btn:
//                BaseToast.showToast(this, "修改");
//                break;
//            case R.id.drop_btn:
//                dbTools.dropTable(TABLE_NAME);
//                break;
//            case R.id.create_table_btn:
//                dbTools.createOrOpenTable(TABLE_NAME);
//                break;
//            case R.id.rawqueue_btn:
//                words = dbTools.rawQueue(TABLE_NAME);
//                historyAdapter.setWords(words);
//                historyAdapter.notifyDataSetChanged();
//                break;
        }

    }
}
