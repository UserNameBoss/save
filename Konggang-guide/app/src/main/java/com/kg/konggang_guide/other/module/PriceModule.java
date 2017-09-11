package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.PriceBean;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class PriceModule extends CpBaseModule<String,PriceBean> {

    public PriceModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<PriceBean> onBaseDataListener, String... parm) {

        String orderId=parm[0];
        String userId=parm[1];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("orderId",orderId);
        hashMap.put("userId",userId);
        OkHttpUtils.getInstance().getMap(Http.CACULATEPRICEDETAIL, hashMap, activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try{
                    PriceBean priceBean=new Gson().fromJson(data,PriceBean.class);
                    onBaseDataListener.onNewData(priceBean);
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
