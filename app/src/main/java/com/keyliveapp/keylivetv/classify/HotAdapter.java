package com.keyliveapp.keylivetv.classify;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyliveapp.keylivetv.R;

/**
 * Created by dllo on 16/10/24.
 */
public class HotAdapter extends RecyclerView.Adapter{

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_hot_channels, parent, false);

        HotViewHolder hotViewHolder = new HotViewHolder(view);

        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    private class HotViewHolder extends RecyclerView.ViewHolder {
        public HotViewHolder(View itemView) {
            super(itemView);
        }
    }
}
