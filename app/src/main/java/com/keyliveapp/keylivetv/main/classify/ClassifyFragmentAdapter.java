package com.keyliveapp.keylivetv.main.classify;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;

import com.keyliveapp.keylivetv.R;

/**
 * Created by dllo on 16/10/24.
 */
public class ClassifyFragmentAdapter extends RecyclerView.Adapter {

    private Context context;

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


