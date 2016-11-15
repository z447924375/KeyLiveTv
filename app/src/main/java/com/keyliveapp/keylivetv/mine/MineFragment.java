package com.keyliveapp.keylivetv.mine;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.login.LoginActivity;

/**
 * Created by dllo on 16/10/22.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout login;
    private ImageView setting;

    @Override
    protected int setLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView() {
        login = getViewLayout(R.id.rv_mine_login);
        setting = getViewLayout(R.id.iv_login_setting);

    }

    @Override
    protected void initDate() {
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
    }
}
