package com.keyliveapp.keylivetv.home;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.tools.HttpManager;
import com.keyliveapp.keylivetv.tools.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/10/22.
 */
public class HomeFragment extends BaseFragment {

    private RecyclerView mHomeRecycler;
    private HomeBean mHomeBean;
    private Banner homeBanner;
    private LinearLayout homeScrollView;

    @Override
    protected int setLayout() {
        return R.layout.home_fragment;
    }

    @Override
    protected void initView() {
        mHomeRecycler = getViewLayout(R.id.home_recyclerview);
        homeBanner = getViewLayout(R.id.home_banner);
        homeScrollView = getViewLayout(R.id.home_quickbtn_scrollview);
    }

    @Override
    protected void initDate() {


        HttpManager.getInstance().getRequest(URLvalues.HOME_PAGE_URL, HomeBean.class, new OnCompletedListener<HomeBean>() {
            @Override
            public void onCompleted(HomeBean result) {
                ArrayList<String> bannerImgSrc = new ArrayList<>();
                for (int i = 0; i < result.getData().getBanner().size(); i++) {
                    bannerImgSrc.add(result.getData().getBanner().get(i).getImage());
                }
                homeBanner.setImages(bannerImgSrc);
                for (int i = 0; i < result.getData().getQuickbutton().size(); i++) {
                    View view = LayoutInflater.from(getContext()).inflate(R.layout.home_scrollview_imgs, homeScrollView, false);
                    ImageView imgs = (ImageView) view.findViewById(R.id.home_scrollview_pic);
                    imgs.setAdjustViewBounds(true);
                    Log.d("HomeFragment", result.getData().getQuickbutton().get(i).getImage());
                    Picasso.with(getContext()).load(result.getData().getQuickbutton().get(i).getImage()).into(imgs);
                    homeScrollView.addView(view);
                }
                List<HomeBean.DataBean.ColumnsBean> columnsBean = result.getData().getColumns();


            }

            @Override
            public void onFailed() {

            }
        });


//        HomeRvAdapter adapter=new HomeRvAdapter(MyApp.getContext());
//        mHomeRecycler.setAdapter(adapter);
//        LinearLayoutManager manager=new LinearLayoutManager(MyApp.getContext());
//        mHomeRecycler.setLayoutManager(manager);

    }
}
