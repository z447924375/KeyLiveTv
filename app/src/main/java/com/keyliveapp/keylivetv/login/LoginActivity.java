package com.keyliveapp.keylivetv.login;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;

/**
 * Created by dllo on 16/11/14.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private EditText phonenum;
    private EditText password;
    private TextView reset;
    private Button login;
    private Button register;
    private ImageView qq;
    private ImageView weibo;

    @Override
    protected int setLayout() {
        return R.layout.login_activity;
    }

    @Override
    protected void initView() {
        back = bindView(R.id.iv_login_back);
        phonenum = bindView(R.id.et_login_phone);
        password = bindView(R.id.et_login_password);
        reset = bindView(R.id.tv_login_new);
        login = bindView(R.id.btn_login_login);
        register = bindView(R.id.btn_login_register);
        qq = bindView(R.id.iv_login_qq);
        weibo = bindView(R.id.tv_login_weibo);

    }

    @Override
    protected void inidate() {
        back.setOnClickListener(this);
        phonenum.setOnClickListener(this);
        password.setOnClickListener(this);
        reset.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        qq.setOnClickListener(this);
        weibo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_back :
                
                finish();
                break;
            case R.id.et_login_phone :
                break;
            case R.id.et_login_password :
                break;
            case R.id.tv_login_new :
                break;
            case R.id.btn_login_login :
                break;
            case R.id.btn_login_register :
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_login_qq :
                break;
            case R.id.tv_login_weibo :
                break;
        }
    }
}
