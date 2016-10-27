package com.keyliveapp.keylivetv.home.homepresenter;

import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.home.homemodel.HomeModelImpl;
import com.keyliveapp.keylivetv.home.homemodel.IHomeModel;
import com.keyliveapp.keylivetv.home.homemodel.OnFinishListener;
import com.keyliveapp.keylivetv.home.homeui.IHomeView;

/**
 * Created by dllo on 16/10/26.
 */

public class HomePresenter {
    private IHomeView mView;
    private IHomeModel mModel;

    public HomePresenter(IHomeView view) {
        mView = view;
        mModel=new HomeModelImpl();
    }

    public void startPresenterRequest(String urlStr){

        mView.showDialog();

        mModel.startRequest(urlStr, new OnFinishListener() {
            @Override
            public void onFinished(HomeBean homeBean) {
                mView.dismissDialog();
                mView.onResponse(homeBean);
            }

            @Override
            public void onError() {

            }
        });
    }

}
