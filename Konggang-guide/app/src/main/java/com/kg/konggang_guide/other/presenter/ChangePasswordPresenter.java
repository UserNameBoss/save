package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.module.ChangePasswordModule;
import com.kg.konggang_guide.other.view.IChangePasswordView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class ChangePasswordPresenter extends CpBasePresenter {

    private ChangePasswordModule changePasswordModule;
    private IChangePasswordView iChangePasswordView;

    public ChangePasswordPresenter(IView iView) {
        super(iView);
        changePasswordModule=new ChangePasswordModule((Activity) iView);
        iChangePasswordView= (IChangePasswordView) iView;
    }


    //获取验证码
    public void getCode(){
        changePasswordModule.requestServerDataOne(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){

                    }else{
                        iChangePasswordView.showToask(jsonObject.getString(AppConstants.MSG));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String code) {
                iChangePasswordView.showToask(code);
            }
        },iChangePasswordView.getPhone());
    }

    public void changePassword(){
        changePasswordModule.requestServerDataTwo(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        iChangePasswordView.isChangeSuccess();
                    }else{
                        iChangePasswordView.showToask(jsonObject.getString(AppConstants.MSG));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iChangePasswordView.showToask(code);
            }
        },iChangePasswordView.getPhone(),iChangePasswordView.getNewPassword(),iChangePasswordView.getVcode());
    }
}
