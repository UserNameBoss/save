package com.kg.konggang_guide.other.module;

import android.app.Activity;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.other.base.CpBaseModule;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.LoginBean;
import com.kg.konggang_guide.other.utils.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/7
 */

public class LoginModule  extends CpBaseModule<String,LoginBean> {

    public LoginModule(Activity activity) {
        super(activity);
    }

    @Override
    public void requestServerDataOne(final OnBaseDataListener<LoginBean> onBaseDataListener, String... parm) {
        try {
            String phone=parm[0];
            String password=parm[1];
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("phone",phone);
            jsonObject.put("password",password);

            OkHttpUtils.getInstance().postJson(Http.LOGIN, jsonObject.toString(), activity, new OnBaseDataListener<String>() {
                @Override
                public void onNewData(String data) {
                    try {
                        LoginBean loginBean=new Gson().fromJson(data,LoginBean.class);
                        onBaseDataListener.onNewData(loginBean);
                    }catch (Exception e){
                        e.printStackTrace();
                        onBaseDataListener.onError("网络异常");
                    }

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
