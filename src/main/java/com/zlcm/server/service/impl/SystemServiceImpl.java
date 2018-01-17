package com.zlcm.server.service.impl;

import com.zlcm.server.communication.MinaClient;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.AdvertDeviceMapper;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.dao.OrderMapper;
import com.zlcm.server.dao.UserMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.admin.AdminAdvert;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.netty.NettyRunnable;
import com.zlcm.server.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    AdvertMapper advertMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AdvertDeviceMapper advertDeviceMapper;
    @Autowired
    UserMapper userMapper;

    private static final Logger _log = LoggerFactory.getLogger(SystemServiceImpl.class);

    @Override
    public void auditing(int aid,int state,String auditingInfo) {
        Advert advert = advertMapper.get(aid);
        if (advert != null){
            //0待审，1通过，2未通过
            if (state == 1){
                List<Device> list = orderMapper.getDeviceWhereAid(aid);
                new NettyRunnable().send(advertDeviceMapper,advertMapper,list,advert);
            }else if (state == 2){
                advert.setState((byte) state);
                advert.setAuditingInfo(auditingInfo);
                advertMapper.update(advert);
            }

        }
    }

    @Override
    public List<AdminAdvert> auditingInfo(int page, int size) throws SysException {
        try {
            return advertMapper.findAdminAdvert(page*size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<User> userList(int page,int size) throws SysException {
        try {
            return userMapper.getPageList(page*size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }
}
