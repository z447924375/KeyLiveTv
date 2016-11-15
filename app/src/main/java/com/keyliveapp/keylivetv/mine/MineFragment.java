package com.keyliveapp.keylivetv.mine;

import android.content.Intent;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.ImageView;
>>>>>>> 24559ad6b34940606f7a6e139bf03d07d992eeb6
import android.widget.RelativeLayout;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
<<<<<<< HEAD
import com.keyliveapp.keylivetv.mine.like.LikeActivity;
=======
import com.keyliveapp.keylivetv.login.LoginActivity;
>>>>>>> 24559ad6b34940606f7a6e139bf03d07d992eeb6

/**
 * Created by dllo on 16/10/22.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {
<<<<<<< HEAD
    private RelativeLayout mineSubLayout;
=======

    private RelativeLayout login;
    private ImageView setting;
>>>>>>> 24559ad6b34940606f7a6e139bf03d07d992eeb6

    @Override
    protected int setLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView() {
<<<<<<< HEAD
        mineSubLayout = getViewLayout(R.id.iv_mine_sub_ll);
=======
        login = getViewLayout(R.id.rv_mine_login);
        setting = getViewLayout(R.id.iv_login_setting);
>>>>>>> 24559ad6b34940606f7a6e139bf03d07d992eeb6

    }

    @Override
    protected void initDate() {
<<<<<<< HEAD
        mineSubLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(getActivity(), LikeActivity.class);
        startActivity(intent);
=======
        login.setOnClickListener(this);
        setting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_mine_login :
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_login_setting :
                Intent intent1 = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent1);
                break;

        }
>>>>>>> 24559ad6b34940606f7a6e139bf03d07d992eeb6
    }
}
