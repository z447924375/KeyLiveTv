package com.keyliveapp.keylivetv.livetv.normal;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.values.URLvalues;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/7.
 */
public class LiveBoardFragment extends BaseFragment {
    private ViewPager liveBoardVp;
    private TabLayout liveBoardTab;



    @Override
    protected int setLayout() {
        return R.layout.live_board;
    }

    @Override
    protected void initView() {
        liveBoardVp = getViewLayout(R.id.live_board_vp);
        liveBoardTab = getViewLayout(R.id.live_board_tab);
    }

    @Override
    protected void initDate() {
        Bundle bundleReceive = getArguments();
        String roomid = bundleReceive.getString("roomid");

        Bundle bundleweek = new Bundle();
        bundleweek.putString("url", URLvalues.WEEK_URL_FRONT + roomid + URLvalues.BOARD_URL_BEHIND);

        Bundle bundleday = new Bundle();
        bundleday.putString("url",URLvalues.DAY_URL_FRONT + roomid + URLvalues.BOARD_URL_BEHIND);

        ArrayList<Fragment> fragments = new ArrayList<>();
        LiveBoardWeekFragment weekFragment = new LiveBoardWeekFragment();
        LiveBoardWeekFragment dayFragment = new LiveBoardWeekFragment();
        weekFragment.setArguments(bundleweek);
        dayFragment.setArguments(bundleday);
        fragments.add(weekFragment);
        fragments.add(dayFragment);


        LiveBoardVpAdapter adapter = new LiveBoardVpAdapter(getChildFragmentManager(), fragments);
        liveBoardVp.setAdapter(adapter);
        liveBoardTab.setupWithViewPager(liveBoardVp);




    }

    public class LiveBoardVpAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments = new ArrayList<>();
        private ArrayList<String> titles = new ArrayList<>();

        public LiveBoardVpAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
            titles.add("周榜");
            titles.add("日榜");

        }

        public LiveBoardVpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments == null ? 0 : fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

    }

}
