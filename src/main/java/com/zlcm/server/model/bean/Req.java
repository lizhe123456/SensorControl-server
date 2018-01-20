package com.zlcm.server.model.bean;

import java.io.Serializable;

public class Req implements Serializable {
    private Integer id;

    private String date;

    private Integer reqnum;

    public Req(String date, Integer reqnum) {
        this.date = date;
        this.reqnum = reqnum;
    }

    public Req() {
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date == null ? null : date.trim();
    }

    public Integer getReqnum() {
        return reqnum;
    }

    public void setReqnum(Integer reqnum) {
        this.reqnum = reqnum;
    }
}