//package com.zlcm.server.service.impl;
//
//import com.zlcm.server.dao.user.*;
//import com.zlcm.server.model.user.UcenterUser;
//import com.zlcm.server.model.user.UcenterUserDetails;
//import com.zlcm.server.model.user.UcenterUserLog;
//import com.zlcm.server.model.user.UcenterUserOauth;
//import com.zlcm.server.service.UcenterApiService;
//import com.zlcm.server.util.GenerationLogUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Transactional
//@Service
//public class UcenterApiServiceImpl implements UcenterApiService {
//
//    @Autowired
//    UcenterUserDao ucenterUserDao;
//    @Autowired
//    UcenterUserDetailsDao ucenterUserDetailsDao;
//    @Autowired
//    UcenterUserOauthDao ucenterUserOauthDao;
//    @Autowired
//    UcenterUserLogDao ucenterUserLogDao;
//    @Autowired
//    UcenterOauthDao ucenterOauthDao;
//
//    @Override
//    public void insertUser(UcenterUser ucenterUser,String agent,String ip,String content) {
//        ucenterUserDao.save(ucenterUser);
//        UcenterUserDetails ucenterUserDetails = new UcenterUserDetails();
//        ucenterUserDetails.setUser_id(ucenterUser.getUser_id());
//        ucenterUserDetails.setPhone(ucenterUser.getUsername());
//        ucenterUserDetails.setNickname(ucenterUser.getUsername());
//        ucenterUserDetails.setAvatar("http://"+ip+":8080/"+"img/avatar.jpg");
//        ucenterUserDetailsDao.save(ucenterUserDetails);
//        saveLog(ucenterUser.getUser_id(), agent,ip,content);
//    }
//
//    @Override
//    public void login(UcenterUser ucenterUser, String agent, String ip, String content) {
//        ucenterUser.setLast_login_ip(ip);
//        ucenterUser.setState(1);
//        ucenterUserDao.update(ucenterUser);
//        saveLog(ucenterUser.getUser_id(), agent,ip,content);
//    }
//
//    private void saveLog(Integer uid,String agent,String ip,String content){
//        UcenterUserLog ucenterUserLog = GenerationLogUtils.generationUcenterUserLog(uid,
//                ip,agent,content);
//        ucenterUserLogDao.save(ucenterUserLog);
//    }
//}
