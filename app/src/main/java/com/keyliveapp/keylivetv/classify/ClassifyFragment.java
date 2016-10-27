package com.keyliveapp.keylivetv.classify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.tools.HttpManager;
import com.keyliveapp.keylivetv.tools.OnCompletedListener;

/**
 * Created by dllo on 16/10/22.
 */
public class ClassifyFragment extends BaseFragment {

    private RecyclerView recyclerView;

    private String URL_CLASSIFY = "https://a4.plu.cn/api/games/all?version=3.7.0&device=4&packageId=1";

    @Override
    protected int setLayout() {
        return R.layout.classify_fragment;
    }

    @Override
    protected void initView() {

        recyclerView = getViewLayout(R.id.rv_classify_base);

    }

    @Override
    protected void initDate() {

        HttpManager.getInstance().getRequest(URL_CLASSIFY, ClassifyBean.class, new OnCompletedListener<ClassifyBean>() {
            @Override
            public void onCompleted(ClassifyBean result) {

                ClassifyFragmentAdapter classifyFragmentAdapter = new ClassifyFragmentAdapter();
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(classifyFragmentAdapter);

            }

            @Override
            public void onFailed() {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.
    }
}
