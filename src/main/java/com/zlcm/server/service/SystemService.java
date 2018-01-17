package com.zlcm.server.service;

import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.admin.AdminAdvert;
import com.zlcm.server.model.bean.User;

import java.util.List;

public interface SystemService {

    void auditing(int aid,int state,String auditingInfo);

    List<AdminAdvert> auditingInfo(int page, int size) throws SysException;

    List<User> userList(int page, int size) throws SysException;



}
