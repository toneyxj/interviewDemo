package com.xj.interview;

import android.app.Application;
import android.os.HandlerThread;
import android.util.Log;

import com.tencent.mmkv.MMKV;
import com.tencent.mmkv.MMKVContentChangeNotification;
import com.tencent.mmkv.MMKVHandler;
import com.tencent.mmkv.MMKVLogLevel;
import com.tencent.mmkv.MMKVRecoverStrategic;

public class APP extends Application  {
    @Override
    public void onCreate() {
        super.onCreate();
        MMKV.initialize(this); //初始化mmkv
    }


}
