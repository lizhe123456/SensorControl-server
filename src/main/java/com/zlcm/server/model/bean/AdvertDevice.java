package com.zlcm.server.model.bean;

import java.io.Serializable;

public class AdvertDevice implements Serializable {
    private Integer id;

    private Integer aid;

    private Integer did;

    public AdvertDevice(Integer aid, Integer did) {
        this.aid = aid;
        this.did = did;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}