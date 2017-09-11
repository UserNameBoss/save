package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.MessageBean;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class MessageModule extends CpBaseModule<String,MessageBean> {

    public MessageModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<MessageBean> onBaseDataListener, String... parm) {
        String userId=parm[0];
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("userId",userId);
        OkHttpUtils.getInstance().getMap(Http.MESSAGECENTER, hashMap, activity, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                MessageBean messageBean=new Gson().fromJson(data,MessageBean.class);
                onBaseDataListener.onNewData(messageBean);
            }

            @Override
            public void onError(String code) {
                onBaseDataListener.onError(code);
            }
        });
    }
}
