package com.keyliveapp.keylivetv.discovery.hottest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.HottestBean;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class HottestFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private HottestBean mHottestBean;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected int setLayout() {
        return R.layout.discovery_hottest;
    }

    @Override
    protected void initView() {
        mRecyclerView = getViewLayout(R.id.rv_discovery_hottest);
        mSwipeRefreshLayout = getViewLayout(R.id.srfl_discovery_hottest);

    }

    @Override
    protected void initDate() {
        HttpManager.getInstance().getRequest(URLvalues.DISCOVERY_URL_HOTTEST, HottestBean.class, new OnCompletedListener<HottestBean>() {
            @Override
            public void onCompleted(HottestBean result) {
                mHottestBean = result;
                HottestRvAdapter hottestRvAdapter = new HottestRvAdapter(getActivity());
                hottestRvAdapter.setmHottestBean(mHottestBean);
                mRecyclerView.setAdapter(hottestRvAdapter);
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
