package com.keyliveapp.keylivetv.home.homeui;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.HomeBean;
import com.keyliveapp.keylivetv.home.homepresenter.HomePresenter;
import com.keyliveapp.keylivetv.values.URLvalues;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by dllo on 16/10/22.
 */
public class HomeFragment extends BaseFragment implements IHomeView{

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
        mDialog=createDialog();
        mHomeRecycler = getViewLayout(R.id.home_recyclerview);
        homeBanner = getViewLayout(R.id.home_banner);
        homeScrollView = getViewLayout(R.id.home_quickbtn_scrollview);
        homeSearch=getViewLayout(R.id.home_search);

    }

    @Override
    protected void initDate() {
        mPresenter=new HomePresenter(this);
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
        List<HomeBean.DataBean.ColumnsBean> columnsBean;
        columnsBean = homeBean.getData().getColumns();
        for (int i = 0; i < columnsBean.size(); i++) {
            columnsBean.get(i).setViewType(i);
        }
        ArrayList<String> bannerImgSrc = new ArrayList<>();
        for (int i = 0; i < homeBean.getData().getBanner().size(); i++) {
            bannerImgSrc.add(homeBean.getData().getBanner().get(i).getImage());
        }
        homeBanner.setImages(bannerImgSrc);
        for (int i = 0; i < homeBean.getData().getQuickbutton().size(); i++) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.home_scrollview_imgs, homeScrollView, false);
            ImageView imgs = (ImageView) view.findViewById(R.id.home_scrollview_pic);
            imgs.setAdjustViewBounds(true);
            Picasso.with(getContext()).load(homeBean.getData().getQuickbutton().get(i).getImage()).into(imgs);
            homeScrollView.addView(view);
        }

        HomeRvAdapter adapter = new HomeRvAdapter(getContext(),columnsBean);
        mHomeRecycler.setAdapter(adapter);


        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mHomeRecycler.setLayoutManager(manager);
    }

    @Override
    public void onError() {
        Toast.makeText(mContext, "loading mistake", Toast.LENGTH_SHORT).show();
    }

    private ProgressDialog createDialog(){
        ProgressDialog dialog=new ProgressDialog(getContext());
        dialog.setTitle("loading...");
        dialog.setMessage("please waiting for a moment");
        dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        return dialog;
    }

}
