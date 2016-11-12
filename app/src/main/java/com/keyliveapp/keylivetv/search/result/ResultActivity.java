package com.keyliveapp.keylivetv.search.result;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.search.history.DBtools;
import com.keyliveapp.keylivetv.search.result.host.HostBean;
import com.keyliveapp.keylivetv.search.result.host.HostFragment;
import com.keyliveapp.keylivetv.search.result.viedo.ViedoBean;
import com.keyliveapp.keylivetv.search.result.viedo.ViedoFragment;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/9.
 */

public class ResultActivity extends BaseActivity implements View.OnClickListener {

    private TabLayout tbResult;
    private ViewPager vpResult;
    private List<BaseFragment> fragments;
    private List<String> titles;

    private String TABLE_NAME = "search_table";
    private String COLUMNS_NAME = "word";

    private String HOST_URL_BEFORE = "http://searchapi.plu.cn/api/search/room?gameId=0&pageIndex=0&pageSize=20&title=";
    private String HOST_URL_BEHIND = "&roomId=0&type=1&from=applive&version=3.7.0&device=4&packageId=1";

    private String VIEDO_URL_BEFORE = "http://searchapi.plu.cn/api/search/media?gameId=0&roomId=0&pageIndex=0&pageSize=20&sortStr=relate&title=";
    private String VIEDO_URL_BEHIND = "&from=qq&version=3.7.0&device=4&packageId=1";

    private HostBean hostBean;
    private ViedoBean viedoBean;
    private String word;
    private Button tvSearch;
    private EditText edSearch;
    private DBtools dbTools;

    @Override
    protected int setLayout() {
        return R.layout.search_result;
    }

    @Override
    protected void initView() {

        tbResult = (TabLayout) findViewById(R.id.tb_result);
        vpResult = (ViewPager) findViewById(R.id.vp_result);

        ImageView imgBack = (ImageView) findViewById(R.id.result_in_frame).findViewById(R.id.search_back);
//        imgBack.setTag(1);
        imgBack.setOnClickListener(this);

        tvSearch = (Button) findViewById(R.id.result_in_frame).findViewById(R.id.search_btn);
//        tvSearch.setTag(3);
        tvSearch.setOnClickListener(this);

        edSearch = (EditText) findViewById(R.id.result_in_frame).findViewById(R.id.search_ed);
        edSearch.setOnClickListener(this);

        dbTools = new DBtools(this);

        String DBname = "searchDB";

        dbTools.createOrOpenDatabase(DBname);

        dbTools.createOrOpenTable(TABLE_NAME);


    }

    @Override
    protected void inidate() {

        Intent intent = getIntent();
        word = intent.getStringExtra("utfWord");

        titles = new ArrayList<>();
        titles.add("主播");
        titles.add("视频");

        final String hostUr = HOST_URL_BEFORE + word + HOST_URL_BEHIND;

        fragments = new ArrayList<>();
        setData(hostUr, HostBean.class);
    }


    private void setData(final String hostUr, Class clazz) {
        HttpManager.getInstance().getRequest(hostUr, clazz, new OnCompletedListener() {

            private VpResultAdapter vpResultAdapter;

            @Override
            public void onCompleted(Object result) {
                if (hostBean == null) {
                    hostBean = (HostBean) result;
                    Log.d("ResultActivity", "hostBean.getTotalItems():" + hostBean.getTotalItems());
                    HostFragment hostFragment = new HostFragment();
                    hostFragment.setHostBean(hostBean,word);
                    hostFragment.setmContext(getBaseContext());
                    fragments.add(hostFragment);
                    String viedoUr = VIEDO_URL_BEFORE + word + VIEDO_URL_BEHIND;
                    setData(viedoUr, ViedoBean.class);
                } else if (viedoBean == null) {
                    viedoBean = (ViedoBean) result;
                    ViedoFragment viedoFragment = new ViedoFragment();
                    viedoFragment.setmContext(getBaseContext());
                    viedoFragment.setViedoBean(viedoBean, word);
                    fragments.add(viedoFragment);
                    if (vpResultAdapter == null) {
                        vpResultAdapter = new VpResultAdapter(getSupportFragmentManager());
                        vpResultAdapter.setTitles(titles);
                        vpResultAdapter.setFragments(fragments);
                        vpResult.setAdapter(vpResultAdapter);
                        tbResult.setupWithViewPager(vpResult);
                    } else {
                        vpResultAdapter.setFragments(fragments);
                        vpResultAdapter.notifyDataSetChanged();
                    }

                }
            }

            @Override
            public void onFailed() {

            }
        });
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
        switch (v.getId()) {
            case R.id.search_back:
                finish();
                break;
            case R.id.search_btn:
                /**
                 * ""空串
                 * null  new不存在使用的堆内存
                 */
                if (!TextUtils.isEmpty(edSearch.getText().toString())) {
                    word = edSearch.getText().toString();
                    dbTools.insert(TABLE_NAME, COLUMNS_NAME, word);
                    edSearch.setText(null);
                    word = getUTF8(word);

                    String hostUr = HOST_URL_BEFORE + word + HOST_URL_BEHIND;
                    fragments.clear();

                    hostBean = null;
                    viedoBean = null;
                    vpResult.removeAllViews();

                    setData(hostUr, HostBean.class);

                } else {
                    Log.d("ResultActivity", "空");
                    Toast.makeText(this, "搜索关键词不能为空", Toast.LENGTH_SHORT);
                }
                break;
        }
    }
}
