package com.keyliveapp.keylivetv.discovery.nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/28.
 */

public class NearbyRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private ArrayList<String> arrayList;

    public NearbyRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.discovery_nearby_view,parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private  ImageView pic;
        private  TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.discovery_nearby_pic);
            tv = (TextView) itemView.findViewById(R.id.discovery_nearby_text);
        }
    }

}
