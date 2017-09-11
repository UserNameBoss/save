package com.kg.konggang_guide.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.personal.adapter.MyOrderAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends CpBaseActivty {

    @BindView(R.id.xrl_order)
    XRecyclerView xrlOrder;


    private MyOrderAdapter myOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        supportTitleStatus(true);
        title.setTitleText("我的订单");
        title.setLeftImage(R.mipmap.icon_back_blue);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrlOrder.setLayoutManager(linearLayoutManager);
        xrlOrder.setPullRefreshEnabled(false);
    }

    @Override
    protected void initEvents() {


    }

    @Override
    protected void initDatas() {
        myOrderAdapter=new MyOrderAdapter();
        xrlOrder.setAdapter(myOrderAdapter);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }
}
