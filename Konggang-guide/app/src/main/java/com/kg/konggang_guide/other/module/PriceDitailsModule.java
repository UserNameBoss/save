package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.PriceDitailsBean;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class PriceDitailsModule extends CpBaseModule<String,PriceDitailsBean> {
    public PriceDitailsModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<PriceDitailsBean> onBaseDataListener, String... parm) {
        String cityId=parm[0];
        String serviceType=parm[1];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("cityId",cityId);
        hashMap.put("serviceType",serviceType);
        OkHttpUtils.getInstance().getMap(Http.CACULATEDETAIL,hashMap,activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    PriceDitailsBean priceDitailsBean=new Gson().fromJson(data,PriceDitailsBean.class);
                    onBaseDataListener.onNewData(priceDitailsBean);
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
