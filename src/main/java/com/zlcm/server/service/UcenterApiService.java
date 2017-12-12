package com.zlcm.server.service;

import com.zlcm.server.model.user.UcenterUser;

public interface UcenterApiService {

    /**
     * 添加用户
     */
    void insertUser(UcenterUser ucenterUser, String agent, String ip, String content);

    /**
     * 登录
     */
    void login(UcenterUser ucenterUser,String agent, String ip, String content);

}
