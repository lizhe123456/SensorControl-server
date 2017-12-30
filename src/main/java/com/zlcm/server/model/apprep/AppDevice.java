package com.zlcm.server.model.apprep;

import java.io.Serializable;
import java.math.BigDecimal;

public class AppDevice implements Serializable{
    private Integer did;

    private String address;

    private BigDecimal dlatitude;

    private BigDecimal dlongitude;

    private String ip;

    private Integer household;

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        this.ip = ip;
    }

    public Integer getHousehold() {
        return household;
    }

    public void setHousehold(Integer household) {
        this.household = household;
    }
}
