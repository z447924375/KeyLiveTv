package com.keyliveapp.keylivetv.search.result.host;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.baseclass.BaseToast;
import com.keyliveapp.keylivetv.bean.HostBean;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

/**
 * Created by dllo on 16/11/9.
 */

public class HostFragment extends BaseFragment {

    private PullToRefreshListView lvHost;

    private TextView tvHost;

    private HostBean hostBean;

    private Context mContext;

    private String HOST_URL_BEFORE1 = "http://searchapi.plu.cn/api/search/room?gameId=0&pageIndex=";
    private String HOST_URL_BEFORE2 = "&pageSize=20&title=";
    private int pageIndex = 1;
    private String searchWord;
    private String HOST_URL_BEHIND = "&roomId=0&type=2&from=applive&version=3.7.0&device=4&packageId=1";

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setHostBean(HostBean hostBean, String searchWord) {
        this.hostBean = hostBean;
        this.searchWord = searchWord;
    }

    @Override
    protected int setLayout() {
        return R.layout.search_result_host;
    }

    @Override
    protected void initView() {
        lvHost = getViewLayout(R.id.lv_host);
        tvHost = getViewLayout(R.id.tv_host);
    }

    @Override
    protected void initDate() {


        String totalItems = "共搜到" + hostBean.getTotalItems() + "个主播";
        tvHost.setText(totalItems);

        final HostViewAdapter pullToRefreshListViewAdapter = new HostViewAdapter();
        pullToRefreshListViewAdapter.setHostBean(hostBean);
        lvHost.setAdapter(pullToRefreshListViewAdapter);

        lvHost.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
               if (firstVisibleItem + visibleItemCount == totalItemCount) {

                   /**
                    * 拼接下一个资源接口
                    */

                   String ur = HOST_URL_BEFORE1 + pageIndex + HOST_URL_BEFORE2 + searchWord + HOST_URL_BEHIND;

                   /**
                    * 1.请求资源
                    * 2.传入适配器
                    * 3.刷新适配器
                    */

                   HttpManager.getInstance().getRequest(ur, HostBean.class, new OnCompletedListener<HostBean>() {
                       @Override
                       public void onCompleted(HostBean result) {
                           if (0 != result.getItems().size()) {
                               pullToRefreshListViewAdapter.setListbBean(result);
                               pullToRefreshListViewAdapter.notifyDataSetChanged();
                               pageIndex ++;
                           } else {
                                BaseToast.showToast(getActivity(), "到底啦");
                           }
                       }

                       @Override
                       public void onFailed() {

                       }
                   });
               }


            }
        });

    }
}
