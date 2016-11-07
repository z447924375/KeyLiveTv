package com.keyliveapp.keylivetv.discovery.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DiscoveryBean;
import com.keyliveapp.keylivetv.discovery.nearby.NearbyRvAdapter;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class NearbyFragment extends BaseFragment{
    private RecyclerView rv;
    private DiscoveryBean bean;
    private String url;
    @Override
    protected int setLayout() {
        return R.layout.discovery_nearby;
    }

    @Override
    protected void initView() {
        rv = getViewLayout(R.id.rv_discovery_nearby);

    }

    @Override
    protected void initDate() {
        url = URLvalues.DISCOVERY_URL_NEARBY;
        HttpManager.getInstance().getRequest(url, DiscoveryBean.class, new OnCompletedListener<DiscoveryBean>() {
            @Override
            public void onCompleted(DiscoveryBean result) {
                bean = result;
                NearbyRvAdapter nearbyRvAdapter = new NearbyRvAdapter(getActivity());
                nearbyRvAdapter.setBean(bean);
                rv.setAdapter(nearbyRvAdapter);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);

            }

            @Override
            public void onFailed() {

            }
        });



    }


}
