package com.keyliveapp.keylivetv.discovery.nearby;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.bean.DiscoveryBean;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dllo on 16/10/28.
 */

public class NearbyRvAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private DiscoveryBean bean;
    private OnNearbyItemClickListener mNearbyItemClickListener;

    public void setNearbyItemClickListener(OnNearbyItemClickListener nearbyItemClickListener) {
        mNearbyItemClickListener = nearbyItemClickListener;
    }

    public NearbyRvAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setBean(DiscoveryBean bean) {
        this.bean = bean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.discovery_nearby_view,parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        MyViewHolder myViewHolder= (MyViewHolder) holder;
        Glide.with(mContext).load(bean.getData().getStreams().getItems().get(position).getPreview())
                .bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(myViewHolder.pic);
        myViewHolder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNearbyItemClickListener.nearbyClick(position);
            }
        });
        Glide.with(mContext).load(bean.getData().getStreams().getItems().get(position).getPreview()).bitmapTransform(new RoundedCornersTransformation(mContext, 10, 0, RoundedCornersTransformation.CornerType.ALL)).into(myViewHolder.pic);
        myViewHolder.name.setText(bean.getData().getStreams().getItems().get(position).getUser().getName());
    }

    @Override
    public int getItemCount() {
        return bean.getData().getStreams().getItems().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private ImageView pic;
        private TextView name;
        private TextView location;

        public MyViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.discovery_nearby_pic);
            name = (TextView) itemView.findViewById(R.id.discovery_nearby_text);
            location = (TextView) itemView.findViewById(R.id.discovery_nearby_location);
        }
    }

    public interface OnNearbyItemClickListener{
        void nearbyClick(int position);
    }


}
