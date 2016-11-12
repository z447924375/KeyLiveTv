package com.keyliveapp.keylivetv.search.result.viedo;

import android.content.Context;
import android.widget.AbsListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.baseclass.BaseToast;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;

/**
 * Created by dllo on 16/11/9.
 */

public class ViedoFragment extends BaseFragment {

    private ViedoBean viedoBean;
    private PullToRefreshListView pullToRefreshListView;
    private TextView tvViedo;
    private Context mContext;

    private String VIEDO_URL_BEFORE1 = "http://searchapi.plu.cn/api/search/media?gameId=0&roomId=0&pageIndex=";
    private String VIEDO_URL_BEFORE2 = "&pageSize=20&sortStr=relate&title=";
    private String VIEDO_URL_BEHIND = "&from=qq&version=3.7.0&device=4&packageId=1";
    private int pageIndex = 1;
    private String searchWord;


    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public void setViedoBean(ViedoBean viedoBean, String word) {
        this.viedoBean = viedoBean;
        this.searchWord = word;
    }

    @Override
    protected int setLayout() {
        return R.layout.search_result_viedo;
    }

    @Override
    protected void initView() {

        pullToRefreshListView = getViewLayout(R.id.lv_viedo);
        tvViedo = getViewLayout(R.id.tv_viedo);

    }

    @Override
    protected void initDate() {

        String totalItems = "共搜到" + viedoBean.getTotalItems() + "个视频";
        tvViedo.setText(totalItems);

        final ViedoViewAdapter viedoViewAdapter = new ViedoViewAdapter();
        viedoViewAdapter.setViedoBean(viedoBean);
        pullToRefreshListView.setAdapter(viedoViewAdapter);

        pullToRefreshListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    /**
                     * 拼接下一个资源接口
                     */
                    String ur = VIEDO_URL_BEFORE1 + pageIndex + VIEDO_URL_BEFORE2 + searchWord + VIEDO_URL_BEHIND;

                    /**
                     * 1.请求资源
                     * 2.传入适配器
                     * 3.刷新适配器
                     */
                    HttpManager.getInstance().getRequest(ur, ViedoBean.class, new OnCompletedListener<ViedoBean>() {
                        @Override
                        public void onCompleted(ViedoBean result) {
                            if (0 != result.getItems().size()) {
                                viedoViewAdapter.setListBean(result);
                                viedoViewAdapter.notifyDataSetChanged();
                                pageIndex ++;
                            } else {
                                BaseToast.showToast(getActivity(), "到底啦");
                                BaseToast.showToast(getActivity(), "视频到底啦");
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
