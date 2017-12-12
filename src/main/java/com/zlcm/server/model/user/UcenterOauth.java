package com.zlcm.server.model.user;

import java.io.Serializable;

public class UcenterOauth implements Serializable {
  private Long oauth_id;
  private String name;

  public Long getOauth_id() {
    return oauth_id;
  }

  public void setOauth_id(Long oauth_id) {
    this.oauth_id = oauth_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
