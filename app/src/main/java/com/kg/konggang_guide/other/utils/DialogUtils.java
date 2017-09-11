package com.kg.konggang_guide.other.utils;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kg.konggang_guide.R;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/26
 */

public class DialogUtils {

    public static void toCarDialog(Context context, String messsage, String btnText, final OnClickListener onClickListener){

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


    public static void myHintDialog(Context context, String messsage, String btnText, final OnClickListener onClickListener){

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
    public static void hintMessage(Context context, String messsage, String time){

        final Dialog dialog = new Dialog(context, R.style.MyDialog02);
        dialog.setContentView(R.layout.dialog_hint_msg);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        WindowManager m = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.width = (int) (d.getWidth() * 0.5); // 宽度设置为屏幕的0.4
        dialogWindow.setAttributes(p);
        dialogWindow.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
        dialog.show();
        dialogWindow.setAttributes(lp);//此句代码一定要放在show()后面，否则不起作用
        dialog.setCanceledOnTouchOutside(false);
        TextView message = (TextView) dialogWindow.findViewById(R.id.tv_content);
        TextView tv_time= (TextView) dialogWindow.findViewById(R.id.tv_time);
        ImageView img_close= (ImageView) dialogWindow.findViewById(R.id.img_close);
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



    public static void changeGuideDialog(Context context,final OnCommitMessage onCommitMessage ){

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
        final EditText et_guideCode= (EditText) dialogWindow.findViewById(R.id.et_guideCode);
        final EditText et_message= (EditText) dialogWindow.findViewById(R.id.et_message);
        TextView cancel = (TextView) dialogWindow.findViewById(R.id.tv_cancel);
        TextView commit = (TextView) dialogWindow.findViewById(R.id.tv_quit);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String guideCode=et_guideCode.getText().toString();
                String message=et_message.getText().toString();
                if(!TextUtils.isEmpty(guideCode)) {
                    dialog.dismiss();
                    onCommitMessage.setMessage(guideCode, message);
                }
            }
        });

    }


    public static void getChangeGuideDialog(Context context){

        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        dialog.setContentView(R.layout.dialog_get_change);
        Window dialogWindow = dialog.getWindow();
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
        TextView tv_cancel= (TextView) dialogWindow.findViewById(R.id.tv_cancel);
        TextView tv_quit= (TextView) dialogWindow.findViewById(R.id.tv_quit);
        ImageView img_close= (ImageView) dialogWindow.findViewById(R.id.img_close);
        img_close.setOnClickListener(new View.OnClickListener() {
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


    }





    public interface OnClickListener{
         void onClickRight();
    }

    public interface OnCommitMessage{
        void setMessage(String guideCode,String message);
    }
}
