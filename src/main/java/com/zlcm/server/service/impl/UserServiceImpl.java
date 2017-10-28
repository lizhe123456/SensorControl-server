package com.zlcm.server.service.impl;

import com.zlcm.server.dao.UserDao;
import com.zlcm.server.model.user.UserInfo;
import com.zlcm.server.model.user.UserUcenter;
import com.zlcm.server.service.UserService;
import com.zlcm.server.util.id.UUIDTools;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService{


    public static final int SCS_ISREGISTER = 101;
    public static final int SCS_SUCCESS = 200;
    public static final int SCS_USER_ERROR = 102;
    public static final int SCS_SQLERROR = 103;
    @Resource
    UserDao userDao;

    /**
     * 登录
     * @param username
     * @param pass
     * @return
     */
    @Override
    public int getLogin(String username,String pass) {
        if (isRegister(username)){
            //已注册
            int num = userDao.selectUser(username,pass);
            if (num != 0){
                return SCS_SUCCESS;
            }else {
                return SCS_USER_ERROR;
            }
        }else {
            //未注册
            return SCS_ISREGISTER;
        }
    }

    /**
     * 是否注册
     * @param username
     * @return
     */
    public boolean isRegister(String username) {
        int userUcenter = userDao.selectUserFormName(username);
        if (userUcenter != 0){
            return true;
        }else {
            return false;
        }
    }



    /**
     * 注册账号
     * @param username
     * @param pass
     * @return
     */
    @Override
    public int register(String username, String pass) {
        if (!isRegister(username)) {
            String uid = getUid();
            UserUcenter userUcenter = new UserUcenter();
            userUcenter.setUsername(username);
            userUcenter.setPassword(pass);
            userUcenter.setUid(uid);
            UserInfo userInfo = new UserInfo();
            userInfo.setUid(uid);
            userDao.insertUserUcenter(userUcenter);
            userDao.insertUserInfo(userInfo);
            return SCS_SUCCESS;
        }else {
            return SCS_ISREGISTER;
        }
    }

    private String getUid(){
        String uid = UUIDTools.uuid();
        if (userDao.selectUid(uid) != 0){
            getUid();
        }
        return uid;
    }

    /**
     * 修改密码
     * @param username
     * @param oldPass
     * @param newPass
     * @return
     */
    @Override
    public int updatePass(String username, String oldPass, String newPass) {

        return 0;
    }

    /**
     * 手机号登录
     * @param phone
     * @param code
     * @return
     */
    @Override
    public int phoneLogin(String phone, String code) {
        return 0;
    }


}
