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
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.bean.LiveStreamBean;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.MediaController;
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
//        pb = (ProgressBar) findViewById(R.id.probar);
//        downloadRateView = (TextView) findViewById(R.id.download_rate);
//        loadRateView = (TextView) findViewById(R.id.load_rate);
    }

    @Override
    protected void inidate() {
        Intent intent = getIntent();
        String roomid = intent.getExtras().getString("roomid");
        if (roomid != null) {
            String streamInfo = URLvalues.STREAM_URL_FRONT + roomid + URLvalues.STREAN_URL_BEHIND;
            HttpManager.getInstance().getRequest(streamInfo, LiveStreamBean.class, new OnCompletedListener<LiveStreamBean>() {
                @Override
                public void onCompleted(LiveStreamBean result) {
                    String streamUrl = result.getPlayLines().get(0).getUrls().get(2).getSecurityUrl();
                    Log.d("dfddfdfdf", streamUrl);
                    if (streamUrl!=null) {
                        mVideoView.setVideoURI(Uri.parse(streamUrl));
                        mVideoView.setMediaController(new MediaController(getApplicationContext()));
                        mVideoView.requestFocus();
                        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.setPlaybackSpeed(1.0f);
                            }
                        });
                    }else {
                        Toast.makeText(VideoViewBuffer.this, "链接无效", Toast.LENGTH_SHORT).show();
                    }



                }

                @Override
                public void onFailed() {

                }
            });


        }


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
