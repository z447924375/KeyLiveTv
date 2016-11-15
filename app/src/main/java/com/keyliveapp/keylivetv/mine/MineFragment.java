package com.keyliveapp.keylivetv.mine;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.mine.like.LikeActivity;

/**
 * Created by dllo on 16/10/22.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
    private RelativeLayout mineSubLayout;

    @Override
    protected int setLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView() {
        mineSubLayout = getViewLayout(R.id.iv_mine_sub_ll);

    }

    @Override
    protected void initDate() {
        mineSubLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LikeActivity.class);
        startActivity(intent);
    }
}
