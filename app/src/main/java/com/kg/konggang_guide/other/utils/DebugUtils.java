package com.kg.konggang_guide.other.utils;

import android.text.TextUtils;
import android.util.Log;

/**
 * Created by Administrator on 2016/9/19.
 */
public class DebugUtils {

    private static boolean isDebug = true;
    private static String Tag = "jlkfapp";
    public static void prinlnLog(String str) {
        if (isDebug) {
            if (TextUtils.isEmpty(str)){
                return;
            }
            if (str.length()<3000)
                Log.e(Tag, str);
            else {
                String str1=str.substring(0,3000);
                Log.e(Tag, str1);
                str=str.substring(3000);
                prinlnLog(str);
            }
        } else {
            Log.e(Tag, "Debug没有打开》》》》》");

        }
    }
    public static void prinlnLogRe(String str) {
        if (isDebug) {
            Log.e(Tag, str);
        } else {
            Log.e(Tag, "Debug没有打开》》》》》");

        }
    }
}
