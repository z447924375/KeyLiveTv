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
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {
    private static final int HEADER_TYPE=0;


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

        return mColumnsBeen.get(position).getViewType();

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                View allView = LayoutInflater.from(mContext).inflate(R.layout.home_all_view, parent, false);
                AllViewHolder allViewHolder = new AllViewHolder(allView);
                return allViewHolder;

            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;

        }

        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (mColumnsBeen.get(position).getViewType()) {
            case 0:
                AllViewHolder allViewHolder = (AllViewHolder) holder;
                allViewHolder.allTitle.setText(mColumnsBeen.get(position).getGame().getTitle());
                allViewHolder.btnChannel.setText(mColumnsBeen.get(position).getChannelsText());
                for (int i = 0; i < 6; i++) {
                  ImageLoader.getInstance().
                          displayImage(mColumnsBeen.get(0).getRooms().get(i).getPreview(),allViewHolder.home_all_pics[i],options);
                }

                break;
        }
    }

    @Override
    public int getItemCount() {
        return mColumnsBeen.size();
    }

    class AllViewHolder extends RecyclerView.ViewHolder {

        private final Button btnChannel;
        private int home_all_pics_id[] = {R.id.home_all_pic1, R.id.home_all_pic2,
                R.id.home_all_pic3, R.id.home_all_pic4,
                R.id.home_all_pic5, R.id.home_all_pic6};
        private int home_all_texts_id[] = {R.id.home_all_text1, R.id.home_all_text2,
                R.id.home_all_text3, R.id.home_all_text4,
                R.id.home_all_text5, R.id.home_all_text6};
        private ImageView home_all_pic2;
        private ImageView home_all_pic1;
        private ImageView home_all_pic4;
        private ImageView home_all_pic3;
        private ImageView home_all_pic5;
        private ImageView home_all_pic6;
        private ImageView home_all_pics[] = {home_all_pic1,
                home_all_pic2, home_all_pic3,
                home_all_pic4, home_all_pic5,
                home_all_pic6};
        private TextView home_all_text1;
        private TextView home_all_text3;
        private TextView home_all_text2;
        private TextView home_all_text5;
        private TextView home_all_text4;
        private TextView home_all_text6;
        private TextView home_all_texts[] = {home_all_text1,
                home_all_text2, home_all_text3,
                home_all_text4, home_all_text5,
                home_all_text6};

        private final TextView allTitle;

        public AllViewHolder(View itemView) {
            super(itemView);
            allTitle = (TextView) itemView.findViewById(R.id.home_all_title);
            btnChannel = (Button) itemView.findViewById(R.id.home_btn_channel);
            for (int i = 0; i < 6; i++) {
                home_all_pics[i] = (ImageView) itemView.findViewById(home_all_pics_id[i]);
                home_all_texts[i] = (TextView) itemView.findViewById(home_all_texts_id[i]);
            }

        }
    }


}
