
package com.keyliveapp.keylivetv.livetv.normal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.bean.DomainBean;
import com.keyliveapp.keylivetv.bean.LiveStreamBean;
import com.keyliveapp.keylivetv.tools.db.DBTools;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;
import com.litesuits.orm.LiteOrm;

import java.util.ArrayList;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.Vitamio;
import io.vov.vitamio.widget.CenterLayout;
import io.vov.vitamio.widget.VideoView;

public class LiveVideoNormalActivity extends BaseActivity implements MediaPlayer.OnInfoListener,
        MediaPlayer.OnBufferingUpdateListener, MediaPlayer.OnPreparedListener, View.OnClickListener {


    private VideoView mVideoView;
    private TextView downloadRateView, loadRateView;
    private ImageButton btnBack;
    private CenterLayout mCenterLayout;
    private CheckBox like, btnFull;
    private TabLayout liveTab;
    private ViewPager liveVp;
    private CountDownTimer countDownTimer;
    private int clickedTime = 0;
    private LiteOrm mLiteOrm;


    @Override
    protected int setLayout() {
        return R.layout.video_mormal_view;
    }

    @Override
    protected void initView() {
        mLiteOrm = LiteOrm.newSingleInstance(this,"like.db");
        Vitamio.isInitialized(getApplicationContext());
        mVideoView = (VideoView) findViewById(R.id.buffer);
        downloadRateView = (TextView) findViewById(R.id.download_rate);
        loadRateView = (TextView) findViewById(R.id.load_rate);
        btnBack = (ImageButton) findViewById(R.id.live_back);
        btnFull = (CheckBox) findViewById(R.id.live_full);
        liveTab = (TabLayout) findViewById(R.id.live_tab);
        liveVp = (ViewPager) findViewById(R.id.live_vp);
        like = (CheckBox) findViewById(R.id.live_like);
        mCenterLayout = (CenterLayout) findViewById(R.id.centerLayout);

    }




    @Override
    protected void inidate() {


        Intent intent = getIntent();
        final String roomid = intent.getExtras().getString("roomid");
        final DomainBean bean = (DomainBean) intent.getSerializableExtra("domain");

        if (roomid != null) {
            String streamInfo = URLvalues.STREAM_URL_FRONT + roomid + URLvalues.STREAN_URL_BEHIND;
            HttpManager.getInstance().getRequest(streamInfo, LiveStreamBean.class, new OnCompletedListener<LiveStreamBean>() {
                @Override
                public void onCompleted(LiveStreamBean result) {
                    String streamUrl = result.getPlayLines().get(0).getUrls().get(2).getSecurityUrl();
                    if (streamUrl != null) {
                        mVideoView.setVideoURI(Uri.parse(streamUrl));

                    } else {
                        Toast.makeText(LiveVideoNormalActivity.this, "链接无效", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailed() {
                    Toast.makeText(LiveVideoNormalActivity.this, "解析错误", Toast.LENGTH_SHORT).show();
                }
            });
        }

        mVideoView.requestFocus();
        mVideoView.setOnBufferingUpdateListener(this);
        mVideoView.setOnInfoListener(this);
        mVideoView.setOnPreparedListener(this);

        mVideoView.setOnTouchListener(new View.OnTouchListener() {


            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        clickedTime++;
                        if (clickedTime % 2 == 1) {
                            btnFull.setVisibility(View.VISIBLE);
                            btnBack.setVisibility(View.VISIBLE);
                            countDownTimer = new CountDownTimer(5000, 1000) {
                                @Override
                                public void onTick(long millisUntilFinished) {
                                }
                                @Override
                                public void onFinish() {
                                    btnBack.setVisibility(View.INVISIBLE);
                                    btnFull.setVisibility(View.INVISIBLE);
                                    clickedTime++;
                                }
                            }.start();
                        }
                        if (clickedTime % 2 == 0) {
                            btnFull.setVisibility(View.INVISIBLE);
                            btnBack.setVisibility(View.INVISIBLE);
                            countDownTimer.cancel();
                        }
                        break;

                }
                return false;
            }
        });


        btnBack.setOnClickListener(this);
        btnFull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    enterFullScreen();
                } else {
                    quitFullScreen();
                }
            }
        });
        like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    DBTools.getInstance().insert(bean);

                    Toast.makeText(LiveVideoNormalActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LiveVideoNormalActivity.this, "已取消收藏", Toast.LENGTH_SHORT).show();
                }
            }
        });


        ArrayList<Fragment> fragments = new ArrayList<>();
        LiveAnchorFragment liveAnchorFragment = new LiveAnchorFragment();
        LiveBoardFragment liveBoardFragment = new LiveBoardFragment();
        Bundle bundle = new Bundle();
        bundle.putString("roomid", roomid);
        bundle.putSerializable("domain", bean);
        liveAnchorFragment.setArguments(bundle);
        liveBoardFragment.setArguments(bundle);
        fragments.add(liveAnchorFragment);
        fragments.add(liveBoardFragment);

        VideoViewVpAdapter adapter = new VideoViewVpAdapter(getSupportFragmentManager(), fragments);
        liveVp.setAdapter(adapter);
        liveTab.setupWithViewPager(liveVp);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.live_back:
                this.finish();
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


    public void enterFullScreen() {

        //横屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        mCenterLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT));
    }

    public void quitFullScreen() {
        //竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


}
