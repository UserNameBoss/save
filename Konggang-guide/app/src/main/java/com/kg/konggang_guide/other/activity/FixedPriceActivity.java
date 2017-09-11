package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.StatusBarUtil;

public class FixedPriceActivity extends CpBaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_price);

        initViews();
        initEvents();
        initDatas();

    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        supportTitle(true);
        title.setTitleText("费用详情");
        title.setLeftImage(R.mipmap.app_back);
        title.setRightText("计价规则");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void onRightClick(View view) {
        Bundle bundle=new Bundle();
        bundle.putString("cityId",getIntent().getExtras().getString("cityId"));
        openActivity(PriceActivity.class,bundle);
    }
}
