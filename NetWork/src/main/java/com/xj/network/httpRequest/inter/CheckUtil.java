package com.xj.network.httpRequest.inter;


import com.xj.network.httpRequest.model.ResponseNoData;
import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.model.check.Record;
import com.xj.network.httpRequest.model.check.SourceData;

import io.reactivex.Observable;
import retrofit2.http.Query;

public interface CheckUtil {
    /**
     * 获取2008-2018年数据，可以固定获取offset=14 ,limit==44
     * @param TAG 请求唯一标识
     * @param callback 请求结果
     */
    void datastoreSearch(String TAG,int offset, int limit, String resource_id,  HttpRequestCallback<TResponse<SourceData<Record>>> callback);
    void datastoreSearch(String TAG,  HttpRequestCallback<TResponse<SourceData<Record>>> callback);

}
