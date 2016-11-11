package com.keyliveapp.keylivetv.home.homeui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseExpandableAdapter;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeContentClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeTitleClickListener;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dllo on 16/11/10.
 */

public class HomeRecyclerAdapter extends BaseExpandableAdapter<HomeRecyclerAdapter.TitleViewHolder,HomeRecyclerAdapter.ContentViewHolder> {
    public void setHomeTitleClickListener(OnHomeTitleClickListener homeTitleClickListener) {
        mTitleClickListener = homeTitleClickListener;
    }
    private OnHomeContentClickListener mContentClickListener;

    public void setContentClickListener(OnHomeContentClickListener contentClickListener) {
        mContentClickListener = contentClickListener;
    }

    private OnHomeTitleClickListener mTitleClickListener;

    private List<HomeBean.DataBean.ColumnsBean> mColumnsBeen;
    private LayoutInflater mInflater;
    private Context mContext;

    public HomeRecyclerAdapter(Context context, List<HomeBean.DataBean.ColumnsBean> columnsBeen) {
        this.mContext = context;
        mColumnsBeen = columnsBeen;
        mInflater = LayoutInflater.from(context);
        mTotalCount = getItemTotalCount();
    }

    @Override
    protected TitleViewHolder createGroupHolder(ViewGroup parent) {
        View headView = mInflater.inflate(R.layout.home_header_view, parent, false);
        TitleViewHolder titleViewHolder = new TitleViewHolder(headView);
        return titleViewHolder;
    }

    @Override
    protected ContentViewHolder createChildHolder(ViewGroup parent) {
        View contentView = mInflater.inflate(R.layout.recycler_content_view, parent, false);
        ContentViewHolder contentViewHolder = new ContentViewHolder(contentView);
        return contentViewHolder;
    }

    @Override
    protected void onBindGroupHolder(TitleViewHolder groupHolder, final int groupPosition) {
        HomeBean.DataBean.ColumnsBean columnsBean = mColumnsBeen.get(groupPosition);
        groupHolder.headTitle.setText(columnsBean.getGame().getTitle());
        groupHolder.btnHeadChannel.setText(columnsBean.getChannelsText());
        groupHolder.btnHeadChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitleClickListener.titleClicked(groupPosition);
            }
        });

    }

    @Override
    protected void onBindChildHolder(ContentViewHolder childHolder, final int groupPosition, final int childPosition) {
        HomeBean.DataBean.ColumnsBean.RoomsBean roomsBean = mColumnsBeen.get(groupPosition).getRooms().get(childPosition);
        childHolder.contentTitle.setText(roomsBean.getChannel().getStatus());

        childHolder.contentTag.setText(roomsBean.getChannel().getName());

        childHolder.contentNum.setText(roomsBean.getViewers() + "");

        Glide.with(mContext).load(roomsBean.getPreview())
                .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(childHolder.contentPic);
        childHolder.contentPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContentClickListener.contentClicked(groupPosition, childPosition);
            }
        });
    }

    @Override
    public Object getGroupItem(int groupPosition) {
        return mColumnsBeen.get(groupPosition);
    }

    @Override
    public Object getChildItem(int groupPosition, int childPosition) {
        return mColumnsBeen.get(groupPosition).getRooms().get(childPosition);
    }

    @Override
    protected int getGroupCount() {
        return mColumnsBeen.size();
    }

    @Override
    protected int getChildCount(int groupPosition) {
        return mColumnsBeen.get(groupPosition).getRooms().size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView headTitle;
        private Button btnHeadChannel;

        public TitleViewHolder(View itemView) {
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
}
