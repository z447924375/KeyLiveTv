package com.keyliveapp.keylivetv.classify.pyh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.MyApp;

import com.keyliveapp.keylivetv.classify.zxh.ClassifyClickInActivity;
import com.keyliveapp.keylivetv.myapp.MyAppp;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/24.
 */
public class HotAdapter extends RecyclerView.Adapter{

    private ArrayList<String> recommendsId;
    private ArrayList<String> recommendsName;
    private ArrayList<String> recommendsIcon;
    private Context context;


    public void setRecommendsId(ArrayList<String> recommendsId) {
        this.recommendsId = recommendsId;
    }

    public void setRecommendsName(ArrayList<String> recommendsName) {
        this.recommendsName = recommendsName;
    }

    public void setRecommendsIcon(ArrayList<String> recommendsIcon) {
        this.recommendsIcon = recommendsIcon;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_hot_channels, parent, false);

        HotViewHolder hotViewHolder = new HotViewHolder(view);

        return hotViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        HotViewHolder hotViewHolder = (HotViewHolder) holder;
        final String hotTitle = recommendsName.get(position);
        final String iconUrl = recommendsIcon.get(position);
        hotViewHolder.tvHot.setText(hotTitle);
        Glide.with(context)
                .load(iconUrl)
                .into(hotViewHolder.imgHot);

//        final String url = URL_BEFORE + recommendsId.get(position) + URL_BEHIND;

        hotViewHolder.imgHot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ClassifyClickInActivity.class);
                intent.putExtra("gameId", recommendsId.get(position));
                intent.putExtra("name", hotTitle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return recommendsIcon.size();
    }

    private class HotViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvHot;
        private final ImageView imgHot;

        public HotViewHolder(View itemView) {
            super(itemView);

            tvHot = (TextView) itemView.findViewById(R.id.tv_hot);
            imgHot = (ImageView) itemView.findViewById(R.id.img_hot);

        }
    }
}
