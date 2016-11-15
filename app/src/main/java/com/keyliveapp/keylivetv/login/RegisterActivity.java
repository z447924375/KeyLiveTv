package com.keyliveapp.keylivetv.login;

import android.view.View;
import android.widget.ImageView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;

/**
 * Created by dllo on 16/11/14.
 */

public class RegisterActivity extends BaseActivity {
    private ImageView back;
    @Override
    protected int setLayout() {
        return R.layout.register_activity;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.iv_register_back);
    }

    @Override
    protected void inidate() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
