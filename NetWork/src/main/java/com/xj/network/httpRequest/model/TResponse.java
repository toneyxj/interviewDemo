package com.xj.network.httpRequest.model;


public class TResponse<T> extends ResponseNoData {
    /**
     * 返回数据集合
     */
    private T result;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
