package com.zlcm.server.model.apprep;

import com.zlcm.server.model.bean.Device;

import java.io.Serializable;
import java.util.List;

public class AppOrder implements Serializable {

    private int order_number;
    private List<Device> list;
    private String startTime;
    private int duration;
    private int orderState;
    private int advertState;

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
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
}
