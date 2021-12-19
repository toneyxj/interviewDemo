package com.xj.network;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.mmkv.MMKV;
import com.xj.network.httpRequest.Function.TestDataFunction;
import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.model.check.Record;
import com.xj.network.httpRequest.model.check.SourceData;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.xj.network.test", appContext.getPackageName());
    }
    @Test
    public void testSaveMMkv(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MMKV.initialize(appContext); //初始化mmkv

        String value = "{\"help\": \"https://data.gov.sg/api/3/action/help_show?name=datastore_search\", \"success\": true, \"result\": {\"resource_id\": \"a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"fields\": [{\"type\": \"int4\", \"id\": \"_id\"}, {\"type\": \"text\", \"id\": \"quarter\"}, {\"type\": \"numeric\", \"id\": \"volume_of_mobile_data\"}], \"records\": [{\"volume_of_mobile_data\": \"0.171586\", \"quarter\": \"2008-Q1\", \"_id\": 15}, {\"volume_of_mobile_data\": \"0.248899\", \"quarter\": \"2008-Q2\", \"_id\": 16}, {\"volume_of_mobile_data\": \"0.439655\", \"quarter\": \"2008-Q3\", \"_id\": 17}, {\"volume_of_mobile_data\": \"0.683579\", \"quarter\": \"2008-Q4\", \"_id\": 18}, {\"volume_of_mobile_data\": \"1.066517\", \"quarter\": \"2009-Q1\", \"_id\": 19}, {\"volume_of_mobile_data\": \"1.357248\", \"quarter\": \"2009-Q2\", \"_id\": 20}, {\"volume_of_mobile_data\": \"1.695704\", \"quarter\": \"2009-Q3\", \"_id\": 21}, {\"volume_of_mobile_data\": \"2.109516\", \"quarter\": \"2009-Q4\", \"_id\": 22}, {\"volume_of_mobile_data\": \"2.3363\", \"quarter\": \"2010-Q1\", \"_id\": 23}, {\"volume_of_mobile_data\": \"2.777817\", \"quarter\": \"2010-Q2\", \"_id\": 24}, {\"volume_of_mobile_data\": \"3.002091\", \"quarter\": \"2010-Q3\", \"_id\": 25}, {\"volume_of_mobile_data\": \"3.336984\", \"quarter\": \"2010-Q4\", \"_id\": 26}, {\"volume_of_mobile_data\": \"3.466228\", \"quarter\": \"2011-Q1\", \"_id\": 27}, {\"volume_of_mobile_data\": \"3.380723\", \"quarter\": \"2011-Q2\", \"_id\": 28}, {\"volume_of_mobile_data\": \"3.713792\", \"quarter\": \"2011-Q3\", \"_id\": 29}, {\"volume_of_mobile_data\": \"4.07796\", \"quarter\": \"2011-Q4\", \"_id\": 30}, {\"volume_of_mobile_data\": \"4.679465\", \"quarter\": \"2012-Q1\", \"_id\": 31}, {\"volume_of_mobile_data\": \"5.331562\", \"quarter\": \"2012-Q2\", \"_id\": 32}, {\"volume_of_mobile_data\": \"5.614201\", \"quarter\": \"2012-Q3\", \"_id\": 33}, {\"volume_of_mobile_data\": \"5.903005\", \"quarter\": \"2012-Q4\", \"_id\": 34}, {\"volume_of_mobile_data\": \"5.807872\", \"quarter\": \"2013-Q1\", \"_id\": 35}, {\"volume_of_mobile_data\": \"7.053642\", \"quarter\": \"2013-Q2\", \"_id\": 36}, {\"volume_of_mobile_data\": \"7.970536\", \"quarter\": \"2013-Q3\", \"_id\": 37}, {\"volume_of_mobile_data\": \"7.664802\", \"quarter\": \"2013-Q4\", \"_id\": 38}, {\"volume_of_mobile_data\": \"7.73018\", \"quarter\": \"2014-Q1\", \"_id\": 39}, {\"volume_of_mobile_data\": \"7.907798\", \"quarter\": \"2014-Q2\", \"_id\": 40}, {\"volume_of_mobile_data\": \"8.629095\", \"quarter\": \"2014-Q3\", \"_id\": 41}, {\"volume_of_mobile_data\": \"9.327967\", \"quarter\": \"2014-Q4\", \"_id\": 42}, {\"volume_of_mobile_data\": \"9.687363\", \"quarter\": \"2015-Q1\", \"_id\": 43}, {\"volume_of_mobile_data\": \"9.98677\", \"quarter\": \"2015-Q2\", \"_id\": 44}, {\"volume_of_mobile_data\": \"10.902194\", \"quarter\": \"2015-Q3\", \"_id\": 45}, {\"volume_of_mobile_data\": \"10.677166\", \"quarter\": \"2015-Q4\", \"_id\": 46}, {\"volume_of_mobile_data\": \"10.96733\", \"quarter\": \"2016-Q1\", \"_id\": 47}, {\"volume_of_mobile_data\": \"11.38734\", \"quarter\": \"2016-Q2\", \"_id\": 48}, {\"volume_of_mobile_data\": \"12.14232\", \"quarter\": \"2016-Q3\", \"_id\": 49}, {\"volume_of_mobile_data\": \"12.86429\", \"quarter\": \"2016-Q4\", \"_id\": 50}, {\"volume_of_mobile_data\": \"13.29757\", \"quarter\": \"2017-Q1\", \"_id\": 51}, {\"volume_of_mobile_data\": \"14.54179\", \"quarter\": \"2017-Q2\", \"_id\": 52}, {\"volume_of_mobile_data\": \"14.88463\", \"quarter\": \"2017-Q3\", \"_id\": 53}, {\"volume_of_mobile_data\": \"15.77653\", \"quarter\": \"2017-Q4\", \"_id\": 54}, {\"volume_of_mobile_data\": \"16.47121\", \"quarter\": \"2018-Q1\", \"_id\": 55}, {\"volume_of_mobile_data\": \"18.47368\", \"quarter\": \"2018-Q2\", \"_id\": 56}, {\"volume_of_mobile_data\": \"19.97554729\", \"quarter\": \"2018-Q3\", \"_id\": 57}, {\"volume_of_mobile_data\": \"20.43921113\", \"quarter\": \"2018-Q4\", \"_id\": 58}], \"_links\": {\"start\": \"/api/action/datastore_search?limit=44&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\", \"next\": \"/api/action/datastore_search?offset=58&limit=44&resource_id=a807b7ab-6cad-4aa6-87d0-e283a7353a0f\"}, \"offset\": 14, \"limit\": 44, \"total\": 59}}";
        Gson gson = new Gson();
        TResponse<SourceData<Record>> re = gson.fromJson(value,  new TypeToken<TResponse<SourceData<Record>>>(){}.getType());
        new TestDataFunction().saveData(re);//存入
        int size=re.getResult().getRecords().size();
        assertEquals(44, size);
    }

    /**
     * 获取缓存数据并翻译缓存数据。
     */
    @Test
    public void getSaveData(){
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        MMKV.initialize(appContext); //初始化mmkv
        TResponse<SourceData<Record>> data= new TestDataFunction().getData();//取出数据
        for (Record record : data.getResult().getRecords()) {
            System.out.println(record);
        }
        assertEquals(44, data.getResult().getRecords().size());
    }
}