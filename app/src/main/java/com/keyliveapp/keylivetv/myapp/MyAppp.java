package com.keyliveapp.keylivetv.myapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/22.
 */

public class MyAppp extends Application{

    private static Context mContext;

    public static Context getmContext() {
        return MyAppp.mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
}
