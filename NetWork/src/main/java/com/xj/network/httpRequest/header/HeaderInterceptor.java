package com.xj.network.httpRequest.header;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xj.network.httpRequest.HttpRequestManager;
import com.xj.network.httpRequest.utils.NetLog;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Calm on 2017/12/14.
 * HeaderInterceptor
 */

public class HeaderInterceptor implements Interceptor {

    private static final String TAG = HeaderInterceptor.class.getSimpleName();
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().url().getPath();
        Request.Builder builder = request.newBuilder();
        String token = HttpRequestManager.getInstall().getToken();
        // 如果token存在，则放到请求头
        if (!TextUtils.isEmpty(token)) {
            builder.addHeader("Authorization", token);
        }
        return chain.proceed(builder.build());
    }

    private RequestBody getRequestBody(Map<String, Object> params) {
        String content = "";
        try {
            content = URLDecoder.decode(new Gson().toJson(params, HashMap.class), "utf-8");
            NetLog.e(TAG, "requestBody content:" + content);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), content);
    }
}
