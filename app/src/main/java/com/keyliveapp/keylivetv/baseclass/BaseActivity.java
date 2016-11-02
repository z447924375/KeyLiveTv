package com.keyliveapp.keylivetv.baseclass;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

/**
 * Created by dllo on 16/10/20.
 */

public abstract class BaseActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayout());
        initView();
        inidate();
    }

    /**
     *  设置布局
     * @return 布局文件id
     */
    protected abstract  int setLayout();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void inidate();

    protected <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }


}
