package com.zlcm.server.model.bean;

import java.io.Serializable;

public class OrderDevice implements Serializable {
    private Integer id;

    private Integer oid;

    private Integer did;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOid() {
        return oid;
    }

    public void setOid(Integer oid) {
        this.oid = oid;
    }

    public Integer getDid() {
        return did;
    }

    public void setDid(Integer did) {
        this.did = did;
    }
}