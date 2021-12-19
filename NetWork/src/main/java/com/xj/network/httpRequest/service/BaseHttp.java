package com.xj.network.httpRequest.service;


import com.xj.network.httpRequest.helper.RxHelper;
import com.xj.network.httpRequest.model.TResponse;

public class BaseHttp {
    protected TResponse getTResponse(Throwable throwable) {
        throwable.printStackTrace();
        TResponse response = new TResponse();
        response.setSuccess(false);
        response.setMsg(RxHelper.getInstance().getErrorInfo(throwable));
        return response;
    }
}
