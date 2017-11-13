package com.zlcm.server.model.user;

public class UcenterUser {
  private Integer user_id;
  private String username;
  private String password;
  private String salt;
  private Integer state;
  private String register_ip;
  private java.sql.Timestamp register_time;
  private String last_login_ip;
  private java.sql.Timestamp last_login_time;
  private Integer locked;

  public Integer getLocked() {
    return locked;
  }

  public void setLocked(Integer locked) {
    this.locked = locked;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }


  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }


  public String getRegister_ip() {
    return register_ip;
  }

  public void setRegister_ip(String register_ip) {
    this.register_ip = register_ip;
  }

  public java.sql.Timestamp getRegister_time() {
    return register_time;
  }

  public void setRegister_time(java.sql.Timestamp register_time) {
    this.register_time = register_time;
  }

  public String getLast_login_ip() {
    return last_login_ip;
  }

  public void setLast_login_ip(String last_login_ip) {
    this.last_login_ip = last_login_ip;
  }

  public java.sql.Timestamp getLast_login_time() {
    return last_login_time;
  }

  public void setLast_login_time(java.sql.Timestamp last_login_time) {
    this.last_login_time = last_login_time;
  }

}
