package com.zlcm.server.service;

import com.zlcm.server.model.user.UserUcenter;

public interface UserService {

    UserUcenter getLogin(String username, String pass);

    int register(String username, String pass);

    int updatePass(String username,String oldPass,String newPass);

    int phoneLogin(String phone,String code);

}
