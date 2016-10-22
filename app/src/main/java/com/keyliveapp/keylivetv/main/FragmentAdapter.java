package com.keyliveapp.keylivetv.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.keyliveapp.keylivetv.baseclass.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/22.
 */
public class FragmentAdapter extends FragmentPagerAdapter{

    private ArrayList<BaseFragment> baseFragments;

    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    public void setBaseFragments(ArrayList<BaseFragment> baseFragments) {
        this.baseFragments = baseFragments;
    }
}
