package com.keyliveapp.keylivetv.discovery.hottest;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.HottestBean;
import com.keyliveapp.keylivetv.discovery.hottest.HottestRvAdapter;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class HottestFragment extends BaseFragment {
    private RecyclerView rv;
    private String url;
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
        url = URLvalues.DISCOVERY_URL_HOTTEST;

        HttpManager.getInstance().getRequest(url, HottestBean.class, new OnCompletedListener<HottestBean>() {
            @Override
            public void onCompleted(HottestBean result) {
                bean = result;
                HottestRvAdapter hottestRvAdapter = new HottestRvAdapter(getActivity());
                hottestRvAdapter.setBean(bean);
                rv.setAdapter(hottestRvAdapter);
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
