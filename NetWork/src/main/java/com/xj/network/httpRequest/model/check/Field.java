package com.xj.network.httpRequest.model.check;

/**
 * 字段属性
 */
public class Field {
    /**
     * 字段类型
     */
    private String type;
    /**
     * 字段名
     */
    private String id;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Field{" +
                "type=" + type +
                ", id='" + id + '\'' +
                '}';
    }
}
