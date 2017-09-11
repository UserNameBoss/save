package com.kg.konggang_guide.other.base;

import android.app.Activity;

/**
 * Created by Administrator on 2016/11/30
 */
public abstract class CpBaseModule<T, V> {
    public Activity activity;

    public CpBaseModule(Activity activity) {
        this.activity = activity;
    }

    public abstract void requestServerDataOne(OnBaseDataListener<V> onBaseDataListener, T... parm);

    public void requestServerDataTwo(OnBaseDataListener<V> onBaseDataListener, T... parm) {
    }

    public void requestServerDataThree(OnBaseDataListener<V> onBaseDataListener, V... parm) {

    }

    public void requestServerDataString(OnBaseDataListener<String> onBaseDataListener, String... parm) {

    }



}
