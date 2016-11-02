package com.keyliveapp.keylivetv.classify.zxh;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

import java.util.ArrayList;
import java.util.List;

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
    private ArrayList<String> viewers;
    private ArrayList<String> names;
    private ArrayList<String> titles;

    private PullToRefreshAdapter pullToRefreshAdapter;
    private String gameId;
    private ImageView imgLoading;
    private AnimationDrawable animationDrawable;
    private LinearLayout classi_ff;

    private android.os.Handler handler;
    private TextView tvLoading;

    @Override
    protected int setLayout() {
        return R.layout.classify_clickin;
    }

    @Override
    protected void initView() {

        classi_ff = bindView(R.id.classify_ff);
        topTitle = bindView(R.id.classify_clickin_title);
        lvPull = bindView(R.id.lv_classify);
        imgLoading = bindView(R.id.img_classify_loading);
        tvLoading = bindView(R.id.tv_classify_loading);


//        lvPull.setMode(PullToRefreshBase.Mode.BOTH);

        lvPull.setMode(PullToRefreshBase.Mode.BOTH);
        lvPull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(final PullToRefreshBase<ListView> refreshView) {

                startIndes += 30;
                if (startIndes - endIndes > 0) {

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(ClassifyClickInActivity.this, "无资源", Toast.LENGTH_SHORT).show();
                            refreshView.onRefreshComplete();
                        }
                    },2000);
                    lvPull.destroyDrawingCache();

                } else {

                    String url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;

                    setOrChangeAdapter(url);

                }

            }
        });

    }

    @Override
    protected void inidate() {
        Intent intent = getIntent();

        gameId = intent.getExtras().getString("gameId");//url

        String titleStr = (String) intent.getExtras().get("name");
        topTitle.setText(titleStr);

        pullToRefreshAdapter = null;

        imgLoading.setImageResource(R.drawable.classif_loading);
        animationDrawable = (AnimationDrawable) imgLoading.getDrawable();
        animationDrawable.start();

        gameId = intent.getExtras().getString("gameId");
        String url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;
        setOrChangeAdapter(url);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        Log.d("ClassifyClickInActivity", "onResume");
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

                if (previews == null) {
                    previews = new ArrayList<>();
                    names = new ArrayList<>();
                    titles = new ArrayList<>();
                    viewers = new ArrayList<>();
                } else {
                    previews.clear();
                    names.clear();
                    titles.clear();
                    viewers.clear();
                }

                List<ClassfyAllBean.DataBean.ItemsBean> itemsBeans = result.getData().getItems();

                for (ClassfyAllBean.DataBean.ItemsBean itemsBean : itemsBeans) {
                    previews.add(itemsBean.getPreview());
                    names.add(itemsBean.getChannel().getName());
                    titles.add(itemsBean.getChannel().getStatus());
                    viewers.add(String.valueOf(itemsBean.getViewers()));
                }

                if (pullToRefreshAdapter == null) {
                    pullToRefreshAdapter = new PullToRefreshAdapter();
                    pullToRefreshAdapter.setPreviews(previews);
                    pullToRefreshAdapter.setNames(names);
                    pullToRefreshAdapter.setTitles(titles);
                    pullToRefreshAdapter.setViewers(viewers);
                    lvPull.setAdapter(pullToRefreshAdapter);

                    handler = new android.os.Handler(new android.os.Handler.Callback() {
                        @Override
                        public boolean handleMessage(Message msg) {
                            switch (msg.what) {
                                case 0:
                                    imgLoading.setImageResource(R.mipmap.img_loading_fail);
                                    tvLoading.setText("没有任何数据");
                                    break;
                                default:
//                                    animationDrawable.stop();
                                    classi_ff.setVisibility(View.GONE);
                                    break;
                            }
                            return false;
                        }
                    });
                    handler.sendEmptyMessageDelayed(endIndes, 0);

                } else {
                    pullToRefreshAdapter.setAll(previews, names, titles, viewers);
                    lvPull.onRefreshComplete();
                }
            }

        });
    }

}
