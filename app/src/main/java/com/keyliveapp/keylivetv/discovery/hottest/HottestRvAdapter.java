package com.keyliveapp.keylivetv.discovery.hottest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.bean.HottestBean;

/**
 * Created by dllo on 16/11/2.
 */

public class HottestRvAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private HottestBean bean;

    public HottestRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(HottestBean bean) {
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.discovery_hottest_view, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        Glide.with(mContext).load(bean.getData().getItems().get(position).getPreview()).into(myViewHolder.pic);
        myViewHolder.name.setText(bean.getData().getItems().get(position).getUser().getName());
        myViewHolder.num.setText(Integer.toString(bean.getData().getItems().get(position).getViewers()));


    }

    @Override
    public int getItemCount() {
        return bean.getData().getItems().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        private ImageView pic;
        private TextView name;
        private TextView num;


        public MyViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.discovery_hottest_pic);
            name = (TextView) itemView.findViewById(R.id.discovery_hottest_text);
            num = (TextView) itemView.findViewById(R.id.discovery_hottest_num);
        }
    }
}
