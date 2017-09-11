package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/9
 */

public class ChangePasswordModule extends CpBaseModule<String,String> {

    public ChangePasswordModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<String> onBaseDataListener, String... parm) {
        String phone=parm[0];

        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("phone",phone);
        hashMap.put("type","1");
        OkHttpUtils.getInstance().getMap(Http.LOGINCODE, hashMap, activity, new OnBaseDataListener<String>() {
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

    @Override
    public void requestServerDataTwo(final OnBaseDataListener<String> onBaseDataListener, String... parm) {

        try {
            String phone=parm[0];
            String newPassword=parm[1];
            String vcode=parm[2];
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("newPassword",newPassword);
            jsonObject.put("vcode",vcode);
            OkHttpUtils.getInstance().postJson(Http.FORGETPASSWORD, jsonObject.toString(), activity, new OnBaseDataListener<String>() {
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
