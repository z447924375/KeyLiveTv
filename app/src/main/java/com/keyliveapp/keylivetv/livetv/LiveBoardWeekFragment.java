package com.keyliveapp.keylivetv.livetv;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by dllo on 16/11/7.
 */
public class LiveBoardWeekFragment extends BaseFragment {
    private ListView liveBoardLv;


    @Override
    protected int setLayout() {
        return R.layout.live_board_week;
    }

    @Override
    protected void initView() {
        liveBoardLv = getViewLayout(R.id.live_board_week_lv);
    }

    @Override
    protected void initDate() {
        Bundle bundle = getArguments();
        String boardurl = bundle.getString("url");
        String stream = apply(boardurl);


        Log.d("sss", stream);


    }


    public String apply(String s) {

        StringBuilder builder = null;
        try {
            URL url = new URL(s);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream in = conn.getInputStream();
            BufferedReader bf = new BufferedReader(new InputStreamReader(in));
            String str = "";
            builder = new StringBuilder();
            while ((str = bf.readLine()) != null) {
                builder.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

}
