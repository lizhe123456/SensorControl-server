package com.zlcm.server.model.device;

import java.math.BigDecimal;

public class Device {

    private String did;
    private String name;
    private String type;
    private String dstate;
    private String dip;
    private BigDecimal dlongitude;
    private BigDecimal dlatitude;
    private String dinfo;
    private String mac;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDstate() {
        return dstate;
    }

    public void setDstate(String dstate) {
        this.dstate = dstate;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip;
    }

    public BigDecimal getDlongitude() {
        return dlongitude;
    }

    public void setDlongitude(BigDecimal dlongitude) {
        this.dlongitude = dlongitude;
    }

    public BigDecimal getDlatitude() {
        return dlatitude;
    }

    public void setDlatitude(BigDecimal dlatitude) {
        this.dlatitude = dlatitude;
    }

    public String getDinfo() {
        return dinfo;
    }

    public void setDinfo(String difo) {
        this.dinfo = difo;
    }
}
