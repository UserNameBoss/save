package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.PriceBean;
import com.kg.konggang_guide.other.presenter.PricePresenter;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.view.IPriceView;
import com.kg.konggang_guide.personal.AppState;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kg.konggang_guide.R.id.tv_price;
import static com.kg.konggang_guide.R.id.tv_travelFee;

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
    @BindView(tv_travelFee)
    TextView tvTravelFee;
    @BindView(R.id.tv_cityFee)
    TextView tvCityFee;
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
        Bundle bundle=new Bundle();
        bundle.putString("cityId",getIntent().getExtras().getString("cityId"));
        openActivity(PriceActivity.class,bundle);
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
        if(priceBean.data!=null&&priceBean.data.size()>0){
            tvMileage.setText("里程费("+priceBean.data.get(0).mileage+"公里)");
            tvMileageMoney.setText(priceBean.data.get(0).mileageMoney+"");
            tvTime.setText("时长费("+priceBean.data.get(0).time+"分钟)");
            tvTimeFee.setText(priceBean.data.get(0).timeFee+"元");
            tvTravelFee.setText(priceBean.data.get(0).travelFee+"元");
            tvCityFee.setText(priceBean.data.get(0).cityFee+"元");
            tvStartPrice.setText(priceBean.data.get(0).startFee+"元");
            tvPrice.setText(priceBean.data.get(0).totalFee+"元");
        }
    }
}
