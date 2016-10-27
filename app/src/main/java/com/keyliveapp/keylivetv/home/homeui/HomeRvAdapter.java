package com.keyliveapp.keylivetv.home.homeui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private static final int HEADER_TYPE = 0;
    private static final int CONTENT_TYPE = 1;
    private final DisplayImageOptions options;
    private List<HomeBean.DataBean.ColumnsBean> mColumnsBeen;
    private LayoutInflater mInflater;
    private int count;


    public HomeRvAdapter(Context context, List<HomeBean.DataBean.ColumnsBean> columnsBeen) {
        mColumnsBeen = columnsBeen;
        options = new DisplayImageOptions.Builder().showImageForEmptyUri(R.mipmap.default_live_ic).showImageOnLoading(R.mipmap.default_live_ic)
                .cacheInMemory(true).cacheOnDisk(true).considerExifParams(true).build();
        mInflater = LayoutInflater.from(context);
        count = this.mColumnsBeen.size() + this.mColumnsBeen.size() * 4 + 2;

    }

    /**
     * v     0 1 1 1 1 1 1
     * <p>
     * 1     h c c c c c c    0
     * 2     h c c c c        7
     * 3     h c c c c        12
     * 4     h c c c c        17
     * 5     h c c c c        22
     * 6     h c c c c        27
     * 7     h c c c c        32
     */

    @Override
    public int getItemViewType(int position) {
        if (count == 0 || (count % 5 == 2 && count != 2)) {
            return HEADER_TYPE;
        } else {
            return CONTENT_TYPE;
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case HEADER_TYPE:
                View headView = mInflater.inflate(R.layout.home_header_view, parent, false);
                HeadViewHolder headViewHolder = new HeadViewHolder(headView);
                return headViewHolder;

            case CONTENT_TYPE:
                View contentView = mInflater.inflate(R.layout.home_content_view, parent, false);
                ContentViewHolder contentViewHolder = new ContentViewHolder(contentView);
                return contentViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        int titlePosition = (position + 3) / 5;
        
        int contentPosition;
        if (titlePosition == 0) {
            contentPosition = position - 1;
        }
        if (titlePosition > 0) {
            contentPosition = position - titlePosition * 5 - 3;
        }


    }

    @Override
    public int getItemCount() {
        return count;
    }

    class HeadViewHolder extends RecyclerView.ViewHolder {

        private TextView headTitle;
        private Button btnHeadChannel;

        public HeadViewHolder(View itemView) {
            super(itemView);
            headTitle = (TextView) itemView.findViewById(R.id.home_head_title);
            btnHeadChannel = (Button) itemView.findViewById(R.id.home_btn_channel);
        }
    }

    class ContentViewHolder extends RecyclerView.ViewHolder {

        private ImageView contentPic;
        private TextView contentTitle;

        public ContentViewHolder(View itemView) {
            super(itemView);
            contentPic = (ImageView) itemView.findViewById(R.id.home_content_pic);
            contentTitle = (TextView) itemView.findViewById(R.id.home_content_text);

        }
    }


}
