package com.keyliveapp.keylivetv.classify;

<<<<<<< HEAD:app/src/main/java/com/keyliveapp/keylivetv/classify/PullToRefreshAdapter.java
=======
import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.util.Log;
>>>>>>> feature/search:app/src/main/java/com/keyliveapp/keylivetv/classify/zxh/PullToRefreshAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;

/**
 * Created by dllo on 16/10/31.
 */
public class PullToRefreshAdapter extends BaseAdapter {
    Clicked mClicked;

    public void setClicked(Clicked clicked) {
        mClicked = clicked;
    }

    private ArrayList<String> previews;
    private ArrayList<String> viewers;
    private ArrayList<String> names;
    private ArrayList<String> titles;
    private Context mContext;

    public void setViewers(ArrayList<String> viewers) {
        this.viewers = viewers;
    }

    public void setNames(ArrayList<String> names) {
        this.names = names;
        Log.d("PullToRefreshAdapter", "names:" + names);
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    private int totalItems;

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public void setPreviews(ArrayList<String> previews) {
        this.previews = previews;
    }

    public void setAll(ArrayList<String> previewss, ArrayList<String> namess, ArrayList<String> titless, ArrayList<String> viewerss) {
        previews.addAll(previewss);
        Toast.makeText(mContext, "previews.size():" + previews.size(), Toast.LENGTH_SHORT).show();
        Log.d("PullToRefreshAdapter", "names:" + names);
        Log.d("PullToRefreshAdapter", "namess:" + namess);
        names.addAll(namess);
        Log.d("PullToRefreshAdapter", "names:" + names);
        viewers.addAll(viewerss);
        titles.addAll(titless);
<<<<<<< HEAD:app/src/main/java/com/keyliveapp/keylivetv/classify/PullToRefreshAdapter.java
        notifyDataSetInvalidated();
=======
//        notifyDataSetChanged();
>>>>>>> feature/search:app/src/main/java/com/keyliveapp/keylivetv/classify/zxh/PullToRefreshAdapter.java
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
<<<<<<< HEAD:app/src/main/java/com/keyliveapp/keylivetv/classify/PullToRefreshAdapter.java
    public View getView(final int position, View convertView, ViewGroup parent) {
=======
    public void notifyDataSetChanged() {
        Log.d("PullToRefreshAdapter", "names:" + names);
        super.notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
>>>>>>> feature/search:app/src/main/java/com/keyliveapp/keylivetv/classify/zxh/PullToRefreshAdapter.java

        MyViewHolder myViewHolder = null;

        if (mContext == null) {
            mContext = parent.getContext();
        }

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
            myViewHolder.imageViewLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClicked.click(position * 2);
                }
            });
            myViewHolder.imageViewRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mClicked.click(position*2+1);
                }
            });


            myViewHolder.tvNameLeft.setText(names.get(position * 2));
            myViewHolder.tvNameRight.setText(names.get(position * 2 + 1));

            myViewHolder.tvTitlesLeft.setText(titles.get(position * 2));
            myViewHolder.tvTitlesRight.setText(titles.get(position * 2 + 1));

            myViewHolder.tvViewerLeft.setText(viewers.get(position * 2));
            myViewHolder.tvViewRigth.setText(viewers.get(position * 2 + 1));

        } catch (IndexOutOfBoundsException e) {
            myViewHolder.imageViewRight.setVisibility(View.INVISIBLE);
            myViewHolder.tvViewRigth.setVisibility(View.INVISIBLE);
            myViewHolder.tvTitlesRight.setVisibility(View.INVISIBLE);
            myViewHolder.tvNameRight.setVisibility(View.INVISIBLE);
        }

        return convertView;
    }

    private class MyViewHolder {

        private final ImageView imageViewLeft;
        private final ImageView imageViewRight;
        private final TextView tvTitlesRight;
        private final TextView tvTitlesLeft;
        private final TextView tvViewRigth;
        private final TextView tvViewerLeft;
        private final TextView tvNameRight;
        private final TextView tvNameLeft;

        public MyViewHolder(View convertView) {
            imageViewLeft = (ImageView) convertView.findViewById(R.id.img_classify_left);
            imageViewRight = (ImageView) convertView.findViewById(R.id.img_classify_right);
            tvNameLeft = (TextView) convertView.findViewById(R.id.tv_name_left);
            tvNameRight = (TextView) convertView.findViewById(R.id.tv_name_right);
            tvViewerLeft = (TextView) convertView.findViewById(R.id.tv_viewers_left);
            tvViewRigth = (TextView) convertView.findViewById(R.id.tv_viewers_right);
            tvTitlesLeft = (TextView) convertView.findViewById(R.id.tv_title_left);
            tvTitlesRight = (TextView) convertView.findViewById(R.id.tv_title_right);
        }
    }

    public interface Clicked {
        void click(int position);
    }


}
