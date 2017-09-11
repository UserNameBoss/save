package com.kg.konggang_guide.other.base;
/**
 * Created by admin on 2016/10/2.
 */
public interface OnBaseDataListener<T> {
    public void onNewData(T data);
    public void onError(String code);
}
