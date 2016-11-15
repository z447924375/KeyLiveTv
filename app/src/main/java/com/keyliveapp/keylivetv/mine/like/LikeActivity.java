package com.keyliveapp.keylivetv.mine.like;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.keyliveapp.keylivetv.R;
import com.keyliveapp.keylivetv.baseclass.BaseActivity;
import com.keyliveapp.keylivetv.baseclass.BaseListViewAdapter;
import com.keyliveapp.keylivetv.baseclass.BaseViewHolder;
import com.keyliveapp.keylivetv.bean.DomainBean;
import com.keyliveapp.keylivetv.tools.db.DBTools;

import java.util.List;

public class LikeActivity extends BaseActivity implements View.OnClickListener {
    private ListView likeLv;
    private ImageButton likeBack;

    @Override
    protected int setLayout() {
        return R.layout.activity_like;
    }

    @Override
    protected void initView() {
        likeLv = bindView(R.id.like_lv);
        likeBack = bindView(R.id.like_back);

    }

    @Override
    protected void inidate() {

        likeBack.setOnClickListener(this);


        DBTools.getInstance().getAll(new DBTools.QueryListener<DomainBean>() {
            @Override
            public void onQuery(final List<DomainBean> domainList) {

                final BaseListViewAdapter adapter = new BaseListViewAdapter(getBaseContext(),domainList,R.layout.like_item) {
                    @Override
                    public void convent(BaseViewHolder viewHolder, Object o, int position) {
                        viewHolder.setText(R.id.like_title,domainList.get(position).getBaseRoomInfo().getName());
                        viewHolder.setText(R.id.like_num,"订阅数 " + domainList.get(position).getBaseRoomInfo().getSubscribeCount()+"");
                        viewHolder.setCirImage(R.id.like_pic,domainList.get(position).getBaseRoomInfo().getAvatar());
                    }
                    public void deleteItem(int pos){
                        domainList.remove(pos);
                        notifyDataSetChanged();
                        Toast.makeText(LikeActivity.this, "已删除", Toast.LENGTH_SHORT).show();
                    }

                };

                likeLv.setAdapter(adapter);
                likeLv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                        

                        return false;
                    }
                });

            }



        }, DomainBean.class);


    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
