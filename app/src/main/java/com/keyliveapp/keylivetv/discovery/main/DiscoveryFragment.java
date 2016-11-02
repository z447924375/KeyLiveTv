package com.keyliveapp.keylivetv.discovery.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.discovery.hottest.HottestFragment;
import com.keyliveapp.keylivetv.discovery.nearby.NearbyFragment;
import com.keyliveapp.keylivetv.discovery.newest.NewestFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/22.
 */
public class DiscoveryFragment extends BaseFragment {
    private TabLayout discoveryLayout;
    private ViewPager discoveryPager;
    @Override
    protected int setLayout() {
        return R.layout.discovery_fragment;
    }

    @Override
    protected void initView() {
        discoveryLayout = getViewLayout(R.id.tb_discovery);
        discoveryPager = getViewLayout(R.id.vp_discovery);

    }

    @Override
    protected void initDate() {
        ArrayList<Fragment> arrayList = new ArrayList<>();
        arrayList.add(new NearbyFragment());
        arrayList.add(new HottestFragment());
        arrayList.add(new NewestFragment());

        DiscoveryVpAdapter discoveryVpAdapter = new DiscoveryVpAdapter(getChildFragmentManager(), arrayList);
        discoveryPager.setAdapter(discoveryVpAdapter);
        discoveryLayout.setupWithViewPager(discoveryPager);


    }
}
