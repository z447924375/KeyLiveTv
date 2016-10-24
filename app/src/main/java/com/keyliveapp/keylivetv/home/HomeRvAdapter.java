package com.keyliveapp.keylivetv.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.keyliveapp.keylivetv.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.youth.banner.Banner;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 1:
                View bannerView = LayoutInflater.from(mContext).inflate(R.layout.home_banner_view, parent, false);
                BannerHolder bannerHolder = new BannerHolder(bannerView);
                return bannerHolder;

            case 2:
                View quickBtnView = LayoutInflater.from(mContext).inflate(R.layout.home_quickbtn_view, parent, false);
                HoriScrollHolder horiScrollHolder = new HoriScrollHolder(quickBtnView);
                return horiScrollHolder;
            case 3:


        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class BannerHolder extends RecyclerView.ViewHolder {

        private Banner recommend_banner;

        public BannerHolder(View itemView) {
            super(itemView);
            recommend_banner = (Banner) itemView.findViewById(R.id.home_banner);
        }
    }

    class HoriScrollHolder extends RecyclerView.ViewHolder {

        private HorizontalScrollView mHorizontalScrollView;

        public HoriScrollHolder(View itemView) {
            super(itemView);
            mHorizontalScrollView = (HorizontalScrollView) itemView.findViewById(R.id.home_quickbtn_scrollview);

        }
    }

}
