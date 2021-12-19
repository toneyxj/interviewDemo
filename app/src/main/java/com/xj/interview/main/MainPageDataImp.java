package com.xj.interview.main;

import com.xj.interview.Model.ShowModel;
import com.xj.interview.inter.HttpTagInter;
import com.xj.interview.inter.PageViewInter;
import com.xj.interview.main.inter.MainPageInter;
import com.xj.interview.main.viewImp.MainPageViewImp;
import com.xj.network.httpRequest.HttpRequestManager;
import com.xj.network.httpRequest.inter.HttpRequestCallback;
import com.xj.network.httpRequest.model.TResponse;
import com.xj.network.httpRequest.model.check.Record;
import com.xj.network.httpRequest.model.check.SourceData;

import java.util.ArrayList;
import java.util.List;

/**
 * 主界面数据暂时utils
 */
public class MainPageDataImp implements MainPageInter, PageViewInter, HttpTagInter {

    private  List<ShowModel> models = null;
    private MainPageViewImp viewImp;

    public MainPageDataImp(MainPageViewImp viewImp) {
        this.viewImp = viewImp;
    }

    @Override
    public void requestData() {
        if (models==null){
            models = new ArrayList<>();
        }else {
            models.clear();
        }
        HttpRequestManager.getInstall().getCheckUtil().datastoreSearch(getHttpTAG(), new HttpRequestCallback<TResponse<SourceData<Record>>>() {
            @Override
            public void callBack(TResponse<SourceData<Record>> data) {
                if (data.isSuccess()) {
                    models.clear();
                    ShowModel model=null;
                    for (Record record : data.getResult().getRecords()) {
                        //如果初始化没有model进行数据添加
                        if (model==null){
                            model=new ShowModel();
                            models.add(model);
                        }
                        //在这里对数据进行处理，处理后的数据添加
                        ShowModel me= model.initData(record);
                        if (me!=model){
                            models.add(me);
                            model=me;
                        }

                    }
                    showRecord();
                } else {
                    if (viewImp != null) {
                        viewImp.showToast(data.getMsg());
                    }
                }
            }
        });
    }

    @Override
    public void onPageCreate() {
        if (models == null) {//没有数据拉取数据
            requestData();
        } else {//如果数据已经存在直接显示
           showRecord();
        }
    }
    private void showRecord(){
        if (viewImp != null) {
            float maxValue=0;
            for (ShowModel record : models) {
                float data=record.getData();
                if (data>maxValue){
                    maxValue=data;
                }
            }
            viewImp.showRecord(maxValue*1.05f,models);
        }
    }

    @Override
    public void onPageResume() {

    }

    @Override
    public void onPageStop() {
        viewImp = null;
    }

    @Override
    public void onPageDestroy() {
        models.clear();
        models = null;
        HttpRequestManager.getInstall().unSubscribeTask(getHttpTAG());
    }

    @Override
    public String getHttpTAG() {
        return "test-get-data";
    }
}

