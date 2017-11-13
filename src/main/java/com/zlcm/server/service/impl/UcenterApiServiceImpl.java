package com.zlcm.server.service.impl;

import com.zlcm.server.dao.user.*;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.model.user.UcenterUserDetails;
import com.zlcm.server.model.user.UcenterUserLog;
import com.zlcm.server.model.user.UcenterUserOauth;
import com.zlcm.server.service.UcenterApiService;
import com.zlcm.server.util.GenerationLogUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UcenterApiServiceImpl implements UcenterApiService {

    @Autowired
    UcenterUserDao ucenterUserDao;
    @Autowired
    UcenterUserDetailsDao ucenterUserDetailsDao;
    @Autowired
    UcenterUserOauthDao ucenterUserOauthDao;
    @Autowired
    UcenterUserLogDao ucenterUserLogDao;
    @Autowired
    UcenterOauthDao ucenterOauthDao;

    @Override
    public void insertUser(UcenterUser ucenterUser,String agent,String ip,String content) {
        ucenterUserDao.save(ucenterUser);
        UcenterUserDetails ucenterUserDetails = new UcenterUserDetails();
        ucenterUserDetails.setUser_id(ucenterUser.getUser_id());
        ucenterUserDetails.setPhone(ucenterUser.getUsername());
        ucenterUserDetailsDao.save(ucenterUserDetails);
        UcenterUserLog ucenterUserLog = GenerationLogUtils.generationUcenterUserLog(ucenterUser.getUser_id(),
                agent,ip,content);
        ucenterUserLogDao.save(ucenterUserLog);
    }


}
