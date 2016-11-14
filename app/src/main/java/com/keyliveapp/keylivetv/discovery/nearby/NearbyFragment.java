package com.keyliveapp.keylivetv.discovery.nearby;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DiscoveryBean;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class NearbyFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    private DiscoveryBean mDiscoveryBean;
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
            public void onCompleted(DiscoveryBean result) {
                mDiscoveryBean = result;
                NearbyRvAdapter nearbyRvAdapter = new NearbyRvAdapter(getActivity());
                nearbyRvAdapter.setBean(mDiscoveryBean);
                mRecyclerView.setAdapter(nearbyRvAdapter);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                mRecyclerView.setLayoutManager(manager);

            }

            @Override
            public void onFailed() {

            }
        });



    }


}
