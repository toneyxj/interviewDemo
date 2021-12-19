package com.xj.network.httpRequest.model;

/**
 * 无数据返回，只有成功与否返回数据结构
 */
public class ResponseNoData {
    /**
     * help帮助
     */
    private String help;
    /**
     *
     * 请求结果
     */
    private boolean success;

    private String msg;

    /**
     * 当前请求结果是否成功
     * @return
     */
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getHelp() {
        return help;
    }

    public void setHelp(String help) {
        this.help = help;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
