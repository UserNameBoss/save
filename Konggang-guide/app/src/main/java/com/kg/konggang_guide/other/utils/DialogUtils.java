package com.kg.konggang_guide.other.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kg.konggang_guide.R;
import com.kg.konggang_guide.other.bean.MessageBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/26
 */

public class DialogUtils {

    public static void toCarDialog(Context context, String messsage, String btnText, final OnClickListener onClickListener) {

        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_custom_tip);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.3); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(false);
        TextView message = (TextView) dialogWindow.findViewById(R.id.tv_message);
        Button cancel = (Button) dialogWindow.findViewById(R.id.no);
        Button commit = (Button) dialogWindow.findViewById(R.id.yes);
        message.setText(messsage);
        commit.setText(btnText);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onClickListener.onClickRight();
            }
        });

    }


    public static void myHintDialog(Context context, String messsage, String btnText, final OnClickListener onClickListener) {

        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_custom_tip02);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.3); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        TextView message = (TextView) dialogWindow.findViewById(R.id.tv_message);
        Button cancel = (Button) dialogWindow.findViewById(R.id.no);
        Button commit = (Button) dialogWindow.findViewById(R.id.yes);
        cancel.setVisibility(View.GONE);
        message.setText(messsage);
        commit.setText(btnText);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                onClickListener.onClickRight();
            }
        });

    }


    //通知信息
    public static void hintMessage(Context context, String messsage, String time) {

        final Dialog dialog = new Dialog(context, R.style.MyDialog02);
        dialog.setContentView(R.layout.dialog_hint_msg);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.5); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
        dialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(false);
        TextView message = (TextView) dialogWindow.findViewById(R.id.tv_content);
        TextView tv_time = (TextView) dialogWindow.findViewById(R.id.tv_time);
        ImageView img_close = (ImageView) dialogWindow.findViewById(R.id.img_close);
        message.setText(messsage);
        tv_time.setText(time);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
//        commit.setText(btnText);
//        cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        commit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                onClickListener.onClickRight();
//            }
//        });

    }


    public static void changeGuideDialog(final Context context, final OnCommitMessage onCommitMessage) {

        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_change_guide);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.4); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(false);
        final EditText et_guideCode = (EditText) dialogWindow.findViewById(R.id.et_guideCode);
        final EditText et_message = (EditText) dialogWindow.findViewById(R.id.et_message);
        TextView cancel = (TextView) dialogWindow.findViewById(R.id.tv_cancel);
        final TextView commit = (TextView) dialogWindow.findViewById(R.id.tv_quit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guideCode = et_guideCode.getText().toString();
                String message = et_message.getText().toString();
                if (!TextUtils.isEmpty(guideCode)) {
                    dialog.dismiss();
                    onCommitMessage.setMessage(guideCode, message);
                } else {
                    Toast.makeText(context, "请输入接单者账号！", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public static void getChangeGuideDialog(Context context,String content, MessageBean.DataEntity.ItemsEntity itemsEntity, MessageBean.DataEntity.GuideEntity guideEntity) {

        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        View view= LayoutInflater.from(context).inflate(R.layout.dialog_get_change,null);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        ViewHolder viewHolder=new ViewHolder(view);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.5); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.CENTER);
        dialog.setCancelable(false);
        dialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(false);
        TextView tv_cancel = (TextView) dialogWindow.findViewById(R.id.tv_cancel);
        TextView tv_quit = (TextView) dialogWindow.findViewById(R.id.tv_quit);
        ImageView img_close = (ImageView) dialogWindow.findViewById(R.id.img_close);
        LinearLayout ll_flight = (LinearLayout) dialogWindow.findViewById(R.id.ll_flight);
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        if (itemsEntity != null) {
            if (itemsEntity.type == 2) {
                viewHolder.llFlight.setVisibility(View.VISIBLE);
                viewHolder.tvFlight.setText(itemsEntity.flightNumber);

            } else {
                viewHolder.llFlight.setVisibility(View.GONE);
            }

            viewHolder.tvTime.setText(TimeUtils.getFromTime(itemsEntity.upTime));
            viewHolder.tvFrom.setText(itemsEntity.departureLocation);
            viewHolder.tvTo.setText(itemsEntity.arrivedLocation);
            viewHolder.tvPhone.setText(itemsEntity.telephone);
            viewHolder.tvContent.setText(content);
            viewHolder.tvId.setText(itemsEntity.id+"");
            if(guideEntity!=null){
                viewHolder.tvName.setText(guideEntity.name);
                viewHolder.tvId.setText(guideEntity.userAccount+"");
                viewHolder.tvGuidePhone.setText(guideEntity.telephone);
            }
        }


    }


    public interface OnClickListener {
        void onClickRight();
    }

    public interface OnCommitMessage {
        void setMessage(String guideCode, String message);
    }

    static class ViewHolder {
        @BindView(R.id.img_close)
        ImageView imgClose;
        @BindView(R.id.tv_flight)
        TextView tvFlight;
        @BindView(R.id.ll_flight)
        LinearLayout llFlight;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_from)
        TextView tvFrom;
        @BindView(R.id.tv_to)
        TextView tvTo;
        @BindView(R.id.tv_phone)
        TextView tvPhone;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_id)
        TextView tvId;
        @BindView(R.id.tv_guidePhone)
        TextView tvGuidePhone;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_cancel)
        TextView tvCancel;
        @BindView(R.id.tv_quit)
        TextView tvQuit;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
