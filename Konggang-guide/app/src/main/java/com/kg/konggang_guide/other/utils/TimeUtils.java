package com.kg.konggang_guide.other.utils;

import android.text.format.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author wuwang
 * @Description
 * @email 1558183202@qq.com
 * @date 2017/8/8
 */

public class TimeUtils {

    public static  CharSequence getFromTime(long timeStamp){
        return DateFormat.format("yyyy-MM-dd HH:mm", timeStamp);
    }

    //获取当前月份
    public static int getCurrMonth(){
        final Calendar c = Calendar.getInstance();
        c.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

      //  mYear = String.valueOf(c.get(Calendar.YEAR));// 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        return mMonth;
    }


    public static CharSequence getDateWithTime(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);
        long l = time.getTimeInMillis() - today.getTimeInMillis();
        int t = (int) (l / (24 * 60 * 60 * 1000));
        switch (t) {
            case 0:
                return "今天" + DateFormat.format("  HH:mm", timeStamp);
            case 1:
                return "明天" + DateFormat.format("  HH:mm", timeStamp);
            default:
                return DateFormat.format("MM-dd HH:mm", timeStamp);
        }
    }


    public static CharSequence getAllDate(long timeStamp) {
        Calendar today = Calendar.getInstance();
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(timeStamp);
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        time.set(Calendar.HOUR_OF_DAY, 0);
        time.set(Calendar.MINUTE, 0);
        time.set(Calendar.SECOND, 0);
        time.set(Calendar.MILLISECOND, 0);

        return DateFormat.format("yyyy-MM-dd HH:mm:ss", timeStamp);

    }

    public static String getCurrentTime(){
        return DateFormat.format("yyyy-MM-dd HH:mm:ss", System.currentTimeMillis()).toString();
    }

    public static String getCurrentTimeHHmm(){
        return DateFormat.format("yyyy-MM-dd HH:mm", System.currentTimeMillis()).toString();
    }

    //转换成毫秒
    public static Long getTimeLong(String formatTime){

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            long millionSeconds = sdf.parse(formatTime).getTime();//毫秒

            return millionSeconds;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0l;
    }

}
