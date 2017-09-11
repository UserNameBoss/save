package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;

public class PriceDitailsActivity extends CpBaseActivty {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_ditails);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        supportTitleStatus(true);
        title.setTitleText("价格预估");
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
        openActivity(PriceActivity.class);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }
}
