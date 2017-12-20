package com.zlcm.server.model.bean;

import java.io.Serializable;
import java.util.Date;

public class UserDetails implements Serializable {
    private Integer uid;

    private String nickname;

    private String avatar;

    private String realName;

    private String idcrad;

    private String phone;

    private Byte sex;

    private Date birthday;

    private String email;

    private Integer storId;

    private static final long serialVersionUID = 1L;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getIdcrad() {
        return idcrad;
    }

    public void setIdcrad(String idcrad) {
        this.idcrad = idcrad == null ? null : idcrad.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getStorId() {
        return storId;
    }

    public void setStorId(Integer storId) {
        this.storId = storId;
    }
}