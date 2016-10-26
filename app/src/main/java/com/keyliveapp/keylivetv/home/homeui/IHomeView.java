package com.keyliveapp.keylivetv.home.homeui;

import com.keyliveapp.keylivetv.bean.HomeBean;

/**
 * Created by dllo on 16/10/26.
 */

public interface IHomeView {
    void showDialog();

    void dismissDialog();

    void onResponse(HomeBean homeBean);

    void onError();

}
