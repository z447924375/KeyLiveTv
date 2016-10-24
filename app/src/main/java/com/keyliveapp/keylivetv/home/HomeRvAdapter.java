package com.keyliveapp.keylivetv.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.keyliveapp.keylivetv.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeRvAdapter extends RecyclerView.Adapter<HomeRvAdapter.HomeViewHolder> {
    private final DisplayImageOptions options;
    private HomeBean mHomeBean;
    private Context mContext;

    public void setHomeBean(HomeBean homeBean) {
        mHomeBean = homeBean;
    }

    public HomeRvAdapter(Context context) {
        mContext = context;
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.default_live_ic).showImageOnLoading(R.mipmap.default_live_ic)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
    }


    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        public HomeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
