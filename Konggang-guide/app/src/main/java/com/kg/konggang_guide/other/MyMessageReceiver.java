package com.kg.konggang_guide.other;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.kg.konggang_guide.other.event.OrderEvent;

import cn.jpush.android.api.JPushInterface;
import de.greenrobot.event.EventBus;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/7/14
 */

public class MyMessageReceiver extends BroadcastReceiver {

    private NotificationManager nm;

    @Override
    public void onReceive(Context context, Intent intent) {
            if (nm==null) {
                nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            }
            Bundle bundle = intent.getExtras();

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
           // DebugUtils.prinlnLog("JPush用户注册成功");

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
           // DebugUtils.prinlnLog("接受到推送下来的自定义消息");
            //收到自定义消息传给消息列表
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
//            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
//            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
            //DebugUtils.prinlnLog("====="+title);
           // DebugUtils.prinlnLog("====="+message);




        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
           // DebugUtils.prinlnLog("接受到推送下来的通知");
            System.out.println("=====通知=====");
            String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_ALERT);
            String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
          //  DebugUtils.prinlnLog("====="+title);
           // DebugUtils.prinlnLog("====="+message);
            OrderEvent orderEvent=new OrderEvent();
            orderEvent.setIsRefresh(true);
            EventBus.getDefault().post(orderEvent);


        }else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            //DebugUtils.prinlnLog("用户点击打开了通知");



        } else {
            //DebugUtils.prinlnLog("Unhandled intent - " + intent.getAction());
        }
    }
}

