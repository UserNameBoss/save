package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.PriceBean;
import com.kg.konggang_guide.other.presenter.PricePresenter;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.view.IPriceView;
import com.kg.konggang_guide.personal.AppState;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kg.konggang_guide.R.id.tv_comboFee;
import static com.kg.konggang_guide.R.id.tv_price;

public class PriceDitailsActivity extends CpBaseActivty implements IPriceView {


    @BindView(tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_startPrice)
    TextView tvStartPrice;
    @BindView(R.id.tv_mileage)
    TextView tvMileage;
    @BindView(R.id.tv_mileageMoney)
    TextView tvMileageMoney;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_timeFee)
    TextView tvTimeFee;

    @BindView(R.id.tv_cityFee)
    TextView tvCityFee;
    @BindView(R.id.tv_travelFee)
    TextView tvTravelFee;
    @BindView(R.id.tv_bridge)
    TextView tvBridge;
    @BindView(R.id.tv_bridgePrice)
    TextView tvBridgePrice;
    @BindView(R.id.tv_price02)
    TextView tvPrice02;
    @BindView(R.id.tv_combo)
    TextView tvCombo;
    @BindView(tv_comboFee)
    TextView tvComboFee;
    @BindView(R.id.tv_changemileage02)
    TextView tvChangemileage02;
    @BindView(R.id.tv_mileageMoney02)
    TextView tvMileageMoney02;
    @BindView(R.id.tv_time02)
    TextView tvTime02;
    @BindView(R.id.tv_changeTimeFee02)
    TextView tvChangeTimeFee02;
    @BindView(R.id.tv_travelFee02)
    TextView tvTravelFee02;
    @BindView(R.id.tv_nightFee)
    TextView tvNightFee;
    @BindView(R.id.tv_cityFee02)
    TextView tvCityFee02;
    @BindView(R.id.tv_bridgePrice02)
    TextView tvBridgePrice02;
    @BindView(R.id.ll_priceType01)
    LinearLayout llPriceType01;
    @BindView(R.id.ll_priceType02)
    LinearLayout llPriceType02;
    @BindView(R.id.tv_travelOver)
    TextView tvTravelOver;
    private String orderId;
    private PricePresenter pricePresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_price_ditails);
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
        title.setTitleText("价格预估");
        title.setLeftImage(R.mipmap.app_back);
        title.setRightText("计价规则");
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {
        orderId = getIntent().getExtras().getString("orderId");
        pricePresenter = new PricePresenter(this);
        pricePresenter.getPriceDitails();
    }

    @Override
    public void onRightClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString("cityId", getIntent().getExtras().getString("cityId"));
        openActivity(PriceActivity.class, bundle);
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @Override
    public String getOrderId() {
        return orderId;
    }

    @Override
    public String getUserId() {
        return AppState.getInstance().getUserId();
    }

    @Override
    public void setPriceBean(PriceBean priceBean) {
        if (priceBean.data != null && priceBean.data.size() > 0) {
            PriceBean.DataEntity dataEntity = priceBean.data.get(0);
            if (dataEntity.calType == 2) {
                llPriceType01.setVisibility(View.VISIBLE);
                llPriceType02.setVisibility(View.GONE);
                tvMileageMoney.setText(priceBean.data.get(0).mileageMoney + "元");
                tvMileage.setText("里程费(" + getBig1(priceBean.data.get(0).mileage) + "公里)");
                tvTime.setText("时长费(" + (int)priceBean.data.get(0).time + "分钟)");
                tvTimeFee.setText(priceBean.data.get(0).timeFee + "元");
                tvTravelFee.setText(priceBean.data.get(0).travelFee + "元");
                tvCityFee.setText(priceBean.data.get(0).cityFee + "元");
                tvStartPrice.setText(priceBean.data.get(0).startFee + "元");

                tvPrice.setText(((int)(priceBean.data.get(0).totalFee)) + ".00");
                tvBridgePrice.setText(dataEntity.serveiceCharge + "元");
                if(dataEntity.mileage>15) {
                    tvTravelOver.setText("远途费(" + getBig1(dataEntity.mileage-15)+"公里)");
                }

            } else if (dataEntity.calType == 1) {
                llPriceType01.setVisibility(View.GONE);
                llPriceType02.setVisibility(View.VISIBLE);
                tvPrice02.setText((int)(priceBean.data.get(0).totalFee) + ".00");
                tvCombo.setText("套餐价(含" + dataEntity.mile + "公里，" + dataEntity.pacTime + "分钟)");
                tvMileageMoney02.setText(dataEntity.mileageCharge + "元");
                tvChangeTimeFee02.setText(dataEntity.timeCharge + "元");
                tvTravelFee02.setText(dataEntity.travelFee + "元");
                tvNightFee.setText(dataEntity.eveFee + "元");
                tvCityFee02.setText(dataEntity.cityFee + "元");
                tvBridgePrice02.setText(dataEntity.serveiceCharge + "元");
                tvComboFee.setText(dataEntity.packages + "元");
                tvChangemileage02.setText("超里程费(" + getBig1(dataEntity.overMile) + "公里)");
                tvTime02.setText("超时长费(" + (int)dataEntity.overTime + "分钟)");
                if(dataEntity.mileage>15) {
                    tvTravelOver.setText("远途费(" + getBig1(dataEntity.mileage-15)+"公里)");
                }
            }

        }
    }


    public String getBig1(double price){
        DecimalFormat df = new DecimalFormat("0.0");//想要的格式
        String score = df.format(price);//要将字符串转换成double型，再格式化
        return score;
    }

}
