package com.keyliveapp.keylivetv.discovery.newest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.bean.NewestBean;

/**
 * Created by dllo on 16/11/3.
 */

public class NewestRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private NewestBean bean;

    public NewestRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(NewestBean bean) {
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.discovery_newest_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return bean.getData().getStreams().getItems().size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
