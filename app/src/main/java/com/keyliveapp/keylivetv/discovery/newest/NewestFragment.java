package com.keyliveapp.keylivetv.discovery.newest;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
public class NewestFragment extends BaseFragment {
    private RecyclerView rv;
    @Override
    protected int setLayout() {
        return R.layout.discovery_newest;
    }

    @Override
    protected void initView() {
        rv = getViewLayout(R.id.rv_discovery_newest);

    }

    @Override
    protected void initDate() {
        HttpManager.getInstance().getRequest(URLvalues.DISCOVERY_URL_NEWEST, DiscoveryBean.class, new OnCompletedListener<DiscoveryBean>() {

            @Override
            public void onCompleted(final DiscoveryBean result) {

                NewestRvAdapter adapter = new NewestRvAdapter(getActivity());
                adapter.setBean(result);
                rv.setAdapter(adapter );
                adapter.setBean(result);
                GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
                manager.setOrientation(LinearLayoutManager.VERTICAL);
                rv.setLayoutManager(manager);

                adapter.setNewestItemClickListener(new NewestRvAdapter.OnNewestItemClickListener() {
                    @Override
                    public void newestClick(final int position) {

                        Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                String domain = result.getData().getStreams().getItems().get(position).getChannel().getDomain();
                                Log.d("zzz", domain +"****"+ position);

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
    private void startFullLiveTv(String domain) {
        String domainUrl = URLvalues.DOMAIN_URL_FRONT + domain + URLvalues.DOMAIN_URL_BEHIND;
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
