package com.kg.konggang_guide.other.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.kg.konggang_guide.Http;
import com.kg.konggang_guide.MainActivity;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.base.OnBaseDataListener;
import com.kg.konggang_guide.other.bean.LoginBean;
import com.kg.konggang_guide.other.bean.SplashBean;
import com.kg.konggang_guide.other.presenter.LoginPresenter;
import com.kg.konggang_guide.other.utils.DebugUtils;
import com.kg.konggang_guide.other.utils.OkHttpUtils;
import com.kg.konggang_guide.other.view.ILongView;
import com.kg.konggang_guide.personal.AppState;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends CpBaseActivty implements ILongView {

    RelativeLayout rlRoot;
    ImageView ivSplash;
    Button btn_right;

    private Timer timer;
    private TimerTask timerTask;
    private int times;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    if(times<2){
                        times++;
                    }else{
                        stopTimer();
                        jumpNextPage();
                    }
                    break;
            }
        }
    };

    private LoginPresenter loginPresenter;
    private String etUserId;
    private String etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        ivSplash = (ImageView) findViewById(R.id.splash_iv);
        btn_right= (Button) findViewById(R.id.btn_right);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("type","2");
        OkHttpUtils.getInstance().getMap(Http.STARTPAGE,hashMap, new OnBaseDataListener<String>() {
            @Override
            public void onNewData(String data) {
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    if (jsonObject.getInt("code") != 200) {
                        showToask(jsonObject.getString("msg"));
                        startAnim(null);
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                SplashBean splashBean = new Gson().fromJson(data, SplashBean.class);
                startAnim(splashBean);
            }

            @Override
            public void onError(String code) {
                startAnim(null);
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
                jumpNextPage();
            }
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initDatas() {

    }

    /**
     * 开启动画
     */
    private void startAnim(SplashBean splashBean) {

        if (splashBean == null||splashBean.data==null||splashBean.data.size()<=0) {
            ivSplash.setImageResource(R.mipmap.img_splash);
        } else {
            String path = splashBean.data.get(0).picture;
            DebugUtils.prinlnLog(path);
            Picasso.with(SplashActivity.this.getApplication()).load(path).into(ivSplash);
        }
        startTimer();
        btn_right.setVisibility(View.VISIBLE);
        // 动画集合
//        AnimationSet set = new AnimationSet(false);
//
//        // 渐变动画
//        AlphaAnimation alpha = new AlphaAnimation(1, 1);
//        alpha.setDuration(2000);// 动画时间
//        alpha.setFillAfter(true);// 保持动画状态
//        set.addAnimation(alpha);
//
//        // 设置动画监听
//        set.setAnimationListener(new Animation.AnimationListener() {
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//
//            // 动画执行结束
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                jumpNextPage();
//            }
//        });
//
//        rlRoot.startAnimation(set);
    }

    public void startTimer(){
        if(timer==null){
            timer=new Timer();
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(0);
                }
            };
            timer.schedule(timerTask,1000,1000);
        }
    }

    public void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timerTask.cancel();
            timer=null;
            timerTask=null;
        }
    }

    /**
     * 跳转下一个页面
     */
    private void jumpNextPage() {
        // 判断之前有没有显示过新手引导
//        boolean userGuide = PrefUtils.getBoolean(this, "is_user_guide_showed",
//                false);
//        if (!userGuide) {
//            // 跳转到新手引导页
//            startActivity(new Intent(SplashActivity.this, GuideActivity.class));
//        } else {
//            if (AppState.getInstance().isLogin()){
////                loginHX();
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }else {
//                startActivity(new Intent(SplashActivity.this, MainActivity.class));
//            }
//        }
        loginPresenter=new LoginPresenter(this);
        if(AppState.getInstance().isLogin()){
            etUserId=AppState.getInstance().getUserPhone();
            etPassword=AppState.getInstance().getPassword();
            loginPresenter.login();
        }else{
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }

    }

    @Override
    public String getPhone() {
        return etUserId;
    }

    @Override
    public String getPassword() {
        return etPassword;
    }

    @Override
    public void setLoginBean(LoginBean loginBean) {

    }

    @Override
    public void setSuccess(boolean isSuccess) {
        if(isSuccess){
            openActivity(MainActivity.class);
        }else{
            openActivity(LoginActivity.class);
        }
        finish();
    }

//    private void loginHX() {
//        String uid = AppState.getInstance().getUserId();
//        //  if (TextUtils.isEmpty(uid)) {
////        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
////        startActivity(intent);
////        finish();
//        //    return;
//        // }
////        ObserverLogin.noctifySuss();
//        EMClient.getInstance().login(uid, "123456", new EMCallBack() {//回调
//            @Override
//            public void onSuccess() {
//                EMClient.getInstance().groupManager().loadAllGroups();
//                EMClient.getInstance().chatManager().loadAllConversations();
//                DebugUtils.prinlnLog("登录聊天服务器成功！");
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
//                        startActivity(intent);
//                        finish();
//                    }
//                });
//            }
//            @Override
//            public void onProgress(int progress, String status) {
//
//            }
//            @Override
//            public void onError(int code, String message) {
//                DebugUtils.prinlnLog("登录聊天服务器失败！");
//                DebugUtils.prinlnLog(message);
//            }
//        });
//    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }
}
