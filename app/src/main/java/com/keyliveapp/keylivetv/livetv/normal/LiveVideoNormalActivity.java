
package com.keyliveapp.keylivetv.livetv.normal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
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
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

import java.util.ArrayList;
import java.util.List;

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
    private DisplayMetrics mDm;


    @Override
    protected int setLayout() {
        return R.layout.video_mormal_view;
    }

    @Override
    protected void initView() {
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
        DomainBean bean = (DomainBean) intent.getSerializableExtra("domain");

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
        btnBack.setOnClickListener(this);
        btnFull.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(LiveVideoNormalActivity.this, "铺满", Toast.LENGTH_SHORT).show();
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
                    List<String> listRoomId = new ArrayList<String>();
                    listRoomId.add(roomid);
//                    DBTools.getInstance().insert(listRoomId);
//                    DBTools.getInstance().getAll(new DBTools.QueryListener<String>() {
//                        @Override
//                        public void onQuery(ArrayList<String> str) {
//                            Log.d("LiveVideoNormalActivity", "str:" + str);
//                        }
//                    },null);

                    Toast.makeText(LiveVideoNormalActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
                } else {
//                    DBTools.getInstance().delete(roomid);
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
//    @Override
//    protected void onResume() {
//
//        super.onResume();
//    }

    public void enterFullScreen() {

        //横屏
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        mDm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(mDm);
        mCenterLayout.setLayoutParams(new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        mVideoView.setLayoutParams(new ViewGroup.LayoutParams(mDm.widthPixels, mDm.heightPixels));


    }

    public void quitFullScreen() {
        //竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


}
