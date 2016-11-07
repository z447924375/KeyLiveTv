package com.keyliveapp.keylivetv.classify;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/24.
 */
public class HotAdapter extends RecyclerView.Adapter{

    private List<String> recommendsId;
    private List<String> recommendsName;
    private List<String> recommendsIcon;
    private Context context;


    public void setRecommendsId(List<String> recommendsId) {
        this.recommendsId = recommendsId;
    }

    public void setRecommendsName(List<String> recommendsName) {
        this.recommendsName = recommendsName;
    }

    public void setRecommendsIcon(List<String> recommendsIcon) {
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
//                Toast.makeText(context, hotTitle, Toast.LENGTH_SHORT).show();
//                Toast.makeText(context, recommendsId.get(position), Toast.LENGTH_SHORT).show();
                String URL_BEFORE1 = "https://a4.plu.cn/api/streams?start-index=";
                String URL_BEFORE2 = "&max-results=30&game=";
                String URL_BEHIND = "&version=3.7.0&device=4&packageId=1";
                doIntent(URL_BEFORE1, URL_BEFORE2, URL_BEHIND);
            }

            private void doIntent(String URL_BEFORE1, String URL_BEFORE2, String URL_BEHIND) {
                Intent intent = new Intent(context, ClassifyClickInActivity.class);
                intent.putExtra("urlbefore1", URL_BEFORE1);
                intent.putExtra("urlbefore2", URL_BEFORE2);
                intent.putExtra("urlbehind", URL_BEHIND);
                intent.putExtra("gameid", recommendsId.get(position));
                intent.putExtra("title", hotTitle);
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
