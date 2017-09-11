package com.kg.konggang_guide.other.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.presenter.ChangePasswordPresenter;
import com.kg.konggang_guide.other.utils.StatusBarUtil;
import com.kg.konggang_guide.other.utils.StringUtil;
import com.kg.konggang_guide.other.utils.ToastUtil;
import com.kg.konggang_guide.other.view.IChangePasswordView;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangePasswordActivity extends CpBaseActivty implements TextWatcher,IChangePasswordView {

    @BindView(R.id.et_userId)
    EditText etUserId;
    @BindView(R.id.img_delete)
    ImageView imgDelete;
    @BindView(R.id.et_code)
    EditText etCode;
    @BindView(R.id.tv_getCode)
    TextView tvGetCode;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;

    private String password;
    private Timer timer;
    private TimerTask timerTask;
    private int time=60;
    private boolean isGetCode;
    private ChangePasswordPresenter changePasswordPresenter;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(time>0){

                time--;
                tvGetCode.setText("验证码("+time+"s)");
            }else{
                isGetCode=false;
                stopTimer();
                time=60;
                tvGetCode.setText("重获验证码");
                tvGetCode.setTextColor(getResources().getColor(R.color.color_38ADFF));
                tvGetCode.setEnabled(true);
                etUserId.setEnabled(true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chage_password);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        StatusBarUtil.setOiStatusBarColor(this);
        StatusBarUtil.StatusBarLightMode(this);
        supportTitle(true);
        title.setTitleText("忘记密码");
        title.setLeftImage(R.mipmap.app_back);
    }

    @Override
    protected void initEvents() {
        etUserId.addTextChangedListener(this);
        etPassword.addTextChangedListener(this);
    }

    @Override
    protected void initDatas() {
        changePasswordPresenter=new ChangePasswordPresenter(this);
    }

    @OnClick({R.id.img_delete, R.id.tv_getCode, R.id.tv_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_delete:
                etUserId.setText("");
                break;
            case R.id.tv_getCode:
                changePasswordPresenter.getCode();
                if(StringUtil.isMobile(etUserId.getText().toString())){
                    startTimer();
                    isGetCode=true;
                    tvGetCode.setText("验证码("+60+"s)");
                    tvGetCode.setTextColor(getResources().getColor(R.color.color_a5a5a5));
                }else{
                    ToastUtil.getInstance(this).shortToast("请输入正确的手机号！");
                }
                break;
            case R.id.tv_login:
                if(etPassword.length()>=6&&etPassword.length()<=20){
                    changePasswordPresenter.changePassword();
                }else{
                    ToastUtil.getInstance(this).shortToast("密码长度为6-18位");
                }
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }


    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {
        if(!TextUtils.isEmpty(etUserId.getText().toString())){
            imgDelete.setVisibility(View.VISIBLE);
            if(StringUtil.isMobile(etUserId.getText().toString())) {
                if(!isGetCode) {
                    tvGetCode.setTextColor(getResources().getColor(R.color.color_38ADFF));
                    tvGetCode.setEnabled(true);
                }

            }else{
                tvGetCode.setTextColor(getResources().getColor(R.color.color_a5a5a5));
                tvGetCode.setEnabled(false);
            }
        }else{
            imgDelete.setVisibility(View.INVISIBLE);
            tvGetCode.setTextColor(getResources().getColor(R.color.color_a5a5a5));
            tvGetCode.setEnabled(false);
        }

        if(!TextUtils.isEmpty(etUserId.getText().toString())&&!TextUtils.isEmpty(etCode.getText().toString())&&
                !TextUtils.isEmpty(etPassword.getText().toString())){

            password=etPassword.getText().toString();
            if(etUserId.getText().toString().length()>=11) {
                if(StringUtil.isMobile(etUserId.getText().toString())) {
                    if(etCode.length()>=4) {
                        if ((password.length() >= 6) && password.length() <= 20) {
                            tvLogin.setEnabled(true);
                        }else{
                            tvLogin.setEnabled(false);
                        }
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

        password=etPassword.getText().toString();
    }



    private void startTimer(){
        if(timer==null){
            timer=new Timer();
            timerTask=  new TimerTask() {
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
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
    }

    @Override
    public String getPhone() {
        return etUserId.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return etPassword.getText().toString();
    }

    @Override
    public String getVcode() {
        return etCode.getText().toString();
    }

    @Override
    public void isChangeSuccess() {
        showToask("修改成功！");
        finish();
    }
}
