package com.kg.konggang_guide.other.base;

import android.content.Context;

/**
 * Created by Administrator on 2016/11/30
 */
public interface IView {
    void showToask(String str);

    void showToask(int i);

    void showDialog(String msg);

    void toOtherActivity(Class<?> cls);

    void toFinishActivity();

    void toResult(int code);

    Context getMyContext();

    void noNet();
}
