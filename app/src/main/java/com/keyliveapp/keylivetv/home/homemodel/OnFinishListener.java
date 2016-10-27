package com.keyliveapp.keylivetv.home.homemodel;

import com.keyliveapp.keylivetv.bean.HomeBean;

/**
 * Created by dllo on 16/10/26.
 */

public interface OnFinishListener {
    void  onFinished(HomeBean homeBean);
    void onError();
}
