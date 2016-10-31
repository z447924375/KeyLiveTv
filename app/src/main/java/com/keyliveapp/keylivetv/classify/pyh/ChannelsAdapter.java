package com.keyliveapp.keylivetv.classify.pyh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyliveapp.keylivetv.R;

/**
 * Created by dllo on 16/10/24.
 */
public class ChannelsAdapter extends RecyclerView.Adapter{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_channels_channels, parent, false);
        ChannelsViewHolder channelsViewHolder = new ChannelsViewHolder(view);
        return channelsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private class ChannelsViewHolder extends RecyclerView.ViewHolder {
        public ChannelsViewHolder(View view) {
            super(view);

        }
    }
}
