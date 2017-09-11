package com.kg.konggang_guide.other.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kg.konggang_guide.AppConstants;
import com.kg.konggang_guide.MainApplication;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.event.OnTitleEvent;
import com.kg.konggang_guide.other.utils.AppManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * description
 *
 * @author created by wuwang on 2016/4/25
 */
public class BaseActivity extends AppCompatActivity implements OnTitleEvent {
    protected Handler handler = new Handler();
    protected ProgressDialog waitDialog;
    public Title title;
    protected Context mContext;
    public LinearLayout topBarLl;

    @Override
    public void onLeftClick(View view) {
        hideSoftKeyboard();
        finish();
    }

    @Override
    public void onRightClick(View view) {

    }

    @Override
    public void onCenterClick(View view) {

    }

    //显示沉浸式状态栏
    public void setTopBar(int topBarColor) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //   getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            supportTopBar(true, topBarColor);
        } else {
            supportTopBar(false, topBarColor);

        }
    }

    private void supportTopBar(boolean isSupport, int topBarColor) {
        topBarLl = (LinearLayout) findViewById(R.id.topBar_ll);
        topBarLl.setBackgroundColor(topBarColor);
        if (topBarLl != null) {
            if (isSupport) {
                topBarLl.setVisibility(View.VISIBLE);
            } else {

                topBarLl.setVisibility(View.GONE);
            }
        } else {
            try {
                new NullPointerException("无法找到 R.id.topBar_ll");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getDisplayWidth() {
        int width = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        return width;
    }

    public int getDisplayHeight() {
        int height = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay().getHeight();
        return height;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        AppManager.activityCreated(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    @Override
    protected void onDestroy() {
        AppManager.activityDestroyed(this);
        super.onDestroy();
    }

    /**
     * 是否为当前的activity添加标题
     *
     * @param isSupport true添加 false不添加
     */
    public void supportTitle(boolean isSupport) {
        if (isSupport) {
            View v = findViewById(R.id.title);
            if (v != null) {
                title = new Title(v, this);
            } else {
                try {
                    throw new Exception("Cannot find Title");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
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
                    Window window=getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(getResources().getColor(R.color.theme_white));
                    TextView textView = (TextView) findViewById(R.id.view_title_top);
                    textView.setVisibility(View.VISIBLE);
                    setMiuiStatusBarDarkMode(this,isSupport);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//android6.0以后可以对状态栏文字颜色和图标进行修改
                        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                    }

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

    public MainApplication getMainApplication() {
        return (MainApplication) getApplication();
    }

    public void setLoading(boolean isLoading) {
        try {
            if (isLoading) {
                if (waitDialog == null || !waitDialog.isShowing()) {
                    waitDialog = new ProgressDialog(BaseActivity.this, R.style.MyDialogStyleBottom);
                    waitDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    waitDialog.setCanceledOnTouchOutside(false);
                    ImageView view = new ImageView(BaseActivity.this);
                    view.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT));
                    Animation loadAnimation = AnimationUtils.loadAnimation(
                            BaseActivity.this, R.anim.rotate);
                    view.startAnimation(loadAnimation);
                    loadAnimation.start();
                    view.setImageResource(R.mipmap.ic_launcher);
                    waitDialog.show();
                    waitDialog.setContentView(view);
                }

            } else {
                if (waitDialog != null && waitDialog.isShowing()) {
                    waitDialog.dismiss();
                    waitDialog = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void openActivity_(Class<?> cls) {
        hideSoftKeyboard();
        Intent i = new Intent(mContext, cls);
        startActivity(i);
    }

    protected void openActivity(Class<?> cls, Bundle bundle) {
        Intent i = new Intent(mContext, cls);
        i.putExtras(bundle);
        startActivity(i);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode) {
        Intent i = new Intent(mContext, cls);
        startActivityForResult(i, requestCode);
    }

    protected void openActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent i = new Intent(mContext, cls);
        i.putExtras(bundle);
        startActivityForResult(i, requestCode);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //注：回调 1
        //Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //注：回调 2
        //Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //注：回调 3
        //Bugtags.onDispatchTouchEvent(this, event);
        return super.dispatchTouchEvent(event);
    }

    public Toast toast(CharSequence toast){
        @SuppressLint("ShowToast") Toast t= Toast.makeText(this, toast, Toast.LENGTH_SHORT);
        t.show();
        return t;
    }

    protected void hideSoftKeyboard() {
        View view = getWindow().peekDecorView();
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), 0);

        }
    }

    public void openActivity(Class<? extends Activity> clazz){
        Intent intent=new Intent(this,clazz);
        startActivity(intent);
    }

    public void openActivity(Class<? extends Activity> clazz,String data){
        Intent intent=new Intent(this,clazz);
        intent.putExtra(AppConstants.INTENT_DATA,data);
        startActivity(intent);
    }

    public void openActivity(Class<? extends Activity> clazz,int data){
        Intent intent=new Intent(this,clazz);
        intent.putExtra(AppConstants.INTENT_DATA,data);
        startActivity(intent);
    }

    public void openActivity(Class<? extends Activity> clazz,double data){
        Intent intent=new Intent(this,clazz);
        intent.putExtra(AppConstants.INTENT_DATA,data);
        startActivity(intent);
    }

    public static boolean setMiuiStatusBarDarkMode(Activity activity, boolean darkmode) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag = 0;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkmode ? darkModeFlag : 0, darkModeFlag);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
