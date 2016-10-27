package com.keyliveapp.keylivetv.classify;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;

/**
 * Created by dllo on 16/10/22.
 */
public class ClassifyFragment extends BaseFragment {

    private RecyclerView recyclerView;

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

        ClassifyFragmentAdapter classifyFragmentAdapter = new ClassifyFragmentAdapter();
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(classifyFragmentAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.
    }
}
