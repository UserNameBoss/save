package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.LoginBean;
import com.kg.konggang_guide.other.module.LoginModule;
import com.kg.konggang_guide.other.view.ILongView;
import com.kg.konggang_guide.personal.AppState;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class LoginPresenter extends CpBasePresenter {

    private LoginModule loginModule;
    private ILongView iLongView;
    public LoginPresenter(IView iView) {
        super(iView);
        iLongView= (ILongView) iView;
        loginModule=new LoginModule((Activity) iView);
    }

    public void login(){
        loginModule.requestServerDataOne(new OnBaseDataListener<LoginBean>() {
            @Override
            public void onNewData(LoginBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    AppState.getInstance().setUserId(data.data.id+"");
                    AppState.getInstance().setName(data.data.name);
                    AppState.getInstance().setUserPhone(data.data.telephone);
                    AppState.getInstance().setPassword(iLongView.getPassword());
                    AppState.getInstance().setIdentity(data.data.identity+"");
                    AppState.getInstance().setPicture(data.data.picture);
                    AppState.getInstance().setSex(data.data.sex);
                    AppState.getInstance().setCityId(data.data.cityId);
                    AppState.getInstance().setAirId(data.data.airId);
                    AppState.getInstance().setLogin(true);
                    iLongView.setLoginBean(data);
                }else{
                    iLongView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {
                iLongView.showToask(code);
            }
        },iLongView.getPhone(),iLongView.getPassword());
    }

}
