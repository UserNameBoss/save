package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.MainActivity;
import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.bean.LoginBean;
import com.kg.konggang_guide.other.presenter.LoginPresenter;
import com.kg.konggang_guide.other.utils.StringUtil;
import com.kg.konggang_guide.other.view.ILongView;
import com.kg.konggang_guide.other.widget.CircleImageView;
import com.kg.konggang_guide.personal.AppState;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends CpBaseActivty implements TextWatcher,ILongView{

    @BindView(R.id.img_icon)
    CircleImageView imgIcon;
    @BindView(R.id.et_userId)
    EditText etUserId;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_noPassword)
    TextView tvNoPassword;
    @BindView(R.id.img_delete)
    ImageView img_delete;
    @BindView(R.id.ll_code)
    LinearLayout ll_code;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.tv_getCode)
    TextView tv_code;

    private Timer timer;
    private int time=60;
    private TimerTask timerTask;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(time>0){
                time--;
                tv_code.setText("验证码("+time+"s)");
            }else{
                stopTimer();
                time=60;
                tv_code.setText("重获验证码");
                tv_code.setTextColor(getResources().getColor(R.color.color_38ADFF));
                tv_code.setEnabled(true);
                etUserId.setEnabled(true);
            }
        }
    };

    private int errorCount;
    private String password;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = activity.getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(activity.getResources().getColor(R.color.color_38ADFF));
//        }

    }

    @Override
    protected void initEvents() {
        etUserId.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
    }

    @Override
    protected void initDatas() {
        loginPresenter=new LoginPresenter(this);
        if(AppState.getInstance().isLogin()){
            etUserId.setText(AppState.getInstance().getUserPhone());
            etPassword.setText(AppState.getInstance().getPassword());
            loginPresenter.login();
        }
    }

    @OnClick({R.id.tv_login,R.id.img_delete,R.id.tv_getCode,R.id.tv_noPassword})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                loginPresenter.login();
                break;
            case R.id.img_delete:
                etUserId.setText("");
                break;
            case R.id.tv_getCode:
                if(!TextUtils.isEmpty(etUserId.getText().toString())) {
                    if(StringUtil.isMobile(etUserId.getText().toString())) {
                        startTimer();
                        tv_code.setText("验证码(60s)");
                        tv_code.setTextColor(getResources().getColor(R.color.color_a5a5a5));
                        tv_code.setEnabled(false);
                        etUserId.setEnabled(false);
                    }else{
                        showToask("请输入正确的手机号！");
                    }
                }else{
                    showToask("请输入手机号！");
                }
                break;
            case R.id.tv_noPassword:
                openActivity(ChangePasswordActivity.class);
                break;
        }

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if(!TextUtils.isEmpty(etUserId.getText().toString())&&!TextUtils.isEmpty(etPassword.getText().toString())){
            password=etPassword.getText().toString();
            if(etUserId.getText().toString().length()>=11) {
                if(StringUtil.isMobile(etUserId.getText().toString())) {
                    if ((password.length() >= 6) && password.length() <= 20) {
                        tvLogin.setEnabled(true);
                    }else{
                        tvLogin.setEnabled(false);
                    }
                }else{
                    showToask("请输入正确的手机号！");
                    tvLogin.setEnabled(false);
                }
            }else{
                tvLogin.setEnabled(false);
            }
        }else{
            tvLogin.setEnabled(false);
        }

        if(TextUtils.isEmpty(etUserId.getText().toString())){
            img_delete.setVisibility(View.INVISIBLE);
        }else{
            img_delete.setVisibility(View.VISIBLE);
        }
    }

    private void startTimer(){
        if(timer==null){
            timer=new Timer();
            timerTask=new TimerTask() {
                @Override
                public void run() {
                    handler.sendEmptyMessage(1);
                }
            };
            timer.schedule(timerTask,1000,1000);
        }
    }

    private void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timerTask.cancel();
            timer=null;
            timerTask=null;
        }
    }

    @Override
    public String getPhone() {

        return etUserId.getText().toString();
    }

    @Override
    public String getPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public void setLoginBean(LoginBean loginBean) {
        openActivity(MainActivity.class);
        finish();
    }
}
