package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.DriverListBean;
import com.kg.konggang_guide.other.utils.DebugUtils;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class MainModule extends CpBaseModule<String,String> {

    public MainModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<String> onBaseDataListener, String... parm) {
        String orderId=parm[0];
        String phone=parm[1];
        String remark=parm[2];
        String userId=parm[3];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("orderId",orderId);
        hashMap.put("phone",phone);
        hashMap.put("remark",remark);
        hashMap.put("userId",userId);

        OkHttpUtils.getInstance().getMap(Http.CHANGEGUIDE, hashMap, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                onBaseDataListener.onNewData(data);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }


    public void requestServerDataTwo(final OnBaseDataListener<DriverListBean> onBaseDataListener, String... parm) {
        String terminalBuilding=parm[0];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("cityId",terminalBuilding);
        OkHttpUtils.getInstance().getMap(Http.DRIVERLIST, hashMap, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                DebugUtils.prinlnLog(data);
                DriverListBean driverListBean=new Gson().fromJson(data,DriverListBean.class);
                onBaseDataListener.onNewData(driverListBean);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }

    @Override
    public void requestServerDataThree(final OnBaseDataListener<String> onBaseDataListener, String... parm) {
        try {
            String orderId=parm[0];
            String guideId=parm[1];

            HashMap<String,String> jsonObject=new HashMap<>();
            jsonObject.put("orderId",orderId);
            jsonObject.put("guideId",guideId);

            OkHttpUtils.getInstance().getMap(Http.GETORDERS, jsonObject, activity, new OnBaseDataListener<String>() {
                @Override
                public void onNewData(String data) {
                    onBaseDataListener.onNewData(data);
                }

                @Override
                public void onError(String code) {
                    onBaseDataListener.onError(code);
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void requestToCar(final OnBaseDataListener<String> onBaseDataListener, String...parm){
        String orderId=parm[0];
        String driverId=parm[1];
        String type=parm[2];

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("orderId",orderId);
        hashMap.put("driverId",driverId);
        hashMap.put("type",type);

        OkHttpUtils.getInstance().getMap(Http.SENTCAER, hashMap, activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                onBaseDataListener.onNewData(data);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }

    public void requestWrite(final OnBaseDataListener<String> onBaseDataListener, String...parm){
        String driverId=parm[0];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("driverId",driverId);
        OkHttpUtils.getInstance().getMap(Http.MESSAGENOREAD, hashMap, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                DebugUtils.prinlnLog(data);
                onBaseDataListener.onNewData(data);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }
}
