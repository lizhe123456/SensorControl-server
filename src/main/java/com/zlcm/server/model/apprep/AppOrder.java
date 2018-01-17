package com.zlcm.server.model.apprep;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.model.bean.Device;

import java.io.Serializable;
import java.util.List;

public class AppOrder implements Serializable {

    private int order_number;
    private List<Device> list;
    private String startTime;
    private String duration;
    private int orderState;
    private int advertState;
    private float price;
    private String advert;
    private String desc;

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

    public List<Device> getList() {
        return list;
    }

    public void setList(List<Device> list) {
        this.list = list;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getOrderState() {
        return orderState;
    }

    public void setOrderState(int orderState) {
        this.orderState = orderState;
    }

    public int getAdvertState() {
        return advertState;
    }

    public void setAdvertState(int advertState) {
        this.advertState = advertState;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAdvert() {
        return Constant.ADDRESS + advert;
    }

    public void setAdvert(String advert) {
        this.advert = advert;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


}
