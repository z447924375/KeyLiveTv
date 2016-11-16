package com.keyliveapp.keylivetv.classify;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.bean.ClassfyAllBean;
import com.keyliveapp.keylivetv.search.history.SearchActivity;
import com.keyliveapp.keylivetv.livetv.StartVideoViewPlayer;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

import java.util.ArrayList;
import java.util.List;

public class ClassifyClickInActivity extends BaseActivity implements View.OnClickListener {
    private TextView topTitle;
    private PullToRefreshListView lvPull;
    private ImageButton btnBack;
    private int startIndes = 0;
    private int endIndes = 0;
    private String URL_BEFORE1 = "";
    private String URL_BEFORE2 = "";
    private String URL_BEHIND = "";

    private ArrayList<ClassfyAllBean> mClassfyAllBeen = new ArrayList<>();
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
    private ImageButton imageButton;

    @Override
    protected int setLayout() {
        return R.layout.classify_clickin;
    }

    @Override
    protected void initView() {
        imageButton = (ImageButton) findViewById(R.id.fram_in_classif).findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
        classi_ff = bindView(R.id.classify_ff);
        topTitle = bindView(R.id.classify_clickin_title);
        lvPull = bindView(R.id.lv_classify);
        imgLoading = bindView(R.id.img_classify_loading);
        tvLoading = bindView(R.id.tv_classify_loading);
        btnBack = bindView(R.id.btn_back);


        lvPull.setMode(PullToRefreshBase.Mode.BOTH);
        lvPull.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(final PullToRefreshBase<ListView> refreshView) {
                startIndes = 0;
                endIndes = 0;
                int xxx = 0;

                String url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;


                pullToRefreshAdapter = null;

                previews.clear();
                names.clear();
                titles.clear();
                viewers.clear();
                setOrChangeAdapter(url);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.onRefreshComplete();

                    }
                }, 1000);

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
                    }, 2000);
                    lvPull.destroyDrawingCache();

                } else {

                    String url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;

                    Log.d("ClassifyClickInActivity", url);
                    setOrChangeAdapter(url);

                }

            }
        });


    }

    @Override
    protected void inidate() {
        Intent intent = getIntent();

        gameId = intent.getExtras().getString("gameid");
        String titleStr = (String) intent.getExtras().get("title");
        topTitle.setText(titleStr);

        pullToRefreshAdapter = null;

        /**
         * 动画
         */
        imgLoading.setImageResource(R.drawable.classif_loading);
        animationDrawable = (AnimationDrawable) imgLoading.getDrawable();
        animationDrawable.start();

        /**
         * 接收Url
         */
        gameId =
                intent.getExtras().getString("gameid");
        URL_BEFORE1 =
                intent.getExtras().getString("urlbefore1");
        URL_BEFORE2 =
                intent.getExtras().getString("urlbefore2");
        URL_BEHIND =
                intent.getExtras().getString("urlbehind");
        String url = "";
        if (null == URL_BEFORE1 || null == URL_BEFORE2 || null == URL_BEHIND) {
            url = "https://a4.plu.cn/api/matches?start-index=0&max-results=200&version=3.7.0&device=4&packageId=1";
            imgLoading.setImageResource(R.mipmap.img_loading_fail);
            tvLoading.setText("没有任何数据");
        } else {
            url = URL_BEFORE1 + startIndes + URL_BEFORE2 + gameId + URL_BEHIND;
        }
        setOrChangeAdapter(url);    // 开启网络请求

        btnBack.setOnClickListener(this);

    }


    private void setOrChangeAdapter(String url) {

        HttpManager.getInstance().getRequest(url, ClassfyAllBean.class, new OnCompletedListener<ClassfyAllBean>() {
            @Override
            public void onCompleted(ClassfyAllBean result) {
                mClassfyAllBeen.add(mClassfyAllBeen.size(), result);
                setData(result);
            }

            @Override
            public void onFailed() {
                Toast.makeText(ClassifyClickInActivity.this, "解析失败", Toast.LENGTH_SHORT).show();
            }


            private void setData(final ClassfyAllBean result) {

                endIndes = result.getData().getTotalItems();

                if (previews == null) {
                    previews = new ArrayList<>();
                    names = new ArrayList<>();
                    titles = new ArrayList<>();
                    viewers = new ArrayList<>();
                } else {

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
                                    classi_ff.setVisibility(View.GONE);
                                    break;
                            }
                            return false;
                        }
                    });
                    handler.sendEmptyMessageDelayed(endIndes, 0);

                } else {
                    pullToRefreshAdapter.notifyDataSetChanged();
                    lvPull.onRefreshComplete();
                }
                pullToRefreshAdapter.setClicked(new PullToRefreshAdapter.Clicked() {
                    @Override
                    public void click(int position, Context context) {
                        String domain = mClassfyAllBeen.get(position / 30).getData().getItems().get(position % 30).getChannel().getDomain();

                        StartVideoViewPlayer.getInstance(context).startBroadCast(domain);

                    }
                });

            }

        });

    }



    @Override
    public void onClick(View v) {
        this.finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
