package com.zlcm.server.model.device;

import java.io.Serializable;
import java.math.BigDecimal;

public class Device implements Serializable {
    private String did;

    private String name;

    private String type;

    private Byte dstate;

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

    private static final long serialVersionUID = 1L;

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Byte getDstate() {
        return dstate;
    }

    public void setDstate(Byte dstate) {
        this.dstate = dstate;
    }

    public String getDip() {
        return dip;
    }

    public void setDip(String dip) {
        this.dip = dip == null ? null : dip.trim();
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

    public void setDinfo(String dinfo) {
        this.dinfo = dinfo == null ? null : dinfo.trim();
    }
}