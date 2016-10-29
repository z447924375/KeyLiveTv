package com.keyliveapp.keylivetv.home.homeui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.classify.zxh.ClassifyClickInActivity;
import com.keyliveapp.keylivetv.home.homepresenter.HomePresenter;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeContentClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnHomeTitleClickListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnLiveRecChannelListener;
import com.keyliveapp.keylivetv.home.homeui.homeclickcallback.OnLiveRecItemClickListener;
import com.keyliveapp.keylivetv.values.URLvalues;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import java.util.ArrayList;
import java.util.List;

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
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        return dialog;


    }


    private void showHoriScrollView(final HomeBean homeBean) {

        for (int i = 0; i < homeBean.getData().getQuickbutton().size(); i++) {

            View view = LayoutInflater.from(getContext()).inflate(R.layout.home_scrollview_imgs, homeScrollView, false);
            ImageView imgs = (ImageView) view.findViewById(R.id.home_scrollview_pic);
            imgs.setAdjustViewBounds(true);
            Glide.with(getContext()).load(homeBean.getData().getQuickbutton().get(i).getImage()).into(imgs);
            homeScrollView.addView(view);
            final int finalI = i;
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "i:" + finalI, Toast.LENGTH_SHORT).show();
                    String url = URLvalues.CLASSIFY_URL_FRONT + homeBean.getData().getQuickbutton()
                            .get(finalI).getHrefTarget() + URLvalues.CLASSIFY_URL_BEHIND;

                    jumpToClassifyClickIn(url);

                }

            });

        }

    }

    private void jumpToClassifyClickIn(String url) {
        Intent intent = new Intent(getActivity(), ClassifyClickInActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void showBanner(HomeBean homeBean) {

        ArrayList<String> bannerImgSrc = new ArrayList<>();
//        ArrayList<String> bannerTitle=new ArrayList<>();
        for (int i = 0; i < homeBean.getData().getBanner().size(); i++) {
            bannerImgSrc.add(homeBean.getData().getBanner().get(i).getImage());
//            bannerTitle.add(homeBean.getData().getBanner().get(i).getTitle());
        }
//        homeBanner.setBannerTitleList(bannerTitle);
        homeBanner.setImages(bannerImgSrc);
        homeBanner.setBannerStyle(Banner.SCROLL_INDICATOR_BOTTOM);
        homeBanner.setDelayTime(3500);

        homeBanner.setOnBannerClickListener(new OnBannerClickListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(mContext, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void showRecyclerView(HomeBean homeBean) {

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
            }
        });
        //liveitem  点击
        adapter.setLiveRecItemClickListener(new OnLiveRecItemClickListener() {
            @Override
            public void liveItemClicked(int i) {
                Log.d("liveItemPosition", "i:" + i);
            }
        });
        //title点击
        adapter.setTitleClickListener(new OnHomeTitleClickListener() {
            @Override
            public void titleClicked(int position) {
                Log.d("titlePosition", "position:" + position + "channel++");
            }
        });
        //content点击
        adapter.setContentClickListener(new OnHomeContentClickListener() {
            @Override
            public void contentClicked(int titlePosition, int contentPosition) {
                Log.d("contentClicked", "titlePosition:" + titlePosition);
                Log.d("contentClicked", "contentPosition:" + contentPosition);
            }
        });


    }
}
