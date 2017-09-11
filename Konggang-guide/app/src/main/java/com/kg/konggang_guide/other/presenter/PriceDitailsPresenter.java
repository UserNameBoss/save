package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.PriceDitailsBean;
import com.kg.konggang_guide.other.module.PriceDitailsModule;
import com.kg.konggang_guide.other.view.IPriceDitailsView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class PriceDitailsPresenter extends CpBasePresenter {

    private PriceDitailsModule priceDitailsModule;
    private IPriceDitailsView iPriceDitailsView;
    public PriceDitailsPresenter(IView iView) {
        super(iView);
        priceDitailsModule=new PriceDitailsModule((Activity) iView);
        iPriceDitailsView= (IPriceDitailsView) iView;
    }

    public void getPriceDitails(){
        priceDitailsModule.requestServerDataOne(new OnBaseDataListener<PriceDitailsBean>() {
            @Override
            public void onNewData(PriceDitailsBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    iPriceDitailsView.setPriceDitails(data);
                }else{
                    iPriceDitailsView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {
                iPriceDitailsView.showToask(code);
            }
        },iPriceDitailsView.getCityId(),iPriceDitailsView.getServiceType());
    }
}
