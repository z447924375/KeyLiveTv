package com.keyliveapp.keylivetv.main;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.classify.ClassifyFragment;
import com.keyliveapp.keylivetv.discovery.DiscoveryFragment;
import com.keyliveapp.keylivetv.home.HomeFragment;
import com.keyliveapp.keylivetv.mine.MineFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private ViewPager lzViewPager;
    private TabLayout lzTabLayout;

    @Override
    protected int setlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        lzViewPager = bindView(R.id.vp_longzhu);
        lzTabLayout = bindView(R.id.tb_longzhu);
    }

    @Override
    protected void inidate() {

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new ClassifyFragment());
        fragments.add(new DiscoveryFragment());
        fragments.add(new MineFragment());

        MainVpAdapter adapter=new MainVpAdapter(getSupportFragmentManager(),fragments);
        lzViewPager.setAdapter(adapter);
        lzTabLayout.setupWithViewPager(lzViewPager);

        lzTabLayout.getTabAt(0).setIcon(R.drawable.home);
        lzTabLayout.getTabAt(1).setIcon(R.drawable.classify);
        lzTabLayout.getTabAt(2).setIcon(R.drawable.discovery);
        lzTabLayout.getTabAt(3).setIcon(R.drawable.mine);



    }

}
