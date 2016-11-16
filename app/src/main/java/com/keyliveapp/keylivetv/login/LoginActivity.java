package com.keyliveapp.keylivetv.login;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

/**
 * Created by dllo on 16/11/14.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener{
    private ImageView back;
    private EditText phonenum;
    private EditText password;
    private TextView reset;
    private Button login;
    private Button register;
    private ImageView tx;
    private ImageView sn;
    private int number, code;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


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
        tx = bindView(R.id.iv_login_qq);
        sn = bindView(R.id.tv_login_weibo);


    }

    @Override
    protected void inidate() {



        back.setOnClickListener(this);
        reset.setOnClickListener(this);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        tx.setOnClickListener(this);
        sn.setOnClickListener(this);
        phonenum.addTextChangedListener(num);
        password.addTextChangedListener(word);

    }
    TextWatcher num = new TextWatcher() {
        private CharSequence phone;
        private int numStart;
        private int numEnd;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            phone = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            numStart = phonenum.getSelectionStart();
            numEnd = phonenum.getSelectionEnd();
            number = phone.length();
            if (number == 11 && code > 6) {
                login.setTextColor(Color.BLACK);
                login.setBackgroundColor(Color.WHITE);
            } else {
                    login.setTextColor(Color.WHITE);
                    login.setBackgroundColor(Color.GRAY);
                }
            }

    };
    TextWatcher word = new TextWatcher() {
        private CharSequence pass;
        private int wordStart;
        private int wortEnd;
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            pass = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            wordStart = password.getSelectionStart();
            wortEnd = password.getSelectionEnd();
            code = pass.length();
            if (number == 11 && code > 6) {
                login.setTextColor(Color.BLACK);
                login.setBackgroundColor(Color.WHITE);
            } else {
                login.setTextColor(Color.WHITE);
                login.setBackgroundColor(Color.GRAY);
            }

        }
    };


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_login_back:
                finish();
                break;
            case R.id.tv_login_new:
                break;
            case R.id.btn_login_login:
                if (phonenum == null) {
                    number = 0;
                }
                if (number != 11) {
                    Toast.makeText(this, "手机号都是11位的,你是二货吗?", Toast.LENGTH_SHORT).show();
                }
                if (code < 6) {
                    Toast.makeText(this, "请输入至少6位字符", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_login_register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_login_qq:
                ShareSDK.initSDK(this,"sharesdk的appkey");
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                qq.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getBaseContext(), "登录失败", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        PlatformDb platDB = arg0.getDb();//获取数平台数据DB

                        SendLoginEvent event = new SendLoginEvent();
                        event.setIcon(platDB.getUserIcon());
                        event.setName(platDB.getUserName());
                        EventBus.getDefault().post(event);

//                        Intent intent = new Intent();
//                        intent.putExtra(USER_NAME, platDB.getUserName());
//                        intent.putExtra(USER_ICON, platDB.getUserIcon());
//                        getBaseContext().sendBroadcast(intent);

                        Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub
                        Toast.makeText(getBaseContext(), "取消登录", Toast.LENGTH_SHORT).show();
                    }
                });
//authorize与showUser单独调用一个即可
                qq.authorize();//单独授权,OnComplete返回的hashmap是空的
                qq.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;
            case R.id.tv_login_weibo:
                ShareSDK.initSDK(this,"sharesdk的appkey");
                Platform weibo = ShareSDK.getPlatform(SinaWeibo.NAME);
//回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                weibo.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        PlatformDb platDB = arg0.getDb();//获取数平台数据DB

                        SendLoginEvent event = new SendLoginEvent();
                        event.setIcon(platDB.getUserIcon());
                        event.setName(platDB.getUserName());
                        EventBus.getDefault().post(event);

//                        Intent intent = new Intent();
//                        intent.putExtra(USER_NAME, platDB.getUserName());
//                        intent.putExtra(USER_ICON, platDB.getUserIcon());
//                        getBaseContext().sendBroadcast(intent);

                        Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
//authorize与showUser单独调用一个即可
                weibo.authorize();//单独授权,OnComplete返回的hashmap是空的
                weibo.showUser(null);//授权并获取用户信息
//移除授权
//weibo.removeAccount(true);
                break;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
