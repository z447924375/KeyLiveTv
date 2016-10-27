package com.keyliveapp.keylivetv.home.homemodel;

import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;

/**
 * Created by dllo on 16/10/26.
 */

public class HomeModelImpl implements IHomeModel {
    @Override
    public void startRequest(String urlStr, final OnFinishListener listener) {
        HttpManager.getInstance().getRequest(urlStr, HomeBean.class, new OnCompletedListener<HomeBean>() {
            @Override
            public void onCompleted(HomeBean result) {
                if (result == null) {
                    listener.onError();
                    return;
                }
                listener.onFinished(result);

            }

            @Override
            public void onFailed() {
                listener.onError();
            }
        });
    }

}
