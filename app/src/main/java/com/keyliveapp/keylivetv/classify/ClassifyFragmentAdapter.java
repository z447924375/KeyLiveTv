package com.keyliveapp.keylivetv.classify;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class ClassifyFragmentAdapter extends RecyclerView.Adapter {

    private Context context;

    private ArrayList<String> recommendsId;
    private ArrayList<String> recommendsName;
    private ArrayList<String> recommendsIcon;

    private ArrayList<String> channelsId;
    private ArrayList<String> channelsName;
    private ArrayList<String> chennelsIcon;

    public void setRecommendsId(ArrayList<String> recommendsId) {
        this.recommendsId = recommendsId;
    }

    public void setRecommendsName(ArrayList<String> recommendsName) {
        this.recommendsName = recommendsName;
    }

    public void setRecommendsIcon(ArrayList<String> recommendsIcon) {
        this.recommendsIcon = recommendsIcon;
    }

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
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }

        RecyclerView.ViewHolder viewHolder = null;

        switch (viewType) {
            case 0:
                View hotView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_hot, parent, false);
                viewHolder = new HotViewHolder(hotView);
                break;
            case 1:
                View channelsView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_channels, parent, false);
                viewHolder = new ChannelsViewHolder(channelsView);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (position) {
            case 0:
                HotViewHolder hotViewHolder = (HotViewHolder) holder;
                HotAdapter hotAdapter = new HotAdapter();
                LinearLayoutManager manager = new LinearLayoutManager(context);
                manager.setOrientation(LinearLayoutManager.HORIZONTAL);
                hotViewHolder.hotRecyclerView.setLayoutManager(manager);
                hotViewHolder.hotRecyclerView.setAdapter(hotAdapter);
                break;
            case 1:
                ChannelsViewHolder channelsViewHolder = (ChannelsViewHolder) holder;
                ChannelsAdapter channelsAdapter = new ChannelsAdapter();
                GridLayoutManager manager1 = new GridLayoutManager(context, 3);
                channelsViewHolder.channelsRecyclerView.setLayoutManager(manager1);
                channelsViewHolder.channelsRecyclerView.setAdapter(channelsAdapter);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView hotRecyclerView;

        public HotViewHolder(View hotView) {
            super(hotView);
            hotRecyclerView = (RecyclerView) hotView.findViewById(R.id.rv_classify_hot);
        }
    }

    private class ChannelsViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView channelsRecyclerView;

        public ChannelsViewHolder(View channelsView) {
            super(channelsView);
            channelsRecyclerView = (RecyclerView) channelsView.findViewById(R.id.rv_classify_channels);
        }
    }
}


