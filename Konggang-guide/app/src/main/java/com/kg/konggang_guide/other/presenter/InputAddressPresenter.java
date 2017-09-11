package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.AddressBean;
import com.kg.konggang_guide.other.bean.CityBean;
import com.kg.konggang_guide.other.module.InputAddressModule;
import com.kg.konggang_guide.other.view.IInputAddressView;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class InputAddressPresenter extends CpBasePresenter {

    private IInputAddressView iInputAddressView;
    private InputAddressModule inputAddressModule;
    public InputAddressPresenter(IView iView) {
        super(iView);
        iInputAddressView= (IInputAddressView) iView;
        inputAddressModule=new InputAddressModule((Activity) iView);
    }

    public void getCity(){
        inputAddressModule.requestServerDataOne(new OnBaseDataListener<CityBean>() {
            @Override
            public void onNewData(CityBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    iInputAddressView.setCityBean(data);
                }else{
                    iInputAddressView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {
                iInputAddressView.showToask(code);
            }
        });
    }

    public void getAddress(){
        inputAddressModule.requestServerDataTwo(new OnBaseDataListener<AddressBean>() {
            @Override
            public void onNewData(AddressBean data) {
                if(data.code==AppConstants.SUCCESS_CODE){
                    iInputAddressView.setSearchAddress(data);
                }else{
                    iInputAddressView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {
                iInputAddressView.showToask(code);
            }
        },iInputAddressView.getCityId(),iInputAddressView.getSearchName());
    }
}
