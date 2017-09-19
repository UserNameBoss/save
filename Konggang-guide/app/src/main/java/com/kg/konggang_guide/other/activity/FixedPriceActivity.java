package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FixedPriceActivity extends CpBaseActivty {

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_mileage)
    TextView tvMileage;
    @BindView(R.id.tv_time)
    TextView tvTime;
    private String time;
    private float mileage;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_price);
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
        title.setTitleText("费用详情");
        title.setLeftImage(R.mipmap.app_back);
        title.setRightText("计价规则");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        bundle = new Bundle();
        tvPrice.setText(bundle.getString("price"));
        tvMileage.setText(bundle.getFloat("mileage")+"");
        tvTime.setText(bundle.getString("time"));
    }

    @Override
    public void onRightClick(View view) {

        bundle.putString("cityId", getIntent().getExtras().getString("cityId"));
        openActivity(PriceActivity.class, bundle);
    }
}
