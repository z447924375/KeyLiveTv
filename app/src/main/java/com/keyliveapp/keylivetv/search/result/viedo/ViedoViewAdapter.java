package com.keyliveapp.keylivetv.search.result.viedo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/11.
 */

public class ViedoViewAdapter extends BaseAdapter {

    private ViedoBean viedoBean;
    private List<ViedoBean.ItemsBean> listBean;

    public void setListBean(ViedoBean viedoBean) {

        for (int i = 0; i < viedoBean.getItems().size(); i++) {
            listBean.add(viedoBean.getItems().get(i));
        }

    }

    public void setViedoBean(ViedoBean viedoBean) {
        this.viedoBean = viedoBean;
        listBean = new ArrayList<>();
        for (int i = 0; i < viedoBean.getItems().size(); i++) {
            listBean.add(viedoBean.getItems().get(i));
        }
    }

    @Override
    public int getCount() {
        return listBean.size();
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

        MyViewHodler myViewHodler = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_result_viedo_view, parent, false);
            myViewHodler = new MyViewHodler(convertView);
            convertView.setTag(myViewHodler);
        } else {
            myViewHodler = (MyViewHodler) convertView.getTag();
        }

        ViedoBean.ItemsBean bean = listBean.get(position);

        Glide.with(parent.getContext())
                .load(bean.getCover())
                .into(myViewHodler.imgViedo);

        myViewHodler.tvViedoName.setText(bean.getTitle());

        myViewHodler.tvViedoTime.setText(getItme(bean.getTimeSpan()));

        String s = "观看人数:   " + bean.getTotalViews();
        myViewHodler.tvViedoManCount.setText(s);

        return convertView;
    }

    public String getItme(int itme) {
        int hour = itme / 60;
        int min = itme % 60;
        String s = "时长:   " + hour + " : " + min;
        return s;
    }

    private class MyViewHodler {

        private final ImageView imgViedo;
        private final TextView tvViedoName;
        private final TextView tvViedoTime;
        private final TextView tvViedoManCount;

        public MyViewHodler(View convertView) {

            imgViedo = (ImageView) convertView.findViewById(R.id.viedo_img);
            tvViedoName = (TextView) convertView.findViewById(R.id.viedo_name);
            tvViedoTime = (TextView) convertView.findViewById(R.id.viedo_time_tv);
            tvViedoManCount = (TextView) convertView.findViewById(R.id.viedo_mancount);

        }
    }
}
