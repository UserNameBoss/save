package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.PriceBean;
import com.kg.konggang_guide.other.module.PriceModule;
import com.kg.konggang_guide.other.view.IPriceView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/18
 */

public class PricePresenter extends CpBasePresenter {
    private PriceModule priceModule;
    private IPriceView iPriceView;
    private Activity activity;
    public PricePresenter(IView iView) {
        super(iView);
        this.priceModule=new PriceModule((Activity) iView);
        this.iPriceView= (IPriceView) iView;
        activity= (Activity) iView;
    }

    public void getPriceDitails(){
        priceModule.requestServerDataOne(new OnBaseDataListener<PriceBean>() {
            @Override
            public void onNewData(PriceBean data) {
                if(activity.isDestroyed()){
                    return;
                }
                if(data.code== AppConstants.SUCCESS_CODE){
                    iPriceView.setPriceBean(data);
                    System.out.println("=================");
                }else{
                    iPriceView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {

            }
        },iPriceView.getOrderId(),iPriceView.getUserId());
    }
}
