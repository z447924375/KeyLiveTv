package com.keyliveapp.keylivetv.home.homeui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeHoriItemClickListner;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dllo on 16/11/11.
 */

public class HomeHoriRecyclerAdapter extends RecyclerView.Adapter<HomeHoriRecyclerAdapter.MyViewHolder> {

    private Context mContext;
    private HomeBean mHomeBean;
    private OnHomeHoriItemClickListner mHoriItemClickListner;

    public void setHoriItemClickListner(OnHomeHoriItemClickListner horiItemClickListner) {
        mHoriItemClickListner = horiItemClickListner;
    }

    public void setHomeBean(HomeBean homeBean) {
        mHomeBean = homeBean;
    }
    public HomeHoriRecyclerAdapter(Context context) {
        mContext = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.home_scrollview_imgs,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Glide.with(mContext).load(mHomeBean.getData().getQuickbutton().get(position).getImage())
                .bitmapTransform(new RoundedCornersTransformation(mContext,30,0, RoundedCornersTransformation.CornerType.ALL))
                .into(holder.pic);
        holder.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHoriItemClickListner.horiItemClick(position);
            }
        });
    }



    @Override

    public int getItemCount() {
        return mHomeBean.getData().getQuickbutton().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView pic;

        public MyViewHolder(View itemView) {
            super(itemView);
            pic = (ImageView) itemView.findViewById(R.id.home_scrollview_pic);

        }
    }
}
