package com.zlcm.server.model.bean;

import java.io.Serializable;

public class IdentityInfo implements Serializable {
    private Integer uid;

    private String address;

    private String idcard;

    private String begin;

    private String department;

    private String end;

    private Integer frontOrderid;

    private Integer backOrderid;

    private String frontImg;

    private String backImg;


    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard == null ? null : idcard.trim();
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin == null ? null : begin.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end == null ? null : end.trim();
    }

    public Integer getFrontOrderid() {
        return frontOrderid;
    }

    public void setFrontOrderid(Integer frontOrderid) {
        this.frontOrderid = frontOrderid;
    }

    public Integer getBackOrderid() {
        return backOrderid;
    }

    public void setBackOrderid(Integer backOrderid) {
        this.backOrderid = backOrderid;
    }

    public String getFrontImg() {
        return frontImg;
    }

    public void setFrontImg(String frontImg) {
        this.frontImg = frontImg == null ? null : frontImg.trim();
    }

    public String getBackImg() {
        return backImg;
    }

    public void setBackImg(String backImg) {
        this.backImg = backImg == null ? null : backImg.trim();
    }
}