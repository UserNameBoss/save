package com.kg.konggang_guide.personal.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.personal.AppState;
import com.kg.konggang_guide.personal.adapter.MyOrderAdapter;
import com.kg.konggang_guide.personal.bean.MyOrderBean;
import com.kg.konggang_guide.personal.presenter.MyOrderPresenter;
import com.kg.konggang_guide.personal.view.IMyOrderView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyOrderActivity extends CpBaseActivty implements IMyOrderView {

    @BindView(R.id.xrl_order)
    XRecyclerView xrlOrder;


    private MyOrderAdapter myOrderAdapter;
    private MyOrderPresenter myOrderPresenter;
    private MyOrderBean myOrderBean;

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
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        supportTitle(true);
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
        myOrderPresenter=new MyOrderPresenter(this);
        myOrderPresenter.getMyOrder();

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @Override
    public String getGuideId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public void setOrderList(MyOrderBean myOrderBean) {
        this.myOrderBean=myOrderBean;
        myOrderAdapter.setMyOrderBean(myOrderBean);

    }
}
