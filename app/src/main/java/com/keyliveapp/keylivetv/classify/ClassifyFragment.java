package com.keyliveapp.keylivetv.classify;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.ClassifyBean;
import com.keyliveapp.keylivetv.search.history.SearchActivity;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/10/22.
 */
public class ClassifyFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private String URL_CLASSIFY = "https://a4.plu.cn/api/games/all?version=3.7.0&device=4&packageId=1";

    private ArrayList<String> chennelsIcon;
    private ArrayList<String> channelsName;
    private ArrayList<String> channelsId;
    private ArrayList<String> recommendsIcon;
    private ArrayList<String> recommendsName;
    private ArrayList<String> recommendsId;
    private ImageView imgSearch;

    @Override
    protected int setLayout() {
        return R.layout.classify_fragment;
    }

    @Override
    protected void initView() {

        recyclerView = getViewLayout(R.id.rv_classify_base);

        imgSearch = getViewLayout(R.id.img_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void initDate() {

        HttpManager.getInstance().getRequest(URL_CLASSIFY, ClassifyBean.class, new OnCompletedListener<ClassifyBean>() {
            @Override
            public void onCompleted(ClassifyBean result) {

                ClassifyFragmentAdapter classifyFragmentAdapter = new ClassifyFragmentAdapter();

                getData(result);

                sendData(classifyFragmentAdapter);


                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(classifyFragmentAdapter);

            }

            @Override
            public void onFailed() {

            }
        });

    }

    private void sendData(ClassifyFragmentAdapter classifyFragmentAdapter) {
        classifyFragmentAdapter.setChannelsId(channelsId);
        classifyFragmentAdapter.setChannelsName(channelsName);
        classifyFragmentAdapter.setChennelsIcon(chennelsIcon);
        classifyFragmentAdapter.setRecommendsIcon(recommendsIcon);
        classifyFragmentAdapter.setRecommendsId(recommendsId);
        classifyFragmentAdapter.setRecommendsName(recommendsName);
    }

    private void getData(ClassifyBean result) {

        recommendsId =
                new ArrayList<>();
        recommendsName =
                new ArrayList<>();
        recommendsIcon =
                new ArrayList<>();

        channelsId =
                new ArrayList<>();
        channelsName =
                new ArrayList<>();
        chennelsIcon =
                new ArrayList<>();

        List<ClassifyBean.DataBean.RecommendBean> recommends = result.getData().getRecommend();
        List<ClassifyBean.DataBean.ItemsBean> items = result.getData().getItems();

        for (ClassifyBean.DataBean.RecommendBean recommend : recommends) {
            ClassifyBean.DataBean.RecommendBean.GameBean game = recommend.getGame();
            recommendsId.add(game.getId());
            recommendsName.add(game.getName());
            recommendsIcon.add(game.getLogo());
        }

        for (ClassifyBean.DataBean.ItemsBean item : items) {
            ClassifyBean.DataBean.ItemsBean.GameBean game = item.getGame();
            channelsId.add(String.valueOf(game.getId()));
            channelsName.add(game.getName());
            chennelsIcon.add(game.getLogo());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.
    }
}
