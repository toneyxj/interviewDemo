package com.xj.interview.Model;

import com.xj.network.httpRequest.model.check.Record;

/**
 * 根据年份展示的数据集合
 */
public class ShowModel {
    private int  year=0;
    private float  data;

    public ShowModel() {
    }

    public ShowModel(int year, float data) {
        this.year = year;
        this.data = data;
    }

    public ShowModel initData(Record record){
        String year=record.getQuarter();
        try {
            int mYear=Integer.parseInt(year.substring(0,year.indexOf("-")));
            float data=Float.parseFloat(record.getVolume_of_mobile_data());
            if (this.year==mYear){
                addData(data);
            }else if (this.year==0){
                this.year=mYear;
                this.data=data;
            }else {
                return new ShowModel(mYear,data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return this;
    }


    private void addData(float data){
        this.data+=data;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * 获取保留两位小数的int值
     * @return
     */
    public int getDataTo2Int(){
        return (int) (data*100);
    }

    public float getData() {
        return data;
    }

    public void setData(float data) {
        this.data = data;
    }
}
