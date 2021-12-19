package com.xj.network.httpRequest.Function;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Response;

public abstract class BaseFunction<T> {
    //创建线程池来处理数据的加解密
    private static ConcurrentHashMap<String, Long> times = new ConcurrentHashMap<>();
    private MMKV mmkv;

    /**
     * 获取缓存控件
     *
     * @return 缓存控件
     */
    private MMKV getMMKV() {
        if (mmkv == null) {
            mmkv = MMKV.mmkvWithID(getCode(), MMKV.MULTI_PROCESS_MODE);
        }
        return mmkv;
    }

    /**
     * 获取缓存文件标志
     *
     * @return 缓存文件标志
     */
    protected abstract String getCode();

    /**
     * 获取缓存的可以值信息
     *
     * @return key定义值
     */
    protected abstract String getKey();

    /**
     * 缓存间隔时间
     *
     * @return
     */
    protected int intervalTime() {
        return 1000 * 60 * 10;//10分钟内不进行重复的缓存
    }


    /**
     * 进行缓存信息
     *
     * @param msg
     */
    public void saveData(T msg) {
        if (isSaveData()) {
            times.put(getKey(), System.currentTimeMillis());//记录上次缓存执行时间
            Gson gson = new Gson();
            String result = gson.toJson(msg);
//            System.out.println(result);
            getMMKV().encode(getKey(), result);
        }
    }

    /**
     * 是否进行缓存操作
     *
     * @return true 进行，false抛弃
     */
    protected boolean isSaveData() {
        Long lastTime = times.get(getKey());
        if (lastTime == null) {
            lastTime = 0l;
        }
        long time = System.currentTimeMillis();
        return time - lastTime > intervalTime();
    }

    /**
     * 获取缓存数据
     *
     * @return 缓存类
     */
    public T getData() {
        String value = getMMKV().decodeString(getKey(), "");
        if (value == null || value.length() == 0) {
            return null;
        } else {
            Gson gson = new Gson();
            return gson.fromJson(value, getType());
        }
    }

    protected abstract Type getType();

}
