package com.kg.konggang_guide.other.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.kg.konggang_guide.other.utils.ToastUtil;
import com.lzy.okgo.OkGo;


/**
 * Created by Administrator on 2016/11/30
 */
public abstract class CpBaseActivty extends BaseActivity implements IView {

    public Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
    }

    protected abstract void initViews();

    protected abstract void initEvents();

    protected abstract void initDatas();

    @Override
    public void setTopBar(int topBarColor) {

    }

    @Override
    public void showToask(String str) {
        ToastUtil.getInstance(this.getApplicationContext()).curToast(str);
    }

    @Override
    public void showToask(int i) {
        ToastUtil.getInstance(this.getApplicationContext()).shortToast(i);
    }

    @Override
    public void showDialog(String msg) {

    }

    @Override
    public void toOtherActivity(Class<?> cls) {
        openActivity_(cls);
    }

    @Override
    public void toFinishActivity() {
        finish();
    }

    @Override
    public void toResult(int code) {

    }

    @Override
    public Context getMyContext() {
        return this;
    }

    @Override
    public void noNet() {
        toast("网络异常");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //根据 Tag 取消请求
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {

            // 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
            View v = getCurrentFocus();

            if (isShouldHideInput(v, ev)) {
                hideSoftKeyboard();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = { 0, 0 };
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
        return false;
    }

    protected void hideSoftKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager mInputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            mInputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }
}
