package com.keyliveapp.keylivetv.search.result.host;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

/**
 * Created by dllo on 16/11/9.
 */
public class HostViewAdapter extends BaseAdapter{

    private HostBean hostBean;
    private List<HostBean.ItemsBean> listbBean;

    public void setHostBean(HostBean hostBean) {
        this.hostBean = hostBean;
        listbBean = new ArrayList<>();
        for (int i = 0; i < hostBean.getItems().size(); i++) {
            listbBean.add(hostBean.getItems().get(i));
        }
    }

    public void setListbBean(HostBean hostBeen) {
        for (int i = 0; i < hostBeen.getItems().size(); i++) {
            listbBean.add(hostBeen.getItems().get(i));
        }
    }

    @Override
    public int getCount() {
        return listbBean.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder myViewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_host_view, parent, false);
            myViewHolder = new MyViewHolder(convertView);
            convertView.setTag(myViewHolder);
        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        String logoIcon = listbBean.get(position).getLogo();

        HostBean.ItemsBean itemsBean = listbBean.get(position);

        Glide.with(parent.getContext())
                .load(itemsBean.getLogo())
                .bitmapTransform(new CropCircleTransformation(parent.getContext()))
                .into(myViewHolder.imgHost);
        myViewHolder.tvName.setText(itemsBean.getName());
        myViewHolder.tvGameName.setText(itemsBean.getGameName());


        /**
         * 根据直播等级来选择背景, 以及显示主播等级
         */
        switch (itemsBean.getGrade() / 10) {
            case 0:
                myViewHolder.llHost.setBackgroundResource(R.mipmap.ic_zhubo_lv_bg_1);
                myViewHolder.tvGrade.setText(String.valueOf(itemsBean.getGrade()));
                break;
            case 1:
                myViewHolder.llHost.setBackgroundResource(R.mipmap.ic_zhubo_lv_bg_2);
                myViewHolder.tvGrade.setText(String.valueOf(itemsBean.getGrade()));
                break;
            case 2:
                myViewHolder.llHost.setBackgroundResource(R.mipmap.ic_zhubo_lv_bg_3);
                myViewHolder.tvGrade.setText(String.valueOf(itemsBean.getGrade()));
                break;
            case 3:
                myViewHolder.llHost.setBackgroundResource(R.mipmap.ic_zhubo_lv_bg_4);
                myViewHolder.tvGrade.setText(String.valueOf(itemsBean.getGrade()));
                break;
            default:
                myViewHolder.llHost.setBackgroundResource(R.mipmap.ic_zhubo_lv_bg_5);
                myViewHolder.tvGrade.setText(String.valueOf(itemsBean.getGrade()));
                break;
        }

        return convertView;
    }

    private class MyViewHolder {

        private final ImageView imgHost;
        private final TextView tvName;
        private final TextView tvGameName;
        private final TextView tvGrade;
        private final LinearLayout llHost;

        public MyViewHolder(View convertView) {
            imgHost = (ImageView) convertView.findViewById(R.id.host_img);
            tvName = (TextView) convertView.findViewById(R.id.name_tv);
            tvGameName = (TextView) convertView.findViewById(R.id.gamename_tv);
            tvGrade = (TextView) convertView.findViewById(R.id.host_ll_tv);
            llHost = (LinearLayout) convertView.findViewById(R.id.host_ll);
        }
    }
}
