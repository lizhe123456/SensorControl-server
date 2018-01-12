package com.zlcm.server.service;

import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Date;

public interface AppUserService {

    //登录
    User login(String phone, HttpServletRequest request) throws SysException;

    //退出登录
    void exitLogin(String username) throws SysException;

    //获取短信验证码
    String getPhoneCode(String phone);

    //获取语音验证码
    void getVoiceCode(String phone);

    //获取用户信息
    UserDetails getUserInfo(Integer uid) throws SysException;

    //我的钱包
    Wallet getWallet(Integer uid);

    //广告协议
    void agreement();

    //实名认证
    IdentityInfo nameAuthen(Integer uid ,String name, String idCard, String front, String back) throws SysException;

    //商家认证
    void storeAuthen(Integer uid, String name, String address, String iphone, String businessLicenseUrl) throws SysException;

    //修改手机号
    void updatePhone(Integer uid,String phone) throws SysException;

    //修改头像
    void updateAvatar(Integer uid,String url);

    //意见反馈
    void feedBack(Integer uid,String desc, String phone) throws SysException;

}
