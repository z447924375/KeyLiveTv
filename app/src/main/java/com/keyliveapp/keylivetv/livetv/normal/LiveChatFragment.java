package com.keyliveapp.keylivetv.livetv.normal;

import android.widget.ListView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;

/**
 * Created by dllo on 16/11/7.
 */
public class LiveChatFragment extends BaseFragment {
    private ListView liveChatLv;

    @Override
    protected int setLayout() {
        return R.layout.live_chat;
    }

    @Override
    protected void initView() {
        liveChatLv = getViewLayout(R.id.live_chat_lv);
    }

    @Override
    protected void initDate() {

    }
}
