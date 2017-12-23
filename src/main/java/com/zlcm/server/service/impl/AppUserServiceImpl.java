package com.zlcm.server.service.impl;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.IdentityInfoMapper;
import com.zlcm.server.dao.UserDetailsMapper;
import com.zlcm.server.dao.UserMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.Sms;
import com.zlcm.server.model.bean.IdentityInfo;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.model.bean.UserDetails;
import com.zlcm.server.model.bean.Wallet;
import com.zlcm.server.service.AppUserService;
import com.zlcm.server.util.IPUtils;
import com.zlcm.server.util.JackJsonUtils;
import com.zlcm.server.util.StringReplaceUtil;
import com.zlcm.server.util.sms.SmsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
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
    public void nameAuthen(Integer uid , String name, String idCrad,int sex,Date birthday,
                           @NotNull  IdentityInfo info) throws SysException {
        UserDetails userDetails = getUserDetails(uid);
        if (userDetails != null){
            IdentityInfo identityInfo = identityInfoMapper.get(uid);
            if (identityInfo == null){
                try {
                    identityInfoMapper.save(info);
                }catch (DataAccessException e) {
                    _log.error(e.getMessage());
                    throw new SysException(Constant.ADD_ERROR);
                }
                userDetails.setRealName(name);
                userDetails.setIdcrad(idCrad);
                userDetails.setSex((byte)(sex));
                userDetails.setBirthday(birthday);
                try {
                    userDetailsMapper.update(userDetails);
                }catch (DataAccessException e){
                    _log.error(e.getMessage());
                    throw new SysException(Constant.UPDATE_ERROR);
                }
            }else {
                //TODO:已认证
            }
        }else {
            //TODO:用户信息为空
        }
    }

    @Override
    public void storeAuthen(Integer uid,String organization,String address,String legalPerson,String iphone,String businessLicenseUrl) {

    }

    @Override
    public void updatePhone(Integer uid,String phone) {
        UserDetails userDetails = userDetailsMapper.get(uid);
        if (userDetails != null){
            userDetails.setPhone(phone);
            userDetailsMapper.update(userDetails);
        }else {
            //TODO:用户信息为空
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
