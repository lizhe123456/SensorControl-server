package com.zlcm.server.model.device;

public class Device {
  private String did;
  private String name;
  private String type;
  private String dstate;
  private String dip;
  private Double dlongitude;
  private Double dlatitude;
  private String dinfo;
  private String mac;

  public String getDid() {
    return did;
  }

  public void setDid(String did) {
    this.did = did;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
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

  public Double getDlongitude() {
    return dlongitude;
  }

  public void setDlongitude(Double dlongitude) {
    this.dlongitude = dlongitude;
  }

  public Double getDlatitude() {
    return dlatitude;
  }

  public void setDlatitude(Double dlatitude) {
    this.dlatitude = dlatitude;
  }

  public String getDinfo() {
    return dinfo;
  }

  public void setDinfo(String dinfo) {
    this.dinfo = dinfo;
  }

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }
}
