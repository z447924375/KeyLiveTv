/*
 * Copyright (C) 2013 yixia.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.keyliveapp.keylivetv.livetv;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.bean.DomainBean;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoViewBuffer extends BaseActivity {

    /**
     * TODO: Set the path variable to a streaming video URL or a local media file
     * path.
     */
    private String path;
    private Uri uri;
    private VideoView mVideoView;
    private ProgressBar pb;
    private TextView downloadRateView, loadRateView;
    private String mStreamUrl;


    @Override
    protected int setLayout() {
        return R.layout.videobuffer;
    }

    @Override
    protected void initView() {
        Vitamio.isInitialized(getApplicationContext());
        mVideoView = (VideoView) findViewById(R.id.buffer);
        pb = (ProgressBar) findViewById(R.id.probar);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
    }

    //  compile 'eu.the4thfloor.volley:com.android.volley:2015.05.28'
    @Override
    protected void inidate() {
        Intent intent = getIntent();
        String domain = intent.getExtras().getString("domain");
        String domainUrl = URLvalues.DOMAIN_URL_FRONT + domain + URLvalues.DOMAIN_URL_BEHIND;
        Observable.just(domainUrl).map(new Function<String, String>() {
            private String mRoomid;

            @Override
            public String apply(String s) throws Exception {


                HttpManager.getInstance().getRequest(s, DomainBean.class, new OnCompletedListener<DomainBean>() {
                    @Override
                    public void onCompleted(DomainBean result) {
                        mRoomid = result.getBroadcast().getRoomId() + "";

                    }

                    @Override
                    public void onFailed() {

                    }
                });

                return mRoomid;

            }
        }).map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                Log.d("kind", s);

                return null;
            }
        })


//        if (uri != null) {
//            mVideoView.setVideoURI(uri);
//            mVideoView.setMediaController(new MediaController(this));
//            mVideoView.requestFocus();
//
//            mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.setPlaybackSpeed(1.0f);
//                }
//            });
//        } else {
//            Log.d("VideoViewBuffer", "链接不存在");
//        }

    }


}
