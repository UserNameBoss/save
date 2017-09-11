package com.kg.konggang_guide.personal.activity;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.kg.konggang_guide.R.id.ll_setting;

public class MyActivity extends CpBaseActivty {

    private Dialog settingDialog;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_icon)
    ImageView imgIcon;
    @BindView(R.id.tv_name)
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
        viewHolder.llPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.llSetting.setVisibility(View.GONE);
                viewHolder.llChangePassword.setVisibility(View.VISIBLE);
                settingDialog.setCanceledOnTouchOutside(false);
            }
        });


        viewHolder.imgEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    viewHolder.imgEye.setImageResource(R.mipmap.password_v);
                    viewHolder.tvPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    viewHolder.imgEye.setImageResource(R.mipmap.password_g);
                    viewHolder.tvPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });

        viewHolder.imgNewEye.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    viewHolder.imgNewEye.setImageResource(R.mipmap.password_v);
                    viewHolder.etNewPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    viewHolder.imgNewEye.setImageResource(R.mipmap.password_g);
                    viewHolder.etNewPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                return true;
            }
        });

        viewHolder.tv_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUtils.myHintDialog(MyActivity.this, "您确认退出吗？", "确定", new DialogUtils.OnClickListener() {
                    @Override
                    public void onClickRight() {
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
                if(password.contains("￥")){
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
                }else{

                    viewHolder.llSetting.setVisibility(View.VISIBLE);
                    viewHolder.llChangePassword.setVisibility(View.GONE);
                    settingDialog.setCanceledOnTouchOutside(true);
                    DialogUtils.myHintDialog(MyActivity.this, "修改成功", "确定", new DialogUtils.OnClickListener() {
                        @Override
                        public void onClickRight() {

                        }
                    });
                }
            }
        });

    }

    static class ViewHolder {
        @BindView(R.id.tv_name)
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
        TextView tvPassword;
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
