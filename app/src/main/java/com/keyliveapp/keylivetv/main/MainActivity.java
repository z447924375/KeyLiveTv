package com.keyliveapp.keylivetv.main;


import android.support.design.widget.TabLayout;


import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
<<<<<<< HEAD
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.classify.ClassifyFragment;
import com.keyliveapp.keylivetv.homepage.HomepageFragment;

import java.util.ArrayList;
=======
import com.keyliveapp.keylivetv.classify.ClassifyFragment;
import com.keyliveapp.keylivetv.discovery.DiscoveryFragment;
import com.keyliveapp.keylivetv.home.HomeFragment;
import com.keyliveapp.keylivetv.mine.MineFragment;
>>>>>>> 99a59c5407fc6a86875816e9f3159f297407facb

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
//    private ViewPager lzViewPager;
//    private TabLayout lzTabLayout;

//    @BindView(R.id.vp_longzhu)ViewPager lzViewPager;
//    @BindView(R.id.tb_longzhu)TabLayout lzTabLayout;

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

//        ButterKnife

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
