package com.kg.konggang_guide.other.presenter;

import android.support.v4.app.Fragment;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.OrderBean;
import com.kg.konggang_guide.other.module.AwaitServiceModule;
import com.kg.konggang_guide.other.view.IAwaitView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class AwaitServicePresenter extends CpBasePresenter {

    private AwaitServiceModule awaitServiceModule;
    private IAwaitView iAwaitView;

    public AwaitServicePresenter(IView iView) {
        super(iView);
        iAwaitView= (IAwaitView) iView;
        awaitServiceModule=new AwaitServiceModule(((Fragment) iView).getActivity());
    }


    public void getOrderList(){
        awaitServiceModule.requestServerDataOne(new OnBaseDataListener<OrderBean>() {
            @Override
            public void onNewData(OrderBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    iAwaitView.setOrderBean(data);
                    iAwaitView.isSuccess(AppConstants.SUCCESS_CODE);
                }else{
                    iAwaitView.isSuccess(0);
                }
            }

            @Override
            public void onError(String code) {
                iAwaitView.showToask(code);
                iAwaitView.isSuccess(0);
            }
        },iAwaitView.getGuideId(),iAwaitView.getType(),iAwaitView.getPageNum());
    }
}
