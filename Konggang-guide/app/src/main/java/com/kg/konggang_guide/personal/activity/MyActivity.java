package com.kg.konggang_guide.personal.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.activity.LoginActivity;
import com.kg.konggang_guide.other.base.CpBaseActivty;
import com.kg.konggang_guide.other.utils.AppManager;
import com.kg.konggang_guide.other.utils.DialogUtils;
import com.kg.konggang_guide.personal.AppState;
import com.kg.konggang_guide.personal.presenter.MyPresenter;
import com.kg.konggang_guide.personal.view.IMyView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kg.konggang_guide.R.id.ll_setting;
import static com.kg.konggang_guide.R.id.tv_name;

public class MyActivity extends CpBaseActivty implements IMyView{

    private Dialog settingDialog;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(tv_name)
    TextView tvName;
    @BindView(R.id.ll_order)
    LinearLayout llOrder;
    @BindView(R.id.img_01)
    ImageView img01;
    @BindView(R.id.img_02)
    ImageView img02;
    @BindView(R.id.tv_phone)
    TextView tvPhone;

    private ViewHolder viewHolder;
    private String name;
    private MyPresenter myPresenter;
    private boolean isVisibleOld;
    private boolean isVisibleNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ButterKnife.bind(this);
        initViews();
        initEvents();
        initDatas();
    }

    @Override
    protected void initViews() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    protected void initEvents() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        llOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(MyOrderActivity.class);
            }
        });

        imgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSettingDialog(MyActivity.this);
            }
        });
    }

    @Override
    protected void initDatas() {
        myPresenter=new MyPresenter(this);
        String picture=AppState.getInstance().getPicture();
        if(!TextUtils.isEmpty(picture)){
            Picasso.with(this).load(picture).into(imgIcon);
        }

        name=AppState.getInstance().getName();
        if(!TextUtils.isEmpty(name)){
            tvName.setText(name);
        }else{
            tvName.setText("引导员");
        }

    }

    @Override
    public void setRequestedOrientation(int requestedOrientation) {
        return;
    }


    public void getSettingDialog(Context context) {



        settingDialog = new Dialog(context, R.style.MyDialog);
        View view= LayoutInflater.from(this).inflate(R.layout.dialog_setting,null);
        viewHolder=new ViewHolder(view);
        settingDialog.setContentView(view);
        final Window dialogWindow = settingDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.4); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.CENTER);
        settingDialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        if(!TextUtils.isEmpty(name)){
            viewHolder.tvName.setText(name);
        }

        viewHolder.tvID.setText(AppState.getInstance().getUserId());
        viewHolder.tvSex.setText(AppState.getInstance().getSex());
        viewHolder.tv_Phone.setText(AppState.getInstance().getUserPhone());
        String account=AppState.getInstance().getAccount();
        if(TextUtils.isEmpty(account)) {
            viewHolder.tv_passwordPhone.setText(AppState.getInstance().getUserPhone());
        }else{
            viewHolder.tv_passwordPhone.setText(account);
        }
        //viewHolder.tvPassword.setText(AppState.getInstance().getPassword());

        viewHolder.llPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.llSetting.setVisibility(View.GONE);
                viewHolder.llChangePassword.setVisibility(View.VISIBLE);
                settingDialog.setCanceledOnTouchOutside(false);
            }
        });



        viewHolder.imgEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!isVisibleOld) {
                        viewHolder.imgEye.setImageResource(R.mipmap.password_v);
                        viewHolder.tvPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        isVisibleOld=true;
                    }else {
                        viewHolder.imgEye.setImageResource(R.mipmap.password_g);
                        viewHolder.tvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        isVisibleOld=false;
                    }

            }
        });


        viewHolder.imgNewEye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isVisibleNew){
                    isVisibleNew=true;
                    viewHolder.imgNewEye.setImageResource(R.mipmap.password_v);
                    viewHolder.etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else{
                    isVisibleNew=false;
                    viewHolder.imgNewEye.setImageResource(R.mipmap.password_g);
                    viewHolder.etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        viewHolder.tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.myHintDialog(MyActivity.this, "您确认退出吗？", "确定", new DialogUtils.OnClickListener() {
                    @Override
                    public void onClickRight() {
                        AppState.getInstance().setLogin(false);
                        AppState.getInstance().setPassword("");
                        AppState.getInstance().setUserId("");
                        AppManager.quitApp();
                        openActivity(LoginActivity.class);

                    }
                });
            }
        });

        viewHolder.tv_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String password=viewHolder.etNewPassword.getText().toString();
                if(TextUtils.isEmpty(viewHolder.tvPassword.getText().toString())){
                    showToask("请输入当前登陆密码！");
                }else if(password.contains("￥")){
                    DialogUtils.myHintDialog(MyActivity.this, "新密码包含非法字符：¥", "确定", new DialogUtils.OnClickListener() {
                        @Override
                        public void onClickRight() {

                        }
                    });
                }else if (password.contains("&")){
                    DialogUtils.myHintDialog(MyActivity.this, "新密码包含非法字符：&", "确定", new DialogUtils.OnClickListener() {
                        @Override
                        public void onClickRight() {

                        }
                    });
                }else if(password.contains("*")){
                    DialogUtils.myHintDialog(MyActivity.this, "新密码包含非法字符：*", "确定", new DialogUtils.OnClickListener() {
                        @Override
                        public void onClickRight() {

                        }
                    });
                }else if(password.length()<6){
                    showToask("新密码为6-18位数字或字母组合");
                }else{
                    myPresenter.changePassword();
                }
            }
        });

    }

    @Override
    public String getPhone() {
        return viewHolder.tv_Phone.getText().toString();
    }

    @Override
    public String getOldPassword() {
        return viewHolder.tvPassword.getText().toString();
    }

    @Override
    public String getNewPassword() {
        return viewHolder.etNewPassword.getText().toString();
    }

    @Override
    public void isSuccess() {
        viewHolder.llSetting.setVisibility(View.VISIBLE);
        viewHolder.llChangePassword.setVisibility(View.GONE);
        viewHolder.tvPassword.setText("");
        viewHolder.etNewPassword.setText("");
        settingDialog.setCanceledOnTouchOutside(true);
        DialogUtils.myHintDialog(MyActivity.this, "修改成功", "确定", new DialogUtils.OnClickListener() {
            @Override
            public void onClickRight() {

            }
        });
    }

    static class ViewHolder {
        @BindView(tv_name)
        TextView tvName;
        @BindView(R.id.tv_ID)
        TextView tvID;
        @BindView(R.id.tv_sex)
        TextView tvSex;
        @BindView(R.id.tv_phone)
        TextView tv_Phone;
        @BindView(R.id.ll_password)
        LinearLayout llPassword;
        @BindView(ll_setting)
        LinearLayout llSetting;
        @BindView(R.id.tv_passwordPhone)
        TextView tv_passwordPhone;
        @BindView(R.id.tv_password)
        EditText tvPassword;
        @BindView(R.id.img_eye)
        ImageView imgEye;
        @BindView(R.id.et_newPassword)
        EditText etNewPassword;
        @BindView(R.id.img_newEye)
        ImageView imgNewEye;
        @BindView(R.id.ll_changePassword)
        LinearLayout llChangePassword;
        @BindView(R.id.tv_commit)
        TextView tv_commit;
        @BindView(R.id.tv_logout)
        TextView tv_logout;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
