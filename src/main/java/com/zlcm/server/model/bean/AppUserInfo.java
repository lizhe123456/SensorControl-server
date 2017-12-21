package com.zlcm.server.model.bean;

import java.io.Serializable;

public class AppUserInfo implements Serializable{

    String avatar;
    String nickname;
    String real_name;
    String idCrad;
    String phone;
    String storephone;
    String sex;
    String birthday;
    String storename;
    String address;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getIdCrad() {
        return idCrad;
    }

    public void setIdCrad(String idCrad) {
        this.idCrad = idCrad;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStorephone() {
        return storephone;
    }

    public void setStorephone(String storephone) {
        this.storephone = storephone;
    }

    @Override
    public String toString() {
        return "AppUserInfo{" +
                "avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", real_name='" + real_name + '\'' +
                ", idCrad='" + idCrad + '\'' +
                ", phone='" + phone + '\'' +
                ", sex='" + sex + '\'' +
                ", birthday='" + birthday + '\'' +
                ", storename='" + storename + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
