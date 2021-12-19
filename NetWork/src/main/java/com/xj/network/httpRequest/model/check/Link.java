package com.xj.network.httpRequest.model.check;

/**
 * 连接
 */
public class Link {
    private String start;
    private String next;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Link{" +
                "start='" + start + '\'' +
                ", next='" + next + '\'' +
                '}';
    }
}
