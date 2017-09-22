package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.CityFromBean;
import com.kg.konggang_guide.other.bean.SearchAddressBean;
import com.kg.konggang_guide.other.utils.OkHttpUtils;
import com.kg.konggang_guide.other.utils.TimeUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class OrderModule extends CpBaseModule<String,String> {


    public OrderModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<String> onBaseDataListener, String... parm) {
        String userId=parm[0];
        String mileage=parm[1];
        String time=parm[2];
        String isOut=parm[3];
        String outMile=parm[4];
        String cityId=parm[5];

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("userId",userId);
        hashMap.put("mileage",mileage);
        hashMap.put("time",time);
        hashMap.put("isOut",isOut);
        if("1".equals(isOut)) {
            hashMap.put("outMile", outMile);
        }
        hashMap.put("type","1");
        hashMap.put("carType","1");
        hashMap.put("cityId",cityId);
        hashMap.put("upTime", TimeUtils.getCurrentTime());

        OkHttpUtils.getInstance().getMap(Http.IMMEDIATELYCACULATEPRICE, hashMap, new OnBaseDataListener<String>() {
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


    public void getCityModule(final OnBaseDataListener<CityFromBean> onBaseDataListener, String... parm) {
        OkHttpUtils.getInstance().get(Http.CITYLIST,new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    CityFromBean cityFromBean=new Gson().fromJson(data,CityFromBean.class);
                    onBaseDataListener.onNewData(cityFromBean);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }


    //上车地点地址选择
    public void getSearchAddress(final OnBaseDataListener<SearchAddressBean> onBaseDataListener, String...parm){
        String cityId=parm[0];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("cityId",cityId);
        OkHttpUtils.getInstance().getMap(Http.SEARCHADDRESS, hashMap, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    SearchAddressBean searchAddressBean=new Gson().fromJson(data,SearchAddressBean.class);
                    onBaseDataListener.onNewData(searchAddressBean);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }


            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }


    @Override
    public void requestServerDataTwo(final OnBaseDataListener<String> onBaseDataListener, String... parm) {
        String orderId=parm[0];
        String uId=parm[1];
        String departureLocation=parm[2];
        String arrivedLocation=parm[3];
        String name=parm[4];
        String telephone=parm[5];
        String isSend=parm[6];
        String carType=parm[7];
        String estimatePrice=parm[8];
        String city=parm[9];
        String ariport=parm[10];
        String terminalBuilding=parm[11];
        String onTime=parm[12];
        String arriedTime=parm[13];
        String departurePosition=parm[14];
        String arrivedPosition=parm[15];
        String arriredTime=parm[16];
        String time=parm[17];
        String mileage=parm[18];
        String guideId=parm[19];
        String cityId=parm[20];
        String airId=parm[21];



        try {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("orderId",orderId);
            jsonObject.put("uId",uId);
            jsonObject.put("departureLocation",departureLocation);
            jsonObject.put("arrivedLocation",arrivedLocation);
            jsonObject.put("name",name);
            jsonObject.put("telephone",telephone);
            jsonObject.put("isSend",isSend);
            jsonObject.put("carType",carType);
            jsonObject.put("estimatePrice",estimatePrice);
            jsonObject.put("city",city);
            jsonObject.put("airport",ariport);
            jsonObject.put("terminalBuilding",terminalBuilding);
            jsonObject.put("onTime",onTime);
            jsonObject.put("arriedTime",arriedTime);
            jsonObject.put("departurePosition",departurePosition);
            jsonObject.put("arrivedPosition",arrivedPosition);
            jsonObject.put("times",time);
            jsonObject.put("mileage",mileage);
            jsonObject.put("status",3);
            jsonObject.put("guideId",guideId);
            jsonObject.put("cityId",cityId);
            jsonObject.put("airId",airId);
            //  jsonObject.put("arriedTime", TimeUtils.getAllDate(System.currentTimeMillis()));

            OkHttpUtils.getInstance().postJson(Http.IMMEDIATELYORDER, jsonObject.toString(), activity, new OnBaseDataListener<String>() {
                @Override
                public void onNewData(String data) {
                    onBaseDataListener.onNewData(data);
                }

                @Override
                public void onError(String code) {
                    onBaseDataListener.onError(code);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
