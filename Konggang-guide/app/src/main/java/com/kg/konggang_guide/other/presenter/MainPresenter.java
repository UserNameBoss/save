package com.kg.konggang_guide.other.presenter;

import android.app.Activity;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.other.base.CpBasePresenter;
import com.kg.konggang_guide.other.base.IView;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.DriverListBean;
import com.kg.konggang_guide.other.module.MainModule;
import com.kg.konggang_guide.other.view.IMainView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class MainPresenter extends CpBasePresenter {

    private MainModule mainModule;
    private IMainView iMainView;
    public MainPresenter(IView iView) {
        super(iView);
        mainModule=new MainModule((Activity) iView);
        iMainView= (IMainView) iView;
    }


    //获取司机列表
    public void getDriverList(){
        mainModule.requestServerDataTwo(new OnBaseDataListener<DriverListBean>() {
            @Override
            public void onNewData(DriverListBean data) {
                if(data.code== AppConstants.SUCCESS_CODE){
                    iMainView.setDriverList(data);
                }else{
                    iMainView.setDriverList(null);
                   // iMainView.showToask(data.msg);
                }
            }

            @Override
            public void onError(String code) {
                iMainView.setDriverList(null);
                iMainView.showToask(code);
            }
        },iMainView.getCityId());
    }

    //改派
    public void setChangeGuide(){
        mainModule.requestServerDataOne(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        iMainView.isChangeGuide();
                    }else{
                        iMainView.showToask(jsonObject.getString(AppConstants.MSG));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iMainView.showToask(code);
            }
        },iMainView.getOrderId(),iMainView.getPhone(),iMainView.getRemark(),iMainView.getGuideId());
    }


    //接单
    public void getOrder(){
        mainModule.requestServerDataThree(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        iMainView.getOrderSuccess(true);
                    }else{
                        iMainView.getOrderSuccess(false);
                        iMainView.showToask(jsonObject.getString(AppConstants.MSG));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iMainView.showToask(code);
            }
        },iMainView.getOrderId02(),iMainView.getGuideId());
    }

    //派车
    public void toCar(){
        mainModule.requestToCar(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        iMainView.isSendCarSuccess(true);
                    }else{
                        iMainView.showToask(jsonObject.getString(AppConstants.MSG));
                        iMainView.isSendCarSuccess(false);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iMainView.showToask(code);
            }
        },iMainView.getOrderId(),iMainView.getDirId(),iMainView.getType());
    }

    public void getWrite(){
        mainModule.requestWrite(new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject=new JSONObject(data);
                    if(jsonObject.getInt(AppConstants.CODE)==AppConstants.SUCCESS_CODE){
                        iMainView.setMessageNoRead(jsonObject.getInt(AppConstants.DATA));
                    }else{
                        iMainView.showToask(jsonObject.getString(AppConstants.MSG));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(String code) {
                iMainView.showToask(code);
            }
        },iMainView.getGuideId());
    }
}
