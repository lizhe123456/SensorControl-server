package com.zlcm.server.model.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class Device implements Serializable {
    private Integer did;

    private String mac;

    private String address;

    private BigDecimal dlatitude;

    private BigDecimal dlongitude;

    private String ip;

    private Byte state;

    private Integer household;

    private String desc;

    private String province;

    private String city;

    private String area;

    private static final long serialVersionUID = 1L;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac == null ? null : mac.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getDlatitude() {
        return dlatitude;
    }

    public void setDlatitude(BigDecimal dlatitude) {
        this.dlatitude = dlatitude;
    }

    public BigDecimal getDlongitude() {
        return dlongitude;
    }

    public void setDlongitude(BigDecimal dlongitude) {
        this.dlongitude = dlongitude;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Integer getHousehold() {
        return household;
    }

    public void setHousehold(Integer household) {
        this.household = household;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }
}