package com.keyliveapp.keylivetv.search.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseToast;

import java.util.ArrayList;

/**
 * Created by dllo on 16/11/3.
 */
public class HistoryAdapter extends BaseAdapter{

    private ArrayList<String> words = new ArrayList<>();

    private Context mContext;


    @Override
    public int getCount() {
        return words.size();
    }

    public void setWords(ArrayList<String> wordss) {
        words.clear();
        this.words.addAll(wordss);
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public void delete(int position) {
        words.remove(position);
        notifyDataSetChanged();
        BaseToast.showToast(mContext, "删除成功");
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MyViewHolder myViewHolder = null;

        if (mContext == null) {
            mContext = parent.getContext();
        }

        if (convertView == null) {

            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_history, parent, false);

            myViewHolder = new MyViewHolder(convertView);

            convertView.setTag(myViewHolder);


        } else {
            myViewHolder = (MyViewHolder) convertView.getTag();
        }

        myViewHolder.historyTv.setText(words.get(position));

        switch (position) {
            case 0:
                myViewHolder.historyTip.setBackgroundResource(R.drawable.history_first); myViewHolder.historyTip.setText("" + 1);
                break;
            case 1:
                myViewHolder.historyTip.setBackgroundResource(R.drawable.history_double); myViewHolder.historyTip.setText("" + 2);
                break;
            case 2:
                myViewHolder.historyTip.setBackgroundResource(R.drawable.history_third); myViewHolder.historyTip.setText("" + 3);
                break;
            default:
                myViewHolder.historyTip.setBackgroundResource(R.drawable.history_another); myViewHolder.historyTip.setText("" + (position + 1));
                break;
        }


        return convertView;
    }

    private class MyViewHolder {

        private final TextView historyTip;
        private final TextView historyTv;

        public MyViewHolder(View convertView) {

            historyTip = (TextView) convertView.findViewById(R.id.history_tip);
            historyTv = (TextView) convertView.findViewById(R.id.history_word);


        }
    }
}
