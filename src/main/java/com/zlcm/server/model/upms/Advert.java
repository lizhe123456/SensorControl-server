package com.zlcm.server.model.upms;

import java.io.Serializable;
import java.util.Date;

public class Advert implements Serializable {
    private Integer id;

    private Integer userId;

    private String did;

    private String info;

    private String desc;

    private Date createTime;

    private Byte state;

    private String continuedTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did == null ? null : did.trim();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc == null ? null : desc.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getContinuedTime() {
        return continuedTime;
    }

    public void setContinuedTime(String continuedTime) {
        this.continuedTime = continuedTime == null ? null : continuedTime.trim();
    }
}