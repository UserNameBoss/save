package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.PriceDitailsBean;
import com.kg.konggang_guide.other.presenter.PriceDitailsPresenter;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.view.IPriceDitailsView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PriceActivity extends CpBaseActivty implements IPriceDitailsView {

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
    @BindView(R.id.tv_commonTime)
    TextView tv_commonTime;
    @BindView(R.id.ll_official)
    LinearLayout llOfficial;
    @BindView(R.id.ll_business)
    LinearLayout llBusiness;
    @BindView(R.id.ll_limousine)
    LinearLayout llLimousine;
    @BindView(R.id.tv_startFee)
    TextView tvStartFee;
    @BindView(R.id.tv_mileageFee)
    TextView tvMileageFee;
    @BindView(R.id.tv_timeFee)
    TextView tvTimeFee;
    @BindView(R.id.tv_travelFee)
    TextView tvTravelFee;
    @BindView(R.id.tv_eveFee)
    TextView tvEveFee;

    private String cityId;


    private PriceDitailsPresenter priceDitailsPresenter;
    private PriceDitailsBean priceDitailsBean;
    private PriceDitailsBean.DataEntity dataEntity_official;
    private PriceDitailsBean.DataEntity dataEntity_business;
    private PriceDitailsBean.DataEntity dataEntity_luxury;

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
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        supportTitle(true);
        title.setTitleText("计价规则");
        title.setLeftImage(R.mipmap.icon_back_blue);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        cityId = getIntent().getExtras().getString("cityId");
        priceDitailsPresenter = new PriceDitailsPresenter(this);
        priceDitailsPresenter.getPriceDitails();
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

    private void setBackground(int i) {
        switch (i) {
            case 0:

                tvOfficial.setTextColor(getResources().getColor(R.color.color_38ADFF));
                tvBusiness.setTextColor(getResources().getColor(R.color.color_333333));
                tvLimousine.setTextColor(getResources().getColor(R.color.color_333333));

                vLineOfficial.setVisibility(View.VISIBLE);
                vLineBusiness.setVisibility(View.INVISIBLE);
                vLineLimousine.setVisibility(View.INVISIBLE);
                setPagerBackground(0);
                break;
            case 1:

                tvOfficial.setTextColor(getResources().getColor(R.color.color_333333));
                tvBusiness.setTextColor(getResources().getColor(R.color.color_38ADFF));
                tvLimousine.setTextColor(getResources().getColor(R.color.color_333333));

                vLineOfficial.setVisibility(View.INVISIBLE);
                vLineBusiness.setVisibility(View.VISIBLE);
                vLineLimousine.setVisibility(View.INVISIBLE);
                setPagerBackground(1);

                break;
            case 2:
                tvOfficial.setTextColor(getResources().getColor(R.color.color_333333));
                tvBusiness.setTextColor(getResources().getColor(R.color.color_333333));
                tvLimousine.setTextColor(getResources().getColor(R.color.color_38ADFF));

                vLineOfficial.setVisibility(View.INVISIBLE);
                vLineBusiness.setVisibility(View.INVISIBLE);
                vLineLimousine.setVisibility(View.VISIBLE);
                setPagerBackground(2);

                break;
        }
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }


    @Override
    public String getCityId() {
        return cityId;
    }

    @Override
    public String getServiceType() {
        return "1";
    }

    @Override
    public void setPriceDitails(PriceDitailsBean priceDitailsBean) {
        this.priceDitailsBean = priceDitailsBean;

        if (this.priceDitailsBean.data != null && this.priceDitailsBean.data.size() > 0) {
            for (int i = 0; i < priceDitailsBean.data.size(); i++) {

                switch (priceDitailsBean.data.get(i).carType) {
                    case 1:
                        dataEntity_official = priceDitailsBean.data.get(i);
                        llOfficial.setVisibility(View.VISIBLE);

                        break;
                    case 2:
                        dataEntity_business = priceDitailsBean.data.get(i);
                        llBusiness.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        dataEntity_luxury = priceDitailsBean.data.get(i);
                        llLimousine.setVisibility(View.VISIBLE);
                        break;
                }
            }
        }
        setPagerBackground(0);
    }


    private void setPagerBackground(int posintion) {
        switch (posintion) {
            case 0:
                if (dataEntity_official != null) {
                    PriceDitailsBean.DataEntity dataEntity = dataEntity_official;
//                    tvPriceMin.setText(dataEntity.startPrice + "元");
//                    tvPriceTime.setText(dataEntity.timePrice + "元/分钟");
//                    tvPriceKm.setText(dataEntity.mileagePrice + "元/公里");
//
//                    tvFarPrice.setText(dataEntity.farAwayPrice + "元/公里");
//                    tv_commonTime.setText("时长费(" + dataEntity.commonTime + "分钟)");
                    //tv_commonMileage.setText(dataEntity.commonMileage+"");
                    tvStartFee.setText(dataEntity.startPrice+"元");
                    tvMileageFee.setText(dataEntity.mileagePrice+"元/公里");
                    tvTimeFee.setText(dataEntity.timePrice+"元/分钟");
                    tvTravelFee.setText(dataEntity.farAwayPrice+"元/公里");
                    tvEveFee.setText(dataEntity.nightPrice+"元/公里");
                }

                break;
            case 1:
                if (dataEntity_business != null) {
                    PriceDitailsBean.DataEntity dataEntity = dataEntity_business;
//                    tvPriceMin.setText(dataEntity.startPrice + "元");
//                    tvPriceTime.setText(dataEntity.timePrice + "元/分钟");
//                    tvPriceKm.setText(dataEntity.mileagePrice + "元/公里");
//
//                    tvFarPrice.setText(dataEntity.farAwayPrice + "元/公里");
//                    if (!TextUtils.isEmpty(dataEntity.commonTime)) {
//                        tv_commonTime.setText("时长费(" + dataEntity.commonTime + "分钟)");
//                    } else {
//                        tv_commonTime.setText("时长费(" + 20 + "分钟)");
//                    }

                    tvStartFee.setText(dataEntity.startPrice+"元");
                    tvMileageFee.setText(dataEntity.mileagePrice+"元/公里");
                    tvTimeFee.setText(dataEntity.timePrice+"元/分钟");
                    tvTravelFee.setText(dataEntity.farAwayPrice+"元/公里");
                    tvEveFee.setText(dataEntity.nightPrice+"元/公里");
                } else {
                    tvPriceMin.setText(0 + "元");
                    tvPriceTime.setText(0 + "元/分钟");
                    tvPriceKm.setText(0 + "元/公里");

                    tvFarPrice.setText(0 + "元/公里");
                    tv_commonTime.setText("时长费(" + 0 + "分钟)");
                }
                break;
            case 2:
                if (dataEntity_luxury != null) {
                    PriceDitailsBean.DataEntity dataEntity = dataEntity_luxury;
//                    tvPriceMin.setText(dataEntity.startPrice + "元");
//                    tvPriceTime.setText(dataEntity.timePrice + "元/分钟");
//                    tvPriceKm.setText(dataEntity.mileagePrice + "元/公里");
//
//                    tvFarPrice.setText(dataEntity.farAwayPrice + "元/公里");
//                    tv_commonTime.setText("时长费(" + dataEntity.commonTime + "分钟)");


                    tvStartFee.setText(dataEntity.startPrice+"元");
                    tvMileageFee.setText(dataEntity.mileagePrice+"元/公里");
                    tvTimeFee.setText(dataEntity.timePrice+"元/分钟");
                    tvTravelFee.setText(dataEntity.farAwayPrice+"元/公里");
                    tvEveFee.setText(dataEntity.nightPrice+"元/公里");

                } else {
                    tvPriceMin.setText(0 + "元");
                    tvPriceTime.setText(0 + "元/分钟");
                    tvPriceKm.setText(0 + "元/公里");

                    tvFarPrice.setText(0 + "元/公里");
                    tv_commonTime.setText("时长费(" + 0 + "分钟)");
                }
                break;
        }
    }
}
