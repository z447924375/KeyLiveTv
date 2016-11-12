package com.keyliveapp.keylivetv.search.result;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;

import java.util.List;

/**
 * Created by dllo on 16/11/9.
 */
public class VpResultAdapter extends FragmentStatePagerAdapter {

    private List<BaseFragment> fragments;
    private List<String> titles;
    private List<String> text;

    public void setText(List<String> text) {
        this.text = text;
    }
    public void text() {
        Log.d("VpResultAdapter", "text.size():" + text.size());
        Log.d("VpResultAdapter", "text:" + text);
    }

    public void setFragments(List<BaseFragment> fragments) {
        this.fragments = fragments;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public VpResultAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
