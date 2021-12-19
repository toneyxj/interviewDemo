package com.xj.network.httpRequest.inter;


import com.tencent.mmkv.MMKV;

/**
 * http请求操控接口
 * 调用流程  使用getMiJiJiaUtil 获取接口调用流程，调用使用的对应接口方法
 * 在界面ondestory中调用unSubscribeTask 解除该界面的业务请求绑定关系
 */
public interface HttpRequestImp {

    /**
     * 设置登录成功返回token
     * @param token token值
     */
    void setToken(String token);

    /**
     * 获取登录返回的token
     * @return token 唯一标识
     */
    String getToken();

    /**
     * 当前是否属于调试模式
     * @param debug true调试模式，false线上模式
     */
    void setDebug(boolean debug);

    /**
     * 获取图书馆工具类
     * @return
     */
    CheckUtil getCheckUtil();


    /**
     * 数据请求解绑TAG
     */
    void unSubscribeTask(String TAG);

    /**
     * 检查地址IP是否可用
     * @param ip IP地址
     * @param port 端口号
     * @param time 时间毫秒
     * @param listener  检查结果监听
     */
    void checkAddressIP(final String ip, final int port, int time, checkAddressIPListener listener);
}
