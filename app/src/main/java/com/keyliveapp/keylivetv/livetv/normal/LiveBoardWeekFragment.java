package com.keyliveapp.keylivetv.livetv.normal;

import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.baseclass.BaseListViewAdapter;
import com.keyliveapp.keylivetv.baseclass.BaseViewHolder;
import com.keyliveapp.keylivetv.bean.LiveBoardBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

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
        final String boardurl = bundle.getString("url");

        
        Observable.just(boardurl)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return getHttpStream(s);
                    }
                })
                .map(new Function<String, List<LiveBoardBean>>() {
                    @Override
                    public List<LiveBoardBean> apply(String s) throws Exception {
                        Type type = new TypeToken<List<LiveBoardBean>>(){}.getType();
                        return new Gson().fromJson(s, type);
                    }
                }).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<LiveBoardBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(final List<LiveBoardBean> value) {

                        liveBoardLv.setAdapter(new BaseListViewAdapter<LiveBoardBean>(mContext, value, R.layout.liveboard_item) {
                            @Override
                            public void convent(BaseViewHolder viewHolder, LiveBoardBean liveBoardBean, int position) {
                                viewHolder.setCirImage(R.id.board_pic, value.get(position).getAvatar());
                                viewHolder.setText(R.id.board_name, value.get(position).getUserName());
                                viewHolder.setText(R.id.board_count, value.get(position).getCount() + "");
                            }


                        });
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }


    public String getHttpStream(String s) {

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
        String result = builder.toString();
        return result;
    }

}
