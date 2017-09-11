package com.kg.konggang_guide.personal.module;

import android.app.Activity;

import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/10
 */

public class MyModule extends CpBaseModule<String,String> {

    public MyModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<String> onBaseDataListener, String... parm) {
        try {
            String phone=parm[0];
            String oldPassword=parm[1];
            String newPassword=parm[2];

            JSONObject jsonObject=new JSONObject();

            jsonObject.put("phone",phone);
            jsonObject.put("oldPassword",oldPassword);
            jsonObject.put("newPassword",newPassword);

            OkHttpUtils.getInstance().postJson(Http.MODIFYPASSWORD, jsonObject.toString(), activity, new OnBaseDataListener<String>() {
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
