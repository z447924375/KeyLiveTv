package com.keyliveapp.keylivetv.baseclass;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

import butterknife.ButterKnife;

/**
 * Created by dllo on 16/10/20.
 */

public abstract class BaseActivity extends FragmentActivity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "imageloader/Cache");
        ImageLoaderConfiguration configuration =
                new ImageLoaderConfiguration
                        .Builder(this)
                        .threadPoolSize(3).diskCacheFileCount(100)
                        .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                        .diskCache(new UnlimitedDiscCache(cacheDir))
                        .memoryCache(new WeakMemoryCache())
                        .build();
        ImageLoader.getInstance().init(configuration);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(setLayout());
        ButterKnife.bind(this);
        initView();
        inidate();
    }

    /**
     *  设置布局
     * @return 布局文件id
     */
    protected abstract  int setLayout();

    /**
     * 初始化
     */
    protected abstract void initView();

    /**
     * 初始化数据
     */
    protected abstract void inidate();

    protected <T extends View> T bindView(int id){
        return (T)findViewById(id);
    }


}
