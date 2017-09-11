package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceActivity extends CpBaseActivty {

    @BindView(R.id.img_official)
    ImageView imgOfficial;
    @BindView(R.id.tv_official)
    TextView tvOfficial;
    @BindView(R.id.v_line_official)
    View vLineOfficial;
    @BindView(R.id.img_business)
    ImageView imgBusiness;
    @BindView(R.id.tv_business)
    TextView tvBusiness;
    @BindView(R.id.v_line_business)
    View vLineBusiness;
    @BindView(R.id.img_limousine)
    ImageView imgLimousine;
    @BindView(R.id.tv_limousine)
    TextView tvLimousine;
    @BindView(R.id.v_line_limousine)
    View vLineLimousine;
    @BindView(R.id.ll_car)
    LinearLayout llCar;
    @BindView(R.id.tv_price_km)
    TextView tvPriceKm;
    @BindView(R.id.tv_price_23_00)
    TextView tvPrice2300;
    @BindView(R.id.tv_price_00_05)
    TextView tvPrice0005;
    @BindView(R.id.tv_price_time)
    TextView tvPriceTime;
    @BindView(R.id.tv_priceMin)
    TextView tvPriceMin;
    @BindView(R.id.tv_farPrice)
    TextView tvFarPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price);
        ButterKnife.bind(this);

        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        supportTitleStatus(true);
        title.setTitleText("计价规则");
        title.setLeftImage(R.mipmap.icon_back_blue);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    @OnClick({R.id.img_official, R.id.img_business, R.id.img_limousine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_official:
                setBackground(0);
                break;
            case R.id.img_business:
                setBackground(1);

                break;
            case R.id.img_limousine:
                setBackground(2);

                break;
        }
    }

    private void setBackground(int i){
        switch (i){
            case 0:

                tvOfficial.setTextColor(getResources().getColor(R.color.color_38ADFF));
                tvBusiness.setTextColor(getResources().getColor(R.color.color_333333));
                tvLimousine.setTextColor(getResources().getColor(R.color.color_333333));

                vLineOfficial.setVisibility(View.VISIBLE);
                vLineBusiness.setVisibility(View.INVISIBLE);
                vLineLimousine.setVisibility(View.INVISIBLE);
                break;
            case 1:

                tvOfficial.setTextColor(getResources().getColor(R.color.color_333333));
                tvBusiness.setTextColor(getResources().getColor(R.color.color_38ADFF));
                tvLimousine.setTextColor(getResources().getColor(R.color.color_333333));

                vLineOfficial.setVisibility(View.INVISIBLE);
                vLineBusiness.setVisibility(View.VISIBLE);
                vLineLimousine.setVisibility(View.INVISIBLE);
                break;
            case 2:

                tvOfficial.setTextColor(getResources().getColor(R.color.color_333333));
                tvBusiness.setTextColor(getResources().getColor(R.color.color_333333));
                tvLimousine.setTextColor(getResources().getColor(R.color.color_38ADFF));

                vLineOfficial.setVisibility(View.INVISIBLE);
                vLineBusiness.setVisibility(View.INVISIBLE);
                vLineLimousine.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }
}
