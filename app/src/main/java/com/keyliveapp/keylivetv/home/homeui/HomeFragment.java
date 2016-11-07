package com.keyliveapp.keylivetv.home.homeui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DomainBean;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.classify.ClassifyClickInActivity;
import com.keyliveapp.keylivetv.home.homepresenter.HomePresenter;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeContentClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeTitleClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnLiveRecChannelListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnLiveRecItemClickListener;
import com.keyliveapp.keylivetv.livetv.VideoViewBuffer;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by dllo on 16/10/22.
 */
public class HomeFragment extends BaseFragment implements IHomeView {

    private RecyclerView mHomeRecycler;
    private Banner homeBanner;
    private LinearLayout homeScrollView;
    private ImageButton homeSearch;
    private ProgressDialog mDialog;
    private HomePresenter mPresenter;
    private TextView bannerTitle;
    public static final String URL_BEFORE1 = "https://a4.plu.cn/api/streams?start-index=";
    public static final String URL_BEFORE2 = "&max-results=30&game=";

    @Override
    protected int setLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {

        mDialog = createDialog();
        mHomeRecycler = getViewLayout(R.id.home_recyclerview);
        homeBanner = getViewLayout(R.id.home_banner);
        homeScrollView = getViewLayout(R.id.home_quickbtn_scrollview);
        homeSearch = getViewLayout(R.id.home_search);
        bannerTitle = getViewLayout(R.id.banner_title);

    }

    @Override
    protected void initDate() {

        mPresenter = new HomePresenter(this);
        mPresenter.startPresenterRequest(URLvalues.HOME_PAGE_URL);

    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        mDialog.dismiss();
    }

    @Override
    public void onResponse(HomeBean homeBean) {

        showBanner(homeBean);
        showHoriScrollView(homeBean);
        showRecyclerView(homeBean);

    }


    @Override
    public void onError() {
        Toast.makeText(mContext, "loading mistake", Toast.LENGTH_SHORT).show();
    }

    private ProgressDialog createDialog() {

        ProgressDialog dialog = new ProgressDialog(getContext());
        dialog.setTitle("loading...");
        dialog.setMessage("please waiting for a moment");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return dialog;


    }


    private void showHoriScrollView(final HomeBean homeBean) {

        for (int i = 0; i < homeBean.getData().getQuickbutton().size(); i++) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.home_scrollview_imgs, homeScrollView, false);
            ImageView imgs = (ImageView) view.findViewById(R.id.home_scrollview_pic);

            imgs.setAdjustViewBounds(true);
            Glide.with(getContext())
                    .load(homeBean.getData().getQuickbutton().get(i).getImage())
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 30, 0, RoundedCornersTransformation.CornerType.ALL))
                    .into(imgs);
            homeScrollView.addView(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String gameid = homeBean.getData().getQuickbutton().get(finalI).getHrefTarget();
                    String title = homeBean.getData().getQuickbutton().get(finalI).getTitle();
                    Toast.makeText(mContext, title, Toast.LENGTH_SHORT).show();
                    jumpToClassifyClickIn(title, gameid, URLvalues.CLASSIFY_URL_BEHIND);

                }

            });

        }

    }


    private void showBanner(final HomeBean homeBean) {

        List<String> bannerImgSrc = new ArrayList<>();
        for (int i = 0; i < homeBean.getData().getBanner().size(); i++) {
            bannerImgSrc.add(homeBean.getData().getBanner().get(i).getImage());
            Log.d("HomeFragment", homeBean.getData().getBanner().get(i).getTitle());
        }
//        homeBanner.setBannerAnimation(Transformer.Tablet);
        homeBanner.setImages(bannerImgSrc);
        homeBanner.setBannerStyle(Banner.SCROLL_INDICATOR_BOTTOM);
        homeBanner.setDelayTime(3500);
        homeBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bannerTitle.setText(homeBean.getData().getBanner().get((position - 1) % 5).getTitle());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        homeBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void showRecyclerView(final HomeBean homeBean) {

        List<HomeBean.DataBean.ColumnsBean> columnsBean;
        columnsBean = homeBean.getData().getColumns();
        HomeRvAdapter adapter = new HomeRvAdapter(getContext(), columnsBean);
        mHomeRecycler.setAdapter(adapter);


        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);

        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position < 1 || position % 5 == 1 ? 2 : 1;
            }
        });
        mHomeRecycler.setLayoutManager(manager);


        //livechannel点击
        adapter.setChannelListener(new OnLiveRecChannelListener() {
            @Override
            public void liveRecChannelClicked() {
                Log.d("liveTitleClicked", "channel");
                jumpToClassifyClickIn("正在直播", "0", "&sort-by=views&version=3.7.0&device=4&packageId=1");

            }
        });
        //liveitem  点击
        adapter.setLiveRecItemClickListener(new OnLiveRecItemClickListener() {
            @Override
            public void liveItemClicked(int i) {
                String domain = homeBean.getData().getColumns().get(0).getRooms().get(i).getChannel().getDomain();
                startLiveTv(domain);

            }

        });
        //title点击
        adapter.setTitleClickListener(new OnHomeTitleClickListener() {
            @Override
            public void titleClicked(int titlePosition) {
                switch (titlePosition) {
                    case 1://随拍
                        jumpToClassifyClickIn("龙珠随拍", "119", "&sort-by=views&version=3.7.0&device=4&packageId=1");
                        break;
                    case 2://女神
                        jumpToClassifyClickIn("龙珠女神", "0", "&sort-by=belle&version=3.7.0&device=4&packageId=1");
                        break;
                    case 3://手游
                        jumpToClassifyClickIn("手机游戏", "88", "&sort-by=views&version=3.7.0&device=4&packageId=1");
                        break;
                    case 4://单机
                        jumpToClassifyClickIn("单机主机", "90", "&sort-by=views&version=3.7.0&device=4&packageId=1");
                        break;
                    case 5://竞技
                        jumpToClassifyClickIn("竞技游戏", "149", "&sort-by=views&version=3.7.0&device=4&packageId=1");
                        break;
                    case 6://网络
                        jumpToClassifyClickIn("网络游戏", "150", "&sort-by=views&version=3.7.0&device=4&packageId=1");
                        break;
                }
            }
        });
        //content点击
        adapter.setContentClickListener(new OnHomeContentClickListener() {
            @Override
            public void contentClicked(int titlePosition, int contentPosition) {

                String domain = homeBean.getData().getColumns().get(titlePosition).getRooms().get(contentPosition)
                        .getChannel().getDomain();
                if (domain != null) {
                    startLiveTv(domain);
                } else {
                    Toast.makeText(mContext, "链接不存在或网络数据错误", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void startLiveTv(String domain) {
        String domainUrl = URLvalues.DOMAIN_URL_FRONT + domain + URLvalues.DOMAIN_URL_BEHIND;
        HttpManager.getInstance().getRequest(domainUrl, DomainBean.class, new OnCompletedListener<DomainBean>() {
            @Override
            public void onCompleted(DomainBean result) {
                String roomid = result.getBroadcast().getRoomId() + "";

                Intent intent = new Intent(getActivity(), VideoViewBuffer.class);
                intent.putExtra("roomid", roomid);
                startActivity(intent);
            }

            @Override
            public void onFailed() {

            }
        });


    }


    private void jumpToClassifyClickIn(String title, String gameid
            , String urlbehind) {
        Intent intent = new Intent(getActivity(), ClassifyClickInActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("urlbefore1", URL_BEFORE1);
        intent.putExtra("gameid", gameid);
        intent.putExtra("urlbefore2", URL_BEFORE2);
        intent.putExtra("urlbehind", urlbehind);
        startActivity(intent);
    }
}
