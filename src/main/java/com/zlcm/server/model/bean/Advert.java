package com.zlcm.server.model.bean;


import com.zlcm.server.constant.Constant;

import java.util.Date;

public class Advert {

  private Integer aid;

  private Integer did;

  private Integer uid;

  private String advertImg;

  private String textInfo;

  private Date startTime;

  private Long duration;

  private Byte state;

  private Integer hits;

  private Byte recommend;

  private String iphone;

  private Integer sid;

  private String auditingInfo;
  private String address;

  public Advert() {
  }

  public Advert(Integer uid, String advertImg, String textInfo, Date startTime, Long duration, String iphone, String address) {
    this.uid = uid;
    this.advertImg = advertImg;
    this.textInfo = textInfo;
    this.startTime = startTime;
    this.duration = duration;
    this.iphone = iphone;
    this.address = address;
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

  public Integer getUid() {
    return uid;
  }

  public void setUid(Integer uid) {
    this.uid = uid;
  }

  public String getAdvertImg() {
    return advertImg;
  }

  public void setAdvertImg(String advertImg) {
    this.advertImg = advertImg == null ? null : advertImg.trim();
  }

  public String getTextInfo() {
    return textInfo;
  }

  public void setTextInfo(String textInfo) {
    this.textInfo = textInfo == null ? null : textInfo.trim();
  }

  public Date getStartTime() {
    return startTime;
  }

  public void setStartTime(Date startTime) {
    this.startTime = startTime;
  }

  public Long getDuration() {
    return duration;
  }

  public void setDuration(Long duration) {
    this.duration = duration;
  }

  public Byte getState() {
    return state;
  }

  public void setState(Byte state) {
    this.state = state;
  }

  public Integer getHits() {
    return hits;
  }

  public void setHits(Integer hits) {
    this.hits = hits;
  }

  public Byte getRecommend() {
    return recommend;
  }

  public void setRecommend(Byte recommend) {
    this.recommend = recommend;
  }

  public String getIphone() {
    return iphone;
  }

  public void setIphone(String iphone) {
    this.iphone = iphone == null ? null : iphone.trim();
  }

  public Integer getSid() {
    return sid;
  }

  public void setSid(Integer sid) {
    this.sid = sid;
  }

  public String getAuditingInfo() {
    return auditingInfo;
  }

  public void setAuditingInfo(String auditingInfo) {
    this.auditingInfo = auditingInfo == null ? null : auditingInfo.trim();
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }
}
