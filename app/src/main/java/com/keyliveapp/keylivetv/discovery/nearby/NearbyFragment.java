package com.keyliveapp.keylivetv.discovery.nearby;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseFragment;
import com.keyliveapp.keylivetv.bean.DiscoveryBean;
import com.keyliveapp.keylivetv.bean.DomainBean;
import com.keyliveapp.keylivetv.livetv.full.LiveVideoFullActivity;
import com.keyliveapp.keylivetv.tools.okhttp.HttpManager;
import com.keyliveapp.keylivetv.tools.okhttp.OnCompletedListener;
import com.keyliveapp.keylivetv.values.URLvalues;

/**
 * Created by dllo on 16/10/28.
 */
public class NearbyFragment extends BaseFragment{
    private RecyclerView rv;
    private DiscoveryBean bean;
    private String url;
    @Override
    protected int setLayout() {
        return R.layout.discovery_nearby;
    }

    @Override
    protected void initView() {
        rv = getViewLayout(R.id.rv_discovery_nearby);

    }

    @Override
    protected void initDate() {
        url = URLvalues.DISCOVERY_URL_NEARBY;
        HttpManager.getInstance().getRequest(url, DiscoveryBean.class, new OnCompletedListener<DiscoveryBean>() {
            @Override
            public void onCompleted(final DiscoveryBean result) {
                bean = result;
                NearbyRvAdapter nearbyRvAdapter = new NearbyRvAdapter(getActivity());
                nearbyRvAdapter.setBean(bean);
                rv.setAdapter(nearbyRvAdapter);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);

                nearbyRvAdapter.setNearbyItemClickListener(new NearbyRvAdapter.OnNearbyItemClickListener() {
                    @Override
                    public void nearbyClick(final int position) {
                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String domain = result.getData().getStreams().getItems().get(position).getChannel().getDomain();

                                startFullLiveTv(domain);
                            }
                        });
                    }
                });



            }

            @Override
            public void onFailed() {

            }
        });



    }
    private void startFullLiveTv(final String domain) {
        final String domainUrl = URLvalues.DOMAIN_URL_FRONT + domain + URLvalues.DOMAIN_URL_BEHIND;
        HttpManager.getInstance().getRequest(domainUrl, DomainBean.class, new OnCompletedListener<DomainBean>() {
            @Override
            public void onCompleted(DomainBean result) {
                String roomid = result.getBroadcast().getRoomId() + "";

                Intent intent = new Intent(getActivity(), LiveVideoFullActivity.class);
                intent.putExtra("roomid", roomid);
                intent.putExtra("domain",result);
                startActivity(intent);
            }

            @Override
            public void onFailed() {

            }
        });


    }


}
