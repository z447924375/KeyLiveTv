package com.keyliveapp.keylivetv.livetv.normal;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DomainBean;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/11/7.
 */
public class LiveAnchorFragment extends BaseFragment {
    private ImageView anchorPic;
    private TextView anchorTitle, anchorName, anchorNum, anchorDesc;


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
        anchorDesc = getViewLayout(R.id.anchor_desc);
    }

    @Override
    protected void initDate() {
        Bundle bundle = getArguments();
        DomainBean bean = (DomainBean) bundle.getSerializable("domain");

        Glide.with(getContext()).load(bean.getBaseRoomInfo().getAvatar())
                .bitmapTransform(new CropCircleTransformation(getContext())).into(anchorPic);
        anchorName.setText(bean.getBaseRoomInfo().getName());
        anchorTitle.setText("主播 : "+bean.getBaseRoomInfo().getBoardCastTitle());
        anchorNum.setText("在线人数 : "+bean.getOnlineCount()+"");
        anchorDesc.setText("公告 : "+bean.getBaseRoomInfo().getDesc());


        Log.d("vvv", bean.getBaseRoomInfo().getName());
    }
}
