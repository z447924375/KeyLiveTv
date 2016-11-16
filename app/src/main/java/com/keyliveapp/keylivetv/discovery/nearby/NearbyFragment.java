package com.keyliveapp.keylivetv.discovery.nearby;

import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DiscoveryBean;
import com.keyliveapp.keylivetv.livetv.StartVideoViewPlayer;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class NearbyFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    private DiscoveryBean bean;
    @Override
    protected int setLayout() {
        return R.layout.discovery_nearby;
    }

    @Override
    protected void initView() {
        mRecyclerView = getViewLayout(R.id.rv_discovery_nearby);

    }

    @Override
    protected void initDate() {
        HttpManager.getInstance().getRequest(URLvalues.DISCOVERY_URL_NEARBY, DiscoveryBean.class, new OnCompletedListener<DiscoveryBean>() {
            @Override
            public void onCompleted(final DiscoveryBean result) {
                bean = result;
                NearbyRvAdapter nearbyRvAdapter = new NearbyRvAdapter(getActivity());
                nearbyRvAdapter.setBean(bean);
                mRecyclerView.setAdapter(nearbyRvAdapter);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);

                nearbyRvAdapter.setNearbyItemClickListener(new NearbyRvAdapter.OnNearbyItemClickListener() {
                    @Override
                    public void nearbyClick(final int position) {
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String domain = result.getData().getStreams().getItems().get(position).getChannel().getDomain();

                                StartVideoViewPlayer.getInstance(getContext()).startBroadCast(domain);

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
