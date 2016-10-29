package com.keyliveapp.keylivetv.home.homeui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeContentClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeTitleClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnLiveRecChannelListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnLiveRecItemClickListener;

import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */

public class HomeRvAdapter extends RecyclerView.Adapter {

    private Context context;
    private static final int LIVE_TYPE = 0;
    private static final int HEAD_TYPE = 1;
    private static final int CONTENT_TYPE = 2;
    private List<HomeBean.DataBean.ColumnsBean> mColumnsBeen;
    private LayoutInflater mInflater;
    private int count;
    private OnLiveRecItemClickListener mLiveRecItemClickListener;
    private OnHomeTitleClickListener mTitleClickListener;
    private OnHomeContentClickListener mContentClickListener;
    private OnLiveRecChannelListener mChannelListener;

    public void setChannelListener(OnLiveRecChannelListener channelListener) {
        mChannelListener = channelListener;
    }

    public void setLiveRecItemClickListener(OnLiveRecItemClickListener liveRecItemClickListener) {
        mLiveRecItemClickListener = liveRecItemClickListener;
    }

    public void setTitleClickListener(OnHomeTitleClickListener titleClickListener) {
        mTitleClickListener = titleClickListener;
    }

    public void setContentClickListener(OnHomeContentClickListener contentClickListener) {
        mContentClickListener = contentClickListener;
    }

    public HomeRvAdapter(Context context, List<HomeBean.DataBean.ColumnsBean> columnsBeen) {
        mColumnsBeen = columnsBeen;
        this.context = context;
        mInflater = LayoutInflater.from(context);
        count = (this.mColumnsBeen.size() - 1) * 5 + 1;

    }


    @Override
    public int getItemViewType(int position) {

        if (position < 1) {
            return LIVE_TYPE;
        } else if (position % 5 == 1) {
            return HEAD_TYPE;
        } else {
            return CONTENT_TYPE;
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {

            case LIVE_TYPE:
                View liveSixView = mInflater.inflate(R.layout.home_live_six_view, parent, false);
                LiveSixViewHolder sixViewHolder = new LiveSixViewHolder(liveSixView);
                return sixViewHolder;
            case HEAD_TYPE:
                View headView = mInflater.inflate(R.layout.home_header_view, parent, false);
                HeadViewHolder headViewHolder = new HeadViewHolder(headView);
                return headViewHolder;
            case CONTENT_TYPE:
                View contentView = mInflater.inflate(R.layout.home_content_view, parent, false);
                ContentViewHolder contentViewHolder = new ContentViewHolder(contentView);
                return contentViewHolder;


        }
        return null;


    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int titlePosition;
        final int contentPosition;
        if (position < 1) {
            titlePosition = 0;
            contentPosition = 0;
        } else {
            titlePosition = (position - 1) / 5 + 1;
            contentPosition = (position - 2) % 5;
        }
        switch (getItemViewType(position)) {
            case LIVE_TYPE:
                LiveSixViewHolder sixViewHolder = (LiveSixViewHolder) holder;
                sixViewHolder.title.setText(mColumnsBeen.get(0).getGame().getName());
                sixViewHolder.btnChannel.setText(mColumnsBeen.get(0).getChannelsText());
                sixViewHolder.btnChannel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mChannelListener.liveRecChannelClicked();
                    }
                });

                for (int i = 0; i < 6; i++) {
                    sixViewHolder.homeLiveTexts[i].setText(mColumnsBeen.get(0).
                            getRooms().get(i).getChannel().getStatus());
                    Glide.with(context).load(mColumnsBeen.get(0).getRooms().get(i)
                            .getPreview()).into(sixViewHolder.homeLivePics[i]);

                    final int finalI = i;
                    sixViewHolder.homeLivePics[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mLiveRecItemClickListener.liveItemClicked(finalI);
                        }
                    });

                }
                break;
            case HEAD_TYPE:
                HeadViewHolder headViewHolder = (HeadViewHolder) holder;
                headViewHolder.headTitle.setText(mColumnsBeen.get(titlePosition).getGame().getTitle());
                headViewHolder.btnHeadChannel.setText(mColumnsBeen.get(titlePosition).getChannelsText());
                headViewHolder.btnHeadChannel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTitleClickListener.titleClicked(titlePosition);
                    }
                });

                break;


            case CONTENT_TYPE:
                ContentViewHolder contentViewHolder = (ContentViewHolder) holder;
                contentViewHolder.contentTitle.setText(mColumnsBeen.get(titlePosition).getRooms()
                        .get(contentPosition).getChannel().getStatus());

                contentViewHolder.contentTag.setText(mColumnsBeen.get(titlePosition).getRooms()
                        .get(contentPosition).getChannel().getName());

                contentViewHolder.contentNum.setText(mColumnsBeen.get(titlePosition).getRooms()
                        .get(contentPosition).getViewers() + "");

                Glide.with(context).load(mColumnsBeen.get(titlePosition).getRooms()
                        .get(contentPosition).getPreview()).into(contentViewHolder.contentPic);
                contentViewHolder.contentPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //// TODO: 16/10/28 回调
                        mContentClickListener.contentClicked(titlePosition, contentPosition);
                    }
                });


                break;


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
        private TextView contentTag;
        private TextView contentNum;

        public ContentViewHolder(View itemView) {
            super(itemView);
            contentPic = (ImageView) itemView.findViewById(R.id.home_content_pic);
            contentTitle = (TextView) itemView.findViewById(R.id.home_content_text);
            contentTag = (TextView) itemView.findViewById(R.id.home_content_tag);
            contentNum = (TextView) itemView.findViewById(R.id.home_content_num);
        }


    }

    class LiveSixViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private Button btnChannel;
        private ImageView home_all_pic1;
        private ImageView home_all_pic2;
        private ImageView home_all_pic3;
        private ImageView home_all_pic4;
        private ImageView home_all_pic5;
        private ImageView home_all_pic6;
        ImageView homeLivePics[] = {home_all_pic1,
                home_all_pic2, home_all_pic3,
                home_all_pic4, home_all_pic5,
                home_all_pic6};
        private TextView home_all_text1;
        private TextView home_all_text3;
        private TextView home_all_text5;
        private TextView home_all_text2;
        private TextView home_all_text6;
        private TextView home_all_text4;
        TextView homeLiveTexts[] = {home_all_text1,
                home_all_text2, home_all_text3,
                home_all_text4, home_all_text5,
                home_all_text6};
        int homeLivePicIds[] = {R.id.home_all_pic1, R.id.home_all_pic2,
                R.id.home_all_pic3, R.id.home_all_pic4,
                R.id.home_all_pic5, R.id.home_all_pic6};
        int homeLiveTextIds[] = {R.id.home_all_text1, R.id.home_all_text2,
                R.id.home_all_text3, R.id.home_all_text4,
                R.id.home_all_text5, R.id.home_all_text6};

        public LiveSixViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.home_all_title);
            btnChannel = (Button) itemView.findViewById(R.id.home_btn_channel);
            for (int i = 0; i < homeLivePics.length; i++) {
                homeLivePics[i] = (ImageView) itemView.findViewById(homeLivePicIds[i]);
                homeLiveTexts[i] = (TextView) itemView.findViewById(homeLiveTextIds[i]);
            }
        }


    }


}
