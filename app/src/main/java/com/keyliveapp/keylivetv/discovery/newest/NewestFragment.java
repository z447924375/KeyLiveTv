package com.keyliveapp.keylivetv.discovery.newest;

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
public class NewestFragment extends BaseFragment {
    private RecyclerView rv;
    @Override
    protected int setLayout() {
        return R.layout.discovery_newest;
    }

    @Override
    protected void initView() {
        rv = getViewLayout(R.id.rv_discovery_newest);

    }

    @Override
    protected void initDate() {
        HttpManager.getInstance().getRequest(URLvalues.DISCOVERY_URL_NEWEST, DiscoveryBean.class, new OnCompletedListener<DiscoveryBean>() {

            private DiscoveryBean bean;

            @Override
            public void onCompleted(DiscoveryBean result) {
                bean = result;
                NewestRvAdapter adapter = new NewestRvAdapter(getActivity());
                adapter.setBean(bean);
                rv.setAdapter(adapter );
                adapter.setBean(bean);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);
            }

            @Override
            public void onFailed() {

            }
        });


    }
}
