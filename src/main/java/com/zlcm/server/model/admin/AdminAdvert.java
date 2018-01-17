package com.zlcm.server.model.admin;

import com.zlcm.server.util.DateUtil;

import java.io.Serializable;
import java.util.Date;

public class AdminAdvert implements Serializable{

    private Integer aid;
    private Integer uid;
    private String advert_img;
    private String text_info;
    private Date start_time;
    private long duration;
    private Byte state;
    private String iphone;
    private String address;
    private String name;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAdvert_img() {
        return advert_img;
    }

    public void setAdvert_img(String advert_img) {
        this.advert_img = advert_img;
    }

    public String getText_info() {
        return text_info;
    }

    public void setText_info(String text_info) {
        this.text_info = text_info;
    }

    public String getStart_time() {
        return start_time == null ? null : DateUtil.formatDateTime(start_time);
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public long getDuration() {
        return duration/24/60/60/1000;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
