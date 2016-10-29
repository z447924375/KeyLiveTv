package com.keyliveapp.keylivetv.discovery.nearby;

import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;

/**
 * Created by dllo on 16/10/28.
 */
public class NearbyFragment extends BaseFragment {
    private RecyclerView rv;
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


    }
}
