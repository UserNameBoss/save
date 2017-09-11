package com.kg.konggang_guide.other.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderActivity extends CpBaseActivty {

    @BindView(R.id.tv_from)
    TextView tvFrom;
    @BindView(R.id.tv_to)
    TextView tvTo;
    @BindView(R.id.tv_phone)
    TextView tvPhone;
    @BindView(R.id.img_official)
    ImageView imgOfficial;
    @BindView(R.id.tv_official)
    TextView tvOfficial;
    @BindView(R.id.img_line_official)
    ImageView imgLineOfficial;
    @BindView(R.id.ll_officical)
    LinearLayout llOfficical;
    @BindView(R.id.img_business)
    ImageView imgBusiness;
    @BindView(R.id.tv_business)
    TextView tvBusiness;
    @BindView(R.id.img_line_business)
    ImageView imgLineBusiness;
    @BindView(R.id.ll_business)
    LinearLayout llBusiness;
    @BindView(R.id.img_limousine)
    ImageView imgLimousine;
    @BindView(R.id.tv_limousine)
    TextView tvLimousine;
    @BindView(R.id.img_line_limousine)
    ImageView imgLineLimousine;
    @BindView(R.id.ll_limousine)
    LinearLayout llLimousine;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_priceDital)
    TextView tvPriceDital;
    @BindView(R.id.tv_order)
    TextView tvOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);

        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        supportTitleStatus(true);
        title.setTitleText("立即用车");
        title.setLeftImage(R.mipmap.app_back);
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @OnClick({R.id.tv_from,R.id.tv_to,R.id.tv_priceDital,R.id.ll_officical, R.id.ll_business, R.id.ll_limousine, R.id.tv_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_officical:
                break;
            case R.id.ll_business:
            case R.id.ll_limousine:
                ToastUtil.getInstance(this).shortToast("该车型暂未开通！");
                break;
            case R.id.tv_order:
                break;
            case R.id.tv_from:
                openActivityForResult(InputAddressActivity.class,1000);

                break;
            case R.id.tv_to:
                openActivityForResult(InputAddressActivity.class,1001);
                break;

            case R.id.tv_priceDital:
                openActivity(PriceDitailsActivity.class);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1000){
            if(data!=null){
                String fromAddress=data.getStringExtra("address");
                tvFrom.setText(fromAddress);
            }

        }else if(requestCode==1001){
            if(data!=null){
                String toAddress=data.getStringExtra("address");
                tvTo.setText(toAddress);
            }
        }


    }
}
