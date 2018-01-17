package com.zlcm.server.model.apprep;

import com.zlcm.server.constant.Constant;

import java.io.Serializable;
import java.util.Date;

public class AppAdvert implements Serializable{

    private Integer aid;
    //广告海报
    private String advert_img;
    //广告文字信息
    private String text_info;
    //开始时间
    private Date start_time;
    //点击量
    private Integer hits;
    //联系方式
    private String iphone;
    //发布人
    private String nickname;
    //头像
    private String avatar;
    //信用分
    private Integer credit;
    //真实姓名
    private String real_name;
    //商家
    private Integer stor_id;
    //所在地或门店地址
    private String address;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAdvertImg() {
        return Constant.ADDRESS +advert_img;
    }

    public void setAdvertImg(String advertImg) {
        this.advert_img = advertImg == null ? null : advertImg;
    }

    public String getTextInfo() {
        return text_info;
    }

    public void setTextInfo(String textInfo) {
        this.text_info = textInfo == null ? null : textInfo;
    }

    public Date getStartTime() {
        return start_time;
    }

    public void setStartTime(Date startTime) {
        this.start_time = startTime == null ? null : startTime;
    }

    public Integer getHits() {
        return hits;
    }

    public void setHits(Integer hits) {
        this.hits = hits;
    }

    public String getPhone() {
        return iphone;
    }

    public void setPhone(String phone) {
        this.iphone = phone == null ? null : phone;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname;
    }

    public String getAvatar() {
        return Constant.ADDRESS + avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getRealName() {
        return real_name;
    }

    public void setRealName(String realName) {
        this.real_name = realName == null ? null : realName;
    }

    public Integer getSid() {
        return stor_id;
    }

    public void setSid(Integer sid) {
        this.stor_id = sid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address;
    }
}
