package com.kg.konggang_guide.other.base;

import android.content.Context;
import android.os.Handler;

/**
 * Created by Administrator on 2016/11/30
 */
public abstract class CpBasePresenter {
    public Handler handler = new Handler();

    public CpBasePresenter(IView iView, Context context) {

    }

    public CpBasePresenter(IView iView) {

    }

    public  void requestData(String ... param){};
    public  void requestData(){};
    public  void requestData(Context context){};
    public  void requestData(int type){};

    public void sendMessage(){};




}
