package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.OrderBean;
import com.kg.konggang_guide.other.utils.DebugUtils;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class AwaitServiceModule extends CpBaseModule<String,OrderBean> {
    public AwaitServiceModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<OrderBean> onBaseDataListener, String... parm) {
        String guideId=parm[0];
        String type=parm[1];
        String pageNum=parm[2];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("guideId",guideId);
        hashMap.put("type",type);
        hashMap.put("pageNo",pageNum);

        OkHttpUtils.getInstance().getMap(Http.SERVICEORDER, hashMap, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                DebugUtils.prinlnLog(data);
                OrderBean orderBean=new Gson().fromJson(data,OrderBean.class);
                onBaseDataListener.onNewData(orderBean);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }
}
