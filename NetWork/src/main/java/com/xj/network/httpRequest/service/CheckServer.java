package com.xj.network.httpRequest.service;

import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.model.check.Record;
import com.xj.network.httpRequest.model.check.SourceData;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 图书馆请求服务
 */
public interface CheckServer {
    /**
     * 根据读者证件号获取读者信息
     *
     * @return
     */
    @GET("/api/action/datastore_search")
    Observable<TResponse<SourceData<Record>>>
    datastoreSearch(@Query("offset")int offset,@Query("limit")int limit,@Query("resource_id") String resource_id);



}

