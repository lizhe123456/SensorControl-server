package com.zlcm.server.model.user;

public class UcenterUserOauth {

  private Long user_oauth_id;
  private Long user_id;
  private Long oauth_id;
  private String open_id;
  private Long status;
  private java.sql.Timestamp create_time;

  public Long getUser_oauth_id() {
    return user_oauth_id;
  }

  public void setUser_oauth_id(Long user_oauth_id) {
    this.user_oauth_id = user_oauth_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public Long getOauth_id() {
    return oauth_id;
  }

  public void setOauth_id(Long oauth_id) {
    this.oauth_id = oauth_id;
  }

  public String getOpen_id() {
    return open_id;
  }

  public void setOpen_id(String open_id) {
    this.open_id = open_id;
  }

  public Long getStatus() {
    return status;
  }

  public void setStatus(Long status) {
    this.status = status;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
}
