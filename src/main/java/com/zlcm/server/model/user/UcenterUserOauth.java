package com.zlcm.server.model.user;

import java.io.Serializable;

public class UcenterUserOauth implements Serializable {

  private Integer user_oauth_id;
  private Integer user_id;
  private Integer oauth_id;
  private String open_id;
  private Integer status;
  private java.sql.Timestamp create_time;

  public Integer getUser_oauth_id() {
    return user_oauth_id;
  }

  public void setUser_oauth_id(Integer user_oauth_id) {
    this.user_oauth_id = user_oauth_id;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public Integer getOauth_id() {
    return oauth_id;
  }

  public void setOauth_id(Integer oauth_id) {
    this.oauth_id = oauth_id;
  }

  public String getOpen_id() {
    return open_id;
  }

  public void setOpen_id(String open_id) {
    this.open_id = open_id;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
}
