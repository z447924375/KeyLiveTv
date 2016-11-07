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
public class ChannelsAdapter extends RecyclerView.Adapter {

    private List<String> channelsId;
    private List<String> channelsName;
    private List<String> chennelsIcon;
    private Context context;

    public void setChannelsId(List<String> channelsId) {
        this.channelsId = channelsId;
    }

    public void setChannelsName(List<String> channelsName) {
        this.channelsName = channelsName;
    }

    public void setChennelsIcon(List<String> chennelsIcon) {
        this.chennelsIcon = chennelsIcon;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (context == null) {
            context = parent.getContext();
        }

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_channels_channels, parent, false);
        ChannelsViewHolder channelsViewHolder = new ChannelsViewHolder(view);
        return channelsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ChannelsViewHolder channelsViewHolder = (ChannelsViewHolder) holder;
        channelsViewHolder.tvChannels.setText(channelsName.get(position));
        Glide.with(context)
                .load(chennelsIcon.get(position))
                .into(channelsViewHolder.imgChannels);

        final String title = channelsName.get(position);

        channelsViewHolder.imgChannels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String URL_BEFORE1 = "";
                String URL_BEFORE2 = "";
                String URL_BEHIND = "";
                String gameId = channelsId.get(position);

                if (position == 0) {
                    URL_BEFORE1 = "https://a4.plu.cn/api/streams?start-index=";
                    URL_BEFORE2 = "&max-results=30&game=";
                    URL_BEHIND = "&sort-by=views&version=3.7.0&device=4&packageId=1";
                } else if (position == 1) {
                    URL_BEFORE1 = null;
                    URL_BEFORE2 = null;
                    URL_BEHIND = null;
                } else {
                    URL_BEFORE1 = "https://a4.plu.cn/api/streams?start-index=";
                    URL_BEFORE2 = "&max-results=30&game=";
                    URL_BEHIND = "&version=3.7.0&device=4&packageId=1";
                }

                doIntent(URL_BEFORE1, URL_BEFORE2, URL_BEHIND, gameId);

            }

            private void doIntent(String URL_BEFORE1, String URL_BEFORE2, String URL_BEHIND, String gameId) {
                Intent intent = new Intent(context, ClassifyClickInActivity.class);
                intent.putExtra("urlbefore1", URL_BEFORE1);
                intent.putExtra("urlbefore2", URL_BEFORE2);
                intent.putExtra("urlbehind", URL_BEHIND);
                intent.putExtra("gameid", gameId);
                intent.putExtra("title", title);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return channelsName.size();
    }

    private class ChannelsViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imgChannels;
        private final TextView tvChannels;

        public ChannelsViewHolder(View view) {
            super(view);
            imgChannels = (ImageView) view.findViewById(R.id.img_channels);
            tvChannels = (TextView) view.findViewById(R.id.tv_channels);
        }
    }
}
