package com.keyliveapp.keylivetv.baseclass;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dllo on 16/10/20.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    protected FragmentManager mFragmentManager;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    protected abstract int setLayout();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setLayout(), container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initDate();
    }

    protected <T extends View> T getViewLayout(int id) {
        return (T) getView().findViewById(id);
    }

    protected <T extends View> T getViewLayout(int id, View v) {
        return (T) getView().findViewById(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void changeFragmentTo(Fragment fragment, int framlayoutId) {
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().replace(framlayoutId, fragment).commit();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void removeFragment(Fragment fragment){
        FragmentManager fragmentManager = getChildFragmentManager();
        fragmentManager.beginTransaction().remove(fragment).commit();
    }

    protected abstract void initView();

    protected abstract void initDate();
}
