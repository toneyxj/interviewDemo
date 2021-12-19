package com.xj.interview.main.viewImp;

import com.xj.interview.Model.ShowModel;
import com.xj.interview.inter.BaseViewInter;
import com.xj.network.httpRequest.model.check.Record;

import java.util.List;

public interface MainPageViewImp extends BaseViewInter {


    void showRecord(float maxValue,List<ShowModel> records);
}
