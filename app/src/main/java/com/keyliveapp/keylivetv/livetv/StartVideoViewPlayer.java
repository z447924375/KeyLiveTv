package com.keyliveapp.keylivetv.livetv;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.keyliveapp.keylivetv.bean.DomainBean;
import com.keyliveapp.keylivetv.livetv.full.LiveVideoFullActivity;
import com.keyliveapp.keylivetv.livetv.normal.LiveVideoNormalActivity;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/11/16.
 */

public class StartVideoViewPlayer {

    private Context mContext;

    public StartVideoViewPlayer(Context context) {
        mContext = context;
    }

    private static StartVideoViewPlayer sViewPlayer;

    public static StartVideoViewPlayer getInstance(Context context) {
        if (sViewPlayer == null) {
            synchronized (DomainBean.class) {
                if (sViewPlayer == null) {
                    sViewPlayer = new StartVideoViewPlayer(context);
                }
            }
        }
        return sViewPlayer;
    }
    public void startBroadCast(String domain) {
        String domainUrl = URLvalues.DOMAIN_URL_FRONT + domain + URLvalues.DOMAIN_URL_BEHIND;
        HttpManager.getInstance().getRequest(domainUrl, DomainBean.class, new OnCompletedListener<DomainBean>() {
            @Override
            public void onCompleted(DomainBean result) {
                String roomid = result.getBroadcast().getRoomId() + "";


                if (result.isIsBroadcasting()) {

                    if (result.getBroadcast().getGameId() == 127) {
                        Intent intent = new Intent(mContext, LiveVideoFullActivity.class);
                        intent.putExtra("roomid", roomid);
                        intent.putExtra("domain", result);
                        mContext.startActivity(intent);
                    }else {
                        Intent intent1 = new Intent(mContext, LiveVideoNormalActivity.class);
                        intent1.putExtra("roomid", roomid);
                        intent1.putExtra("domain", result);
                        mContext.startActivity(intent1);
                    }
                }else {
                    Toast.makeText(mContext, "来早啦~~~  该主播未开播~~~~~", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailed() {

            }
        });

    }
}
