package com.zlcm.server.service;

import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.IdentityInfo;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.model.bean.UserDetails;
import com.zlcm.server.model.bean.Wallet;

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
    void nameAuthen(Integer uid , String name, String idCrad,int sex,Date birthday,
                    @NotNull IdentityInfo info) throws SysException;

    //商家认证
    void storeAuthen(Integer uid,String organization,String address,String legalPerson,String iphone,String businessLicenseUrl);

    //修改手机号
    void updatePhone(Integer uid,String phone);

    //修改头像
    void updateAvatar(Integer uid,String url);

}
