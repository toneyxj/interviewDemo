package com.xj.interview.utils;

import android.util.Log;

public class Applog {
    public static boolean isDebug=true;
    private static final String TAG="NetLog";

    public static void e(String title, Object obj){
        if (title==null||obj==null) {
            return;
        }
        if (isDebug) {
            Log.e(title, obj.toString());
        }
    }
    public static void e(Object obj){
        e(TAG,obj);
    }
}
