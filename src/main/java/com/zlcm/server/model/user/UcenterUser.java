package com.zlcm.server.model.user;

public class UcenterUser {
  private Integer user_id;
  private String username;
  private String password;
  private String salt;
  private String nickname;
  private String email;
  private String phone;
  private Integer state;
  private String avatar;
  private String register_ip;
  private java.sql.Timestamp register_time;
  private String last_login_ip;
  private java.sql.Timestamp last_login_time;
  private Integer sex;
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

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
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

  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }
}
