package com.zlcm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.*;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.Sms;
import com.zlcm.server.model.bean.*;
import com.zlcm.server.service.AppUserService;
import com.zlcm.server.util.DateUtil;
import com.zlcm.server.util.IPUtils;
import com.zlcm.server.util.JackJsonUtils;
import com.zlcm.server.util.StringReplaceUtil;
import com.zlcm.server.util.sms.SmsApi;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ytx.org.apache.http.client.utils.HttpClientUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

@Transactional
@Service
public class AppUserServiceImpl implements AppUserService {

    private static Logger _log = LoggerFactory.getLogger(AppUserServiceImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserDetailsMapper userDetailsMapper;

    @Autowired
    IdentityInfoMapper identityInfoMapper;

    @Autowired
    FeedbackMapper feedbackMapper;

    @Autowired
    StoreMapper storeMapper;

    @Override
    public User login(String phone, HttpServletRequest request) throws SysException {

        User user = getUser(phone);
        if (user == null){
            user = new User();
            user.setUsername(phone);
            user.setLastLoginTime(new Date());
            user.setLastLoginIp(IPUtils.getIpAddr(request));
            try{
                userMapper.save(user);
                UserDetails details = new UserDetails();
                details.setUid(user.getUid());
                details.setPhone(phone);
                details.setNickname(phone);
                details.setAvatar("http://localhost:8080/img/avatar.jpg");
                try {
                    userDetailsMapper.save(details);
                }catch (DataAccessException e){
                    userMapper.deleteByPK(user.getUid());
                    _log.error(e.getMessage());
                    throw new SysException(Constant.ADD_ERROR);
                }
            }catch (DataAccessException e){
                _log.error(e.getMessage());
                throw new SysException(Constant.ADD_ERROR);
            }
        }
        user.setState((byte) 1);
        try {
            userMapper.update(user);
        }catch (DataAccessException e) {
            _log.error(e.getMessage());
            throw new SysException(Constant.UPDATE_ERROR);
        }
        return user;
    }



    @Override
    public void exitLogin(String username) throws SysException {
        User user = getUser(username);
        user.setState((byte) 0);
        try {
            userMapper.update(user);
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.UPDATE_ERROR);
        }
    }

    @Override
    public String getPhoneCode(String phone) {
        String code = SmsApi.getRandomStr(6,0);
        try {
            String s = SmsApi.sendM(phone,code);
            Sms sms = JackJsonUtils.fromJson(s, Sms.class);
            if (sms == null){
                return null;
            }
            if (sms.getCode() == 22){
                return null;
            }
        } catch (IOException e) {
            return null;
        }
        return code;
    }

    @Override
    public void getVoiceCode(String phone) {

    }

    @Override
    public UserDetails getUserInfo(Integer uid) throws SysException {
        UserDetails details;
        try {
            details = userDetailsMapper.get(uid);
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
        return details;
    }

    @Override
    public Wallet getWallet(Integer uid) {
        return null;
    }

    @Override
    public void agreement() {

    }

    @Override
    public IdentityInfo nameAuthen(Integer uid ,String name, String idCard, String front, String back) throws SysException {
        try {
            UserDetails userDetails = getUserDetails(uid);
            if (userDetails != null && !TextUtils.isEmpty(userDetails.getRealName()) && !TextUtils.isEmpty(userDetails.getIdcrad())){
                //已实名
                return identityInfoMapper.get(uid);
            }
            String frontJson = SmsApi.authName(front,"front");
            String backJson = SmsApi.authName(back,"back");
            AuthFront authFront = JSON.parseObject(frontJson,AuthFront.class);
            AuthBack authBack = JSON.parseObject(backJson,AuthBack.class);
            if (authFront.getError_code() != 0 && authBack.getError_code() != 0){
                //验证错误
                return null;
            }
            if (!idCard.equals(authFront.getResult().getIdcard()) && !name.equals(authFront.getResult().getRealname())){
                //身份证是否一致
                return null;
            }
            IdentityInfo identityInfo = identityInfoMapper.get(uid);
            if (identityInfo == null){
                identityInfo = new IdentityInfo();
                identityInfo.setUid(uid);
                identityInfo.setBackImg(back);
                identityInfo.setFrontImg(front);
                identityInfo.setFrontOrderid(authFront.getResult().getOrderid());
                identityInfo.setBackOrderid(authBack.getResult().getOrderid());
                identityInfo.setBegin(authBack.getResult().getBegin());
                identityInfo.setEnd(authBack.getResult().getEnd());
                identityInfo.setDepartment(authBack.getResult().getDepartment());
                identityInfo.setAddress(authFront.getResult().getAddress());
                identityInfoMapper.save(identityInfo);
                userDetails.setRealName(name);
                userDetails.setIdcrad(authFront.getResult().getIdcard());
                userDetails.setSex((byte) (authFront.getResult().getSex() == "男" ? 0 : 1));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
                ParsePosition pos = new ParsePosition(0);
                Date strtodate = formatter.parse(authFront.getResult().getBorn(), pos);
                userDetails.setBirthday(strtodate);
                userDetailsMapper.update(userDetails);
                return identityInfo;
            }else {
                //TODO:已认证
                return identityInfoMapper.get(uid);
            }
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.UPDATE_ERROR);
        }
    }

    @Override
    public void storeAuthen(Integer uid, String name, String address, String iphone, String businessLicenseUrl) throws SysException {
        try {
            UserDetails userDetails = userDetailsMapper.get(uid);
            if (userDetails != null && userDetails.getStorId() != 0){
                //已认证
                return;
            }
            Store store = new Store();
            store.setAddress(name);
            store.setIphone(iphone);
            store.setBusinessLicense(businessLicenseUrl);
            store.setName(name);
            storeMapper.save(store);
            userDetails.setStorId(store.getSid());
            userDetailsMapper.update(userDetails);
            return;
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.UPDATE_ERROR);
        }
    }

    @Override
    public void updatePhone(Integer uid,String phone) throws SysException {
        User user = getUser(phone);
        if (user == null){
            User user1 = userMapper.get(uid);
            user1.setUsername(phone);
            UserDetails userDetails = userDetailsMapper.get(uid);
            if (userDetails != null && user1 != null){
                userDetails.setPhone(phone);
                userDetailsMapper.update(userDetails);
                userMapper.update(user1);
            }else {
                throw new SysException(Constant.UPDATE_ERROR);
            }
        }
    }

    @Override
    public void updateAvatar(Integer uid,String url) {
        UserDetails userDetails = userDetailsMapper.get(uid);
        if (userDetails != null){
            userDetails.setAvatar(url);
            userDetailsMapper.update(userDetails);
        }else {
            //TODO:用户信息为空
        }
    }

    @Override
    public void feedBack(Integer uid,String desc, String phone) throws SysException {
        try {
            Feedback feedback = new Feedback();
            feedback.setUid(uid);
            feedback.setIphone(phone);
            feedback.setText(desc);
            feedbackMapper.save(feedback);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.ADD_ERROR);
        }
    }

    public User getUser(String username) throws SysException {
        User user;
        try{
            user = userMapper.getUserForName(username);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
        return user;
    }

    public UserDetails getUserDetails(Integer uid) throws SysException {
        UserDetails userDetails;
        try {
            userDetails = userDetailsMapper.get(uid);
        } catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
        return userDetails;
    }
}
