package com.zlcm.server.model.user;

public class UcenterUserLog {

  private Integer user_log_id;
  private Integer user_id;
  private String content;
  private String ip;
  private String agent;
  private java.sql.Timestamp create_time;

  public Integer getUser_log_id() {
    return user_log_id;
  }

  public void setUser_log_id(Integer user_log_id) {
    this.user_log_id = user_log_id;
  }

  public Integer getUser_id() {
    return user_id;
  }

  public void setUser_id(Integer user_id) {
    this.user_id = user_id;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getAgent() {
    return agent;
  }

  public void setAgent(String agent) {
    this.agent = agent;
  }

  public java.sql.Timestamp getCreate_time() {
    return create_time;
  }

  public void setCreate_time(java.sql.Timestamp create_time) {
    this.create_time = create_time;
  }
}
