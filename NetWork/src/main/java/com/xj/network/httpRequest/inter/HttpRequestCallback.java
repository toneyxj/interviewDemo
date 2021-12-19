package com.xj.network.httpRequest.inter;

/**
 * http请求返回
 */
public interface HttpRequestCallback<T> {
    /**
     * 数据请求返回参数
     * @param data 数据集合
     */
    void callBack(T data);
}
