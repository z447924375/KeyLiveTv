package com.keyliveapp.keylivetv.home;


import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.baseclass.MyApp;

/**
 * Created by dllo on 16/10/22.
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView mHomeRecycler;
    private HomeBean mHomeBean;

    @Override
    protected int setLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {
        mHomeRecycler = getViewLayout(R.id.home_recyclerview);
    }

    @Override
    protected void initDate() {
        HomeRvAdapter adapter=new HomeRvAdapter(MyApp.getContext());

    }
}
