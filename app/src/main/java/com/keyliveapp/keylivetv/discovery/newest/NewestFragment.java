package com.keyliveapp.keylivetv.discovery.newest;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.NewestBean;
import com.keyliveapp.keylivetv.discovery.nearby.NearbyRvAdapter;
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
        HttpManager.getInstance().getRequest(URLvalues.DISCOVERY_URL_NEWEST, NewestBean.class, new OnCompletedListener<NewestBean>() {

            private NewestBean bean;

            @Override
            public void onCompleted(NewestBean result) {
                bean = result;
                NearbyRvAdapter adapter = new NearbyRvAdapter(getActivity());


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
