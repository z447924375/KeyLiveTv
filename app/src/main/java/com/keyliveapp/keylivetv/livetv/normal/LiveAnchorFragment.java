package com.keyliveapp.keylivetv.livetv.normal;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DomainBean;

/**
 * Created by dllo on 16/11/7.
 */
public class LiveAnchorFragment extends BaseFragment {
    private ImageView anchorPic;
    private TextView anchorTitle;
    private TextView anchorName;
    private TextView anchorNum;


    @Override
    protected int setLayout() {
        return R.layout.live_anchor;
    }

    @Override
    protected void initView() {
        anchorPic = getViewLayout(R.id.anchor_pic);
        anchorName = getViewLayout(R.id.anchor_name);
        anchorNum = getViewLayout(R.id.anchor_num);
        anchorTitle = getViewLayout(R.id.anchor_title);

    }

    @Override
    protected void initDate() {
        Bundle bundle = getArguments();
        DomainBean bean = (DomainBean) bundle.getSerializable("domain");

        Log.d("vvv", bean.getBaseRoomInfo().getName());
    }
}
