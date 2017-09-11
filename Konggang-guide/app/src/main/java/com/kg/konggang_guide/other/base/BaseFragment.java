package com.kg.konggang_guide.other.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.kg.konggang_guide.MainApplication;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.event.OnTitleEvent;


/**
 * description
 *
 * @author created by wuwang on 2016/4/25
 */
public abstract class BaseFragment extends Fragment implements OnTitleEvent {

    public View rootView;
    protected Title title;
    protected Context mContext;
    protected Handler handler=new Handler();
    protected  void findViews(View layout) {
    }
    protected  void initialize() {
    }

    protected  void bindEvent(){
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.mContext = getActivity().getApplicationContext();
//        return super.onCreateView(inflater, container, savedInstanceState);
        if (rootView == null) {
            rootView = onCreateRootView(inflater, container);
        } else {
            if (rootView.getParent() != null) {
                ((ViewGroup) rootView.getParent()).removeView(rootView);
            }
        }
        return rootView;
    }

    /**
     * 是否为当前的activity添加标题,并设置沉浸式
     * @param isSupport
     */
    public void supportTitleStatus(boolean isSupport) {
        if (isSupport) {
            View v = findViewById(R.id.title);
            if (v != null) {
                title = new Title(v, this);
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    //透明状态栏
                    getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    TextView textView = (TextView) findViewById(R.id.view_title_top);
                    textView.setVisibility(View.VISIBLE);
                }else {
                    TextView textView = (TextView) findViewById(R.id.view_title_top);
                    textView.setVisibility(View.GONE);
                }
            } else {
                try {
                    throw new Exception("Cannot find Title");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public abstract View onCreateRootView(LayoutInflater inflater, ViewGroup container);

    public View findViewById(int id) {
        return rootView.findViewById(id);
    }

    public int gColor(int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getActivity().getColor(id);
        } else {
            return this.getResources().getColor(id);
        }
    }

    protected void hideSoftKeyboard() {
        View view = getActivity().getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean onBackPressed() {
        return true;
    }

    public Toast toast(CharSequence toast) {
        Toast t = Toast.makeText(getContext(), toast, Toast.LENGTH_SHORT);
        t.show();
        return t;
    }

    public MainApplication getMainApplication(){
        return ((BaseActivity)getActivity()).getMainApplication();
    }

    public BaseActivity getBaseActivity(){
        return (BaseActivity) getActivity();
    }

    
    protected void supportTitle(boolean isSupport){
        if(isSupport){
            View v = findViewById(R.id.title);
            if(v!=null){
                title = new Title(v,this);
            }else{
                try {
                    throw new Exception("Cannot find Title");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onCenterClick(View view) {

    }

    @Override
    public void onLeftClick(View view) {

    }

    @Override
    public void onRightClick(View view) {

    }
    protected void openActivity(Class<?> cls) {
        if (isAdded()) {
            Intent i = new Intent(mContext, cls);
            startActivity(i);
        }
    }


    protected void openActivity(Class<?> cls, Bundle bundle) {
        if (bundle == null) {
            openActivity(cls);
            return;
        }
        Intent i = new Intent(mContext, cls);
        i.putExtras(bundle);
        startActivity(i);
        int version = Integer.valueOf(Build.VERSION.SDK);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode) {
        Intent i = new Intent(mContext, cls);
        startActivityForResult(i, requestCode);
        int version = Integer.valueOf(Build.VERSION.SDK);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent i = new Intent(mContext, cls);
        i.putExtras(bundle);
        startActivityForResult(i, requestCode);
        int version = Integer.valueOf(Build.VERSION.SDK);
    }
}
