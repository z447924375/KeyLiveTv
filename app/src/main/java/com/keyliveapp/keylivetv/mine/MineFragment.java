package com.keyliveapp.keylivetv.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.login.LoginActivity;
import com.keyliveapp.keylivetv.login.SendLoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/10/22.
 */

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout login;
    private ImageView setting;
    private ImageView personal;
    private TextView pllogin;

    @Override
    protected int setLayout() {
        return R.layout.mine_fragment;
    }

    @Override
    protected void initView() {
        login = getViewLayout(R.id.rv_mine_login);
        setting = getViewLayout(R.id.iv_login_setting);
        personal = getViewLayout(R.id.iv_mine_personal);
        pllogin = getViewLayout(R.id.tv_mine_pllogin);

    }

    @Override
    protected void initDate() {
        try {
            EventBus.getDefault().register(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        login.setOnClickListener(this);
        setting.setOnClickListener(this);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getLoginEvent(SendLoginEvent event){
        String icon = event.getIcon();
        String name = event.getName();
        Log.d("MineFragment", icon + name);
        Glide.with(getContext()).load(icon).bitmapTransform(new CropCircleTransformation(getContext())).into(personal);
        pllogin.setText(name);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
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
