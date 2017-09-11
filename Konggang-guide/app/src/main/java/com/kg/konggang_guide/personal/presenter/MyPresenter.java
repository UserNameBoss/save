package com.kg.konggang_guide.personal.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.personal.module.MyModule;
import com.kg.konggang_guide.personal.view.IMyView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/10
 */

public class MyPresenter extends CpBasePresenter {

    private MyModule myModule;
    private IMyView iMyView;

    public MyPresenter(IView iView) {
        super(iView);
        myModule=new MyModule((Activity) iView);
        iMyView= (IMyView) iView;
    }

    public void changePassword(){
        myModule.requestServerDataOne(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        iMyView.isSuccess();
                    }else{
                        iMyView.showToask(jsonObject.getString(AppConstants.MSG));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iMyView.showToask(code);
            }
        },iMyView.getPhone(),iMyView.getOldPassword(),iMyView.getNewPassword());
    }
}
