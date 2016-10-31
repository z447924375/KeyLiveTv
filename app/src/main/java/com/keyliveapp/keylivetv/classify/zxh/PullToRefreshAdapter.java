package com.keyliveapp.keylivetv.classify.zxh;

import android.support.v4.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/31.
 */
public class PullToRefreshAdapter extends BaseAdapter{

    private ArrayList<String> previews;

    private int totalItems;

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setPreviews(ArrayList<String> previews) {
        this.previews = previews;
    }

    public void setAll(ArrayList<String> previewss) {
        previews.addAll(previewss);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        int count;
        count = (previews.size() + 1) / 2;
        return count;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder myViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classify_all, parent, false);

            myViewHolder = new MyViewHolder(convertView);

            convertView.setTag(myViewHolder);

        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        try {
            Glide.with(parent.getContext())
                    .load(previews.get(position * 2))
                    .into(myViewHolder.imageViewLeft);
            Glide.with(parent.getContext())
                    .load(previews.get(position * 2 + 1))
                    .into(myViewHolder.imageViewRight);
        } catch (IndexOutOfBoundsException e) {
            myViewHolder.imageViewRight.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private class MyViewHolder {

        private final ImageView imageViewLeft;
        private final ImageView imageViewRight;

        public MyViewHolder(View convertView) {
            imageViewLeft = (ImageView) convertView.findViewById(R.id.img_classify_left);
            imageViewRight = (ImageView) convertView.findViewById(R.id.img_classify_right);
        }
    }
}
