package com.keyliveapp.keylivetv.classify.zxh;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;

public class ClassifyClickInActivity extends BaseActivity {
    private TextView title;
    private RecyclerView mRecyclerView;

    @Override
    protected int setLayout() {
        return R.layout.classify_clickin;
    }

    @Override
    protected void initView() {
        title = bindView(R.id.classify_clickin_title);
        mRecyclerView = bindView(R.id.classify_clickin_rv);
    }

    @Override
    protected void inidate() {
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        Log.d("ClassifyClickInActivity", url);

    }
}
