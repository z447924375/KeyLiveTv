package com.keyliveapp.keylivetv.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.keyliveapp.keylivetv.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private final DisplayImageOptions options;
    private List<HomeBean.DataBean.ColumnsBean> mColumnsBeen;

    public void setColumnsBeen(List<HomeBean.DataBean.ColumnsBean> columnsBeen) {
        mColumnsBeen = columnsBeen;
    }

    private Context mContext;


    public HomeRvAdapter(Context context) {
        mContext = context;
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.default_live_ic).showImageOnLoading(R.mipmap.default_live_ic)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
    }

    @Override
    public int getItemViewType(int position) {
        for (int i = 0; i < mColumnsBeen.size(); i++) {
            return i;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mColumnsBeen.size();
    }


}
