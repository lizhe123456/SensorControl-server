package com.zlcm.server.model.bean;

import java.io.Serializable;
import java.util.Date;

public class Advert implements Serializable {
    private Integer aid;

    private Integer did;

    private String img;

    private String text;

    private Date startTime;

    private Byte state;

    private String auditingInfo;

    private String duration;

    private static final long serialVersionUID = 1L;

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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getAuditingInfo() {
        return auditingInfo;
    }

    public void setAuditingInfo(String auditingInfo) {
        this.auditingInfo = auditingInfo == null ? null : auditingInfo.trim();
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration == null ? null : duration.trim();
    }
}