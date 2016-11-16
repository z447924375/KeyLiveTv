package com.keyliveapp.keylivetv.discovery.hottest;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.HottestBean;
import com.keyliveapp.keylivetv.livetv.StartVideoViewPlayer;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class HottestFragment extends BaseFragment {
    private RecyclerView rv;
    private HottestBean bean;

    @Override
    protected int setLayout() {
        return R.layout.discovery_hottest;
    }

    @Override
    protected void initView() {
        rv = getViewLayout(R.id.rv_discovery_hottest);

    }

    @Override
    protected void initDate() {
        HttpManager.getInstance().getRequest(URLvalues.DISCOVERY_URL_HOTTEST, HottestBean.class, new OnCompletedListener<HottestBean>() {
            @Override
            public void onCompleted(final HottestBean result) {
                bean = result;
                HottestRvAdapter hottestRvAdapter = new HottestRvAdapter(getActivity());
                hottestRvAdapter.setBean(bean);
                rv.setAdapter(hottestRvAdapter);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);

                hottestRvAdapter.setHottestItemClickListener(new HottestRvAdapter.OnHottestItemClickListener() {
                    @Override
                    public void hottestClick(final int position) {
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String domain = result.getData().getItems().get(position).getChannel().getDomain();
                                Log.d("zzz", domain +"****"+ position);
                                StartVideoViewPlayer.getInstance(getContext()).startBroadCast(domain);
//                                startFullLiveTv(domain);
                            }
                        });
                    }
                });


            }

            @Override
            public void onFailed() {

            }
        });


    }



}
