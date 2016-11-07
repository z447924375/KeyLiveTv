
package com.keyliveapp.keylivetv.livetv;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.bean.LiveStreamBean;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

import java.util.ArrayList;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.VideoView;

public class VideoViewBuffer extends BaseActivity implements MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, View.OnClickListener {


    private VideoView mVideoView;
    private TextView downloadRateView, loadRateView;
    private ImageButton btnBack, btnFull;
    private TabLayout liveTab;
    private ViewPager liveVp;


    @Override
    protected int setLayout() {
        return R.layout.videobuffer;
    }

    @Override
    protected void initView() {
        Vitamio.isInitialized(getApplicationContext());
        mVideoView = (VideoView) findViewById(R.id.buffer);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        btnBack = (ImageButton) findViewById(R.id.live_back);
        btnFull = (ImageButton) findViewById(R.id.live_full);
        liveTab = (TabLayout) findViewById(R.id.live_tab);
        liveVp = (ViewPager) findViewById(R.id.live_vp);
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
                    if (streamUrl != null) {
                        mVideoView.setVideoURI(Uri.parse(streamUrl));

                    } else {
                        Toast.makeText(VideoViewBuffer.this, "链接无效", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed() {
                    Toast.makeText(VideoViewBuffer.this, "解析错误", Toast.LENGTH_SHORT).show();
                }
            });
        }

        mVideoView.requestFocus();
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnPreparedListener(this);
        btnFull.setOnClickListener(this);
        btnBack.setOnClickListener(this);



        ArrayList<Fragment> fragments = new ArrayList<>();
        LiveBoardFragment liveBoardFragment = new LiveBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomid",roomid);
        liveBoardFragment.setArguments(bundle);
        fragments.add(new LiveChatFragment());
        fragments.add(new LiveAnchorFragment());
        fragments.add(liveBoardFragment);

        VideoViewVpAdapter adapter = new VideoViewVpAdapter(getSupportFragmentManager(),fragments);
        liveVp.setAdapter(adapter);
        liveTab.setupWithViewPager(liveVp);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_back:
                this.finish();
                break;
            case R.id.live_full:
                Toast.makeText(this, "铺满全屏未设置", Toast.LENGTH_SHORT).show();
                break;

        }

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setPlaybackSpeed(1.0f);
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        switch (what) {
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                if (mVideoView.isPlaying()) {
                    downloadRateView.setText("");
                    loadRateView.setText("");
                    downloadRateView.setVisibility(View.VISIBLE);
                    loadRateView.setVisibility(View.VISIBLE);
                    mVideoView.pause();
                }
                break;
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:

                downloadRateView.setVisibility(View.GONE);
                loadRateView.setVisibility(View.GONE);
                mVideoView.start();
                break;
            case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                downloadRateView.setText("" + extra + "kb/s" + "  ");
                break;
        }
        return true;
    }


    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        loadRateView.setText(percent + "%");
    }


}
