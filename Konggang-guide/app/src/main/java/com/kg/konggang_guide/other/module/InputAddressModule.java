package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.AddressBean;
import com.kg.konggang_guide.other.bean.CityBean;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class InputAddressModule extends CpBaseModule<String,CityBean> {
    public InputAddressModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<CityBean> onBaseDataListener, String... parm) {
        OkHttpUtils.getInstance().get(Http.CITYLIST, activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                CityBean cityBean=new Gson().fromJson(data, CityBean.class);
                onBaseDataListener.onNewData(cityBean);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }



    public void requestServerDataTwo(final OnBaseDataListener<AddressBean> onBaseDataListener, String... parm) {
        String cityId=parm[0];
        String searchName=parm[1];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("cityId",cityId);
        hashMap.put("searchName",searchName);

        OkHttpUtils.getInstance().getMap(Http.SEARCHADDRESS, hashMap, activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    AddressBean addressBean=new Gson().fromJson(data,AddressBean.class);
                    onBaseDataListener.onNewData(addressBean);
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
}
