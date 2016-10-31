package com.keyliveapp.keylivetv.classify.zxh;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class ClassifyClickInActivity extends BaseActivity {
    private TextView topTitle;
    private RecyclerView mRecyclerView;
    private PullToRefreshListView lvPull;

    private String URL_BEFORE1 = "https://a4.plu.cn/api/streams?start-index=";
    private int startIndes = 0;
    private int endIndes = 0;
    private String URL_BEFORE2 = "&max-results=30&game=";
    private String URL_BEHIND = "&version=3.7.0&device=4&packageId=1";
    private ArrayList<String> previews;
    private PullToRefreshAdapter pullToRefreshAdapter;
    private String gameId;

    @Override
    protected int setLayout() {
        return R.layout.classify_clickin;
    }

    @Override
    protected void initView() {

        title = bindView(R.id.classify_clickin_title);
        lvPull = bindView(R.id.lv_classify);
//        lvPull.setMode(PullToRefreshBase.Mode.BOTH);

        lvPull.setMode(PullToRefreshBase.Mode.BOTH);
        lvPull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

                startIndes += 30;
                if (startIndes - endIndes > 0) {

//                    lvPull.onRefreshComplete();
                    lvPull.destroyDrawingCache();
//                    pullToRefreshAdapter.notifyDataSetChanged();

                } else {

                    String url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;

                    setOrChangeAdapter(url);

                }

            }
        });

    }

    @Override
    protected void inidate() {

        gameId = intent.getExtras().getString("gameId");

        String titleStr = (String) intent.getExtras().get("name");
        title.setText(titleStr);

        String url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;

        pullToRefreshAdapter = null;

        Log.d("ClassifyClickInActivity", url);

        setOrChangeAdapter(url);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("ClassifyClickInActivity", "onResume");
    }

    private void setOrChangeAdapter(String url) {
        HttpManager.getInstance().getRequest(url, ClassfyAllBean.class, new OnCompletedListener<ClassfyAllBean>() {
            @Override
            public void onCompleted(ClassfyAllBean result) {

                setData(result);

            }


            @Override
            public void onFailed() {
                Toast.makeText(ClassifyClickInActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
            }


            private void setData(ClassfyAllBean result) {

                endIndes = result.getData().getTotalItems();
                Log.d("ClassifyClickInActivity", "endIndes:" + endIndes);

                previews = new ArrayList<>();

                for (ClassfyAllBean.DataBean.ItemsBean itemsBean : result.getData().getItems()) {
                    previews.add(itemsBean.getPreview());
                }

                if (pullToRefreshAdapter == null) {
                    pullToRefreshAdapter = new PullToRefreshAdapter();
                    pullToRefreshAdapter.setPreviews(previews);
                    lvPull.setAdapter(pullToRefreshAdapter);
                } else {
                    pullToRefreshAdapter.setAll(previews);
                    lvPull.onRefreshComplete();
                }
            }

        });
    }

}
