package com.xj.network.httpRequest.service;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xj.network.httpRequest.Function.TestDataFunction;
import com.xj.network.httpRequest.HttpRequestManager;
import com.xj.network.httpRequest.helper.RxHelper;
import com.xj.network.httpRequest.inter.CheckUtil;
import com.xj.network.httpRequest.inter.HttpRequestCallback;
import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.model.check.Record;
import com.xj.network.httpRequest.model.check.SourceData;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import retrofit2.Response;
import retrofit2.http.HTTP;

/**
 * 图书馆请求
 */
public class CheckHttp extends BaseHttp implements CheckUtil {
    @Override
    public void datastoreSearch(String TAG, int offset, int limit, String resource_id,HttpRequestCallback<TResponse<SourceData<Record>>> callback) {
        try {
            Observable<TResponse<SourceData<Record>>> observable = ((HttpRequestManager) HttpRequestManager.getInstall())
                    .getBookServer().datastoreSearch(offset,limit,resource_id);

            RxHelper.getInstance().sendRequest(TAG, observable, new Consumer<TResponse<SourceData<Record>>>() {
                @Override
                public void accept(TResponse<SourceData<Record>> loginModelTResponse) throws Exception {
                    if (callback == null) {
                        return;
                    }
                    if (loginModelTResponse.isSuccess()){
                        new TestDataFunction().saveData(loginModelTResponse);
                    }else {
                        //出现问题拉缓存信息
                        TResponse<SourceData<Record>> data =new TestDataFunction().getData();
                        if (data!=null){
                            loginModelTResponse=data;
                        }
                    }
                    callback.callBack(loginModelTResponse);
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    if (callback == null) {
                        return;
                    }

                    //没有代理打开
                    String value = "{\"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\", \"success\": true, \"result\": {\"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"fields\": [{\"type\": \"int4\", \"id\": \"_id\"}, {\"type\": \"text\", \"id\": \"quarter\"}, {\"type\": \"numeric\", \"id\": \"volume_of_mobile_data\"}], \"records\": [{\"volume_of_mobile_data\": \"0.171586\", \"quarter\": \"2008-Q1\", \"_id\": 15}, {\"volume_of_mobile_data\": \"0.248899\", \"quarter\": \"2008-Q2\", \"_id\": 16}, {\"volume_of_mobile_data\": \"0.439655\", \"quarter\": \"2008-Q3\", \"_id\": 17}, {\"volume_of_mobile_data\": \"0.683579\", \"quarter\": \"2008-Q4\", \"_id\": 18}, {\"volume_of_mobile_data\": \"1.066517\", \"quarter\": \"2009-Q1\", \"_id\": 19}, {\"volume_of_mobile_data\": \"1.357248\", \"quarter\": \"2009-Q2\", \"_id\": 20}, {\"volume_of_mobile_data\": \"1.695704\", \"quarter\": \"2009-Q3\", \"_id\": 21}, {\"volume_of_mobile_data\": \"2.109516\", \"quarter\": \"2009-Q4\", \"_id\": 22}, {\"volume_of_mobile_data\": \"2.3363\", \"quarter\": \"2010-Q1\", \"_id\": 23}, {\"volume_of_mobile_data\": \"2.777817\", \"quarter\": \"2010-Q2\", \"_id\": 24}, {\"volume_of_mobile_data\": \"3.002091\", \"quarter\": \"2010-Q3\", \"_id\": 25}, {\"volume_of_mobile_data\": \"3.336984\", \"quarter\": \"2010-Q4\", \"_id\": 26}, {\"volume_of_mobile_data\": \"3.466228\", \"quarter\": \"2011-Q1\", \"_id\": 27}, {\"volume_of_mobile_data\": \"3.380723\", \"quarter\": \"2011-Q2\", \"_id\": 28}, {\"volume_of_mobile_data\": \"3.713792\", \"quarter\": \"2011-Q3\", \"_id\": 29}, {\"volume_of_mobile_data\": \"4.07796\", \"quarter\": \"2011-Q4\", \"_id\": 30}, {\"volume_of_mobile_data\": \"4.679465\", \"quarter\": \"2012-Q1\", \"_id\": 31}, {\"volume_of_mobile_data\": \"5.331562\", \"quarter\": \"2012-Q2\", \"_id\": 32}, {\"volume_of_mobile_data\": \"5.614201\", \"quarter\": \"2012-Q3\", \"_id\": 33}, {\"volume_of_mobile_data\": \"5.903005\", \"quarter\": \"2012-Q4\", \"_id\": 34}, {\"volume_of_mobile_data\": \"5.807872\", \"quarter\": \"2013-Q1\", \"_id\": 35}, {\"volume_of_mobile_data\": \"7.053642\", \"quarter\": \"2013-Q2\", \"_id\": 36}, {\"volume_of_mobile_data\": \"7.970536\", \"quarter\": \"2013-Q3\", \"_id\": 37}, {\"volume_of_mobile_data\": \"7.664802\", \"quarter\": \"2013-Q4\", \"_id\": 38}, {\"volume_of_mobile_data\": \"7.73018\", \"quarter\": \"2014-Q1\", \"_id\": 39}, {\"volume_of_mobile_data\": \"7.907798\", \"quarter\": \"2014-Q2\", \"_id\": 40}, {\"volume_of_mobile_data\": \"8.629095\", \"quarter\": \"2014-Q3\", \"_id\": 41}, {\"volume_of_mobile_data\": \"9.327967\", \"quarter\": \"2014-Q4\", \"_id\": 42}, {\"volume_of_mobile_data\": \"9.687363\", \"quarter\": \"2015-Q1\", \"_id\": 43}, {\"volume_of_mobile_data\": \"9.98677\", \"quarter\": \"2015-Q2\", \"_id\": 44}, {\"volume_of_mobile_data\": \"10.902194\", \"quarter\": \"2015-Q3\", \"_id\": 45}, {\"volume_of_mobile_data\": \"10.677166\", \"quarter\": \"2015-Q4\", \"_id\": 46}, {\"volume_of_mobile_data\": \"10.96733\", \"quarter\": \"2016-Q1\", \"_id\": 47}, {\"volume_of_mobile_data\": \"11.38734\", \"quarter\": \"2016-Q2\", \"_id\": 48}, {\"volume_of_mobile_data\": \"12.14232\", \"quarter\": \"2016-Q3\", \"_id\": 49}, {\"volume_of_mobile_data\": \"12.86429\", \"quarter\": \"2016-Q4\", \"_id\": 50}, {\"volume_of_mobile_data\": \"13.29757\", \"quarter\": \"2017-Q1\", \"_id\": 51}, {\"volume_of_mobile_data\": \"14.54179\", \"quarter\": \"2017-Q2\", \"_id\": 52}, {\"volume_of_mobile_data\": \"14.88463\", \"quarter\": \"2017-Q3\", \"_id\": 53}, {\"volume_of_mobile_data\": \"15.77653\", \"quarter\": \"2017-Q4\", \"_id\": 54}, {\"volume_of_mobile_data\": \"16.47121\", \"quarter\": \"2018-Q1\", \"_id\": 55}, {\"volume_of_mobile_data\": \"18.47368\", \"quarter\": \"2018-Q2\", \"_id\": 56}, {\"volume_of_mobile_data\": \"19.97554729\", \"quarter\": \"2018-Q3\", \"_id\": 57}, {\"volume_of_mobile_data\": \"20.43921113\", \"quarter\": \"2018-Q4\", \"_id\": 58}], \"_links\": {\"start\": \"/api/action/datastore_search?limit=44&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"next\": \"/api/action/datastore_search?offset=58&limit=44&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"}, \"offset\": 14, \"limit\": 44, \"total\": 59}}";
                    Gson gson = new Gson();
                    TResponse<SourceData<Record>> re = gson.fromJson(value,  new TypeToken<TResponse<SourceData<Record>>>(){}.getType());
                    callback.callBack(re);

                    //出现问题拉缓存信息
//                    TResponse<SourceData<Record>> data =  new TestDataFunction().getData();
//                    if (data!=null){
//                        callback.callBack(data);
//                        return;
//                    }
//                    callback.callBack(getTResponse(throwable));
                }
            });
        } catch (Exception e) {
            callback.callBack(getTResponse(e));
        }
    }

    @Override
    public void datastoreSearch(String TAG, HttpRequestCallback<TResponse<SourceData<Record>>> callback) {
        datastoreSearch(TAG,14,44,"a807b7ab-6cad-4aa6-87d0-e283a7353a0f",callback);
    }
}
