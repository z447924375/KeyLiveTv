package com.keyliveapp.keylivetv.classify.pyh;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ChannelsAdapter extends RecyclerView.Adapter{

    private ArrayList<String> channelsId;
    private ArrayList<String> channelsName;
    private ArrayList<String> chennelsIcon;
    private Context context;

    public void setChannelsId(ArrayList<String> channelsId) {
        this.channelsId = channelsId;
    }

    public void setChannelsName(ArrayList<String> channelsName) {
        this.channelsName = channelsName;
    }

    public void setChennelsIcon(ArrayList<String> chennelsIcon) {
        this.chennelsIcon = chennelsIcon;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_channels_channels, parent, false);
        ChannelsViewHolder channelsViewHolder = new ChannelsViewHolder(view);
        return channelsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ChannelsViewHolder channelsViewHolder = (ChannelsViewHolder) holder;
//        channelsViewHolder.tvChannels.setText(channelsName.get(position));
        Glide.with(context)
                .load(chennelsIcon)
                .into(channelsViewHolder.imgChannels);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private class ChannelsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgChannels;
//        private final TextView tvChannels;

        public ChannelsViewHolder(View view) {
            super(view);
            imgChannels = (ImageView) view.findViewById(R.id.img_channels);
//            tvChannels = (TextView) view.findViewById(R.id.tv_channels);
        }
    }
}
