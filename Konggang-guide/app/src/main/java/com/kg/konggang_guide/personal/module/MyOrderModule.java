package com.kg.konggang_guide.personal.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.utils.OkHttpUtils;
import com.kg.konggang_guide.personal.bean.MyOrderBean;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class MyOrderModule extends CpBaseModule<String,MyOrderBean> {

    public MyOrderModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<MyOrderBean> onBaseDataListener, String... parm) {
        String guideId=parm[0];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("guideId",guideId);
        OkHttpUtils.getInstance().getMap(Http.MYORDER, hashMap, activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                MyOrderBean myOrderBean=new Gson().fromJson(data,MyOrderBean.class);
                onBaseDataListener.onNewData(myOrderBean);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }
}
