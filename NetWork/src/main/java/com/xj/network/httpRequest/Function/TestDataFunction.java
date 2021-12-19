package com.xj.network.httpRequest.Function;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.model.check.Record;
import com.xj.network.httpRequest.model.check.SourceData;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.LinkedHashMap;

import okhttp3.Response;

/**
 * 测试网络获取数据缓存设置
 */
public class TestDataFunction extends BaseFunction<TResponse<SourceData<Record>>> {
    @Override
    protected String getCode() {
        return "test-data";
    }

    @Override
    protected String getKey() {
        return "year-Data";
    }

    @Override
    protected boolean isSaveData() {
        return super.isSaveData();
        //是否执行缓存操作这里可以自定义缓存时间，默认按照系统时间计算，没有使用开机运行时间是为了定制按照时间段进行更新逻辑。
    }

    @Override
    protected Type getType() {
        return new TypeToken<TResponse<SourceData<Record>>>(){}.getType();
    }

}
