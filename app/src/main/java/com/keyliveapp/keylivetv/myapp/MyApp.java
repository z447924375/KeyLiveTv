package com.keyliveapp.keylivetv.myapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by dllo on 16/10/22.
 */

public class MyApp extends Application{

    private static Context mContext;

    public static Context getmContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        this.mContext = this;
    }
}
