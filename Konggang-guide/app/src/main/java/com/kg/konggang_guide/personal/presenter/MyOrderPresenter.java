package com.kg.konggang_guide.personal.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.personal.bean.MyOrderBean;
import com.kg.konggang_guide.personal.module.MyOrderModule;
import com.kg.konggang_guide.personal.view.IMyOrderView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class MyOrderPresenter extends CpBasePresenter {

    private MyOrderModule myOrderModule;
    private IMyOrderView iMyOrderView;
    public MyOrderPresenter(IView iView) {
        super(iView);
        myOrderModule=new MyOrderModule((Activity) iView);
        iMyOrderView= (IMyOrderView) iView;
    }

    public void getMyOrder(){
        myOrderModule.requestServerDataOne(new OnBaseDataListener<MyOrderBean>() {
            @Override
            public void onNewData(MyOrderBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    iMyOrderView.setOrderList(data);
                }else{
                    iMyOrderView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {
                iMyOrderView.showToask(code);
            }
        },iMyOrderView.getGuideId());
    }
}
