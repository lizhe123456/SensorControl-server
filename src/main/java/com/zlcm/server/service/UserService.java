package com.zlcm.server.service;

public interface UserService {

    int getLogin(String username, String pass);

    int register(String username, String pass);

    int updatePass(String username,String oldPass,String newPass);

    int phoneLogin(String phone,String code);

}
