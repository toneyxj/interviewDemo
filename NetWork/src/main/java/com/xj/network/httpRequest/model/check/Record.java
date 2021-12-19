package com.xj.network.httpRequest.model.check;

/**
 * 记录
 */
public class Record {
    /**
     * 值信息
     */
    private String volume_of_mobile_data;
    /**
     * 年度如2008-Q12
     */
    private String quarter;
    /**
     * 数据id号
     */
    private String _id;

    public String getVolume_of_mobile_data() {
        return volume_of_mobile_data;
    }

    public void setVolume_of_mobile_data(String volume_of_mobile_data) {
        this.volume_of_mobile_data = volume_of_mobile_data;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "Record{" +
                "volume_of_mobile_data='" + volume_of_mobile_data + '\'' +
                ", quarter='" + quarter + '\'' +
                ", _id='" + _id + '\'' +
                '}';
    }
}
