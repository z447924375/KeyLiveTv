package com.keyliveapp.keylivetv.main;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.classify.ClassifyFragment;
import com.keyliveapp.keylivetv.homepage.HomepageFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {


    private TabLayout tb_longzhu;
    private ViewPager vp_longzhu;

    @Override
    protected int setlayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tb_longzhu =
                bindView(R.id.tb_longzhu);
        vp_longzhu =
                bindView(R.id.vp_longzhu);
    }

    @Override
    protected void inidate() {
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager());

        ArrayList<BaseFragment> baseFragments = new ArrayList<>();
        baseFragments.add(new HomepageFragment());
        baseFragments.add(new ClassifyFragment());
        baseFragments.add(new HomepageFragment());
        baseFragments.add(new HomepageFragment());
        fragmentAdapter.setBaseFragments(baseFragments);

        vp_longzhu.setAdapter(fragmentAdapter);
        tb_longzhu.setupWithViewPager(vp_longzhu);



    }
}
