package com.xj.network.httpRequest.inter;

/**
 * 检查地址ip是否可用
 */
public interface checkAddressIPListener {
    /**
     *检查成功
     * @param IP IP地址
     * @param port 端口号
     */
    void onCheckSuccess(String IP,int port);

    /**
     * 检查连接失败
     * @param IP IP地址
     * @param port 端口号
     * @param msg 信息
     */
    void onCheckFail(String IP,int port,String msg);
}
