package com.xj.network.httpRequest.utils;

import android.util.Log;


/**
 * log日志打印
 */
public class NetLog {
    private static final String TAG="NetLog";
    public static boolean isDebug=true;

    public static void e(String title,Object obj){
        if (title==null||obj==null) {
            return;
        }
        if (!isDebug){return;}
        Log.e(title,obj.toString());
    }
    public static void e(Object obj){
        e(TAG,obj);
    }
}
