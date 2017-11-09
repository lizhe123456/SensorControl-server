package com.zlcm.server.model.user;

public class UcenterUserDetails {
  private Long user_id;
  private String signature;
  private String real_name;
  private java.sql.Timestamp birthday;
  private String question;
  private String answer;
  private Long real_name_auth;

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getSignature() {
    return signature;
  }

  public void setSignature(String signature) {
    this.signature = signature;
  }

  public String getReal_name() {
    return real_name;
  }

  public void setReal_name(String real_name) {
    this.real_name = real_name;
  }

  public java.sql.Timestamp getBirthday() {
    return birthday;
  }

  public void setBirthday(java.sql.Timestamp birthday) {
    this.birthday = birthday;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  public Long getReal_name_auth() {
    return real_name_auth;
  }

  public void setReal_name_auth(Long real_name_auth) {
    this.real_name_auth = real_name_auth;
  }
}
