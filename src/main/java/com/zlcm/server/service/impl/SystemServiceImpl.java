package com.zlcm.server.service.impl;

import com.zlcm.server.communication.MinaClient;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.*;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.admin.AdminAdvert;
import com.zlcm.server.model.admin.AdminDevice;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
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
    @Autowired
    DeviceMapper deviceMapper;

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

    @Override
    public List<AdminDevice> deviceList(int page, int size) throws SysException {
        try {
            List<Device> devices = deviceMapper.getPageList(page * size,size);
            List<AdminDevice> list = new ArrayList<>();
            if (devices != null){
                for (int i = 0; i < devices.size(); i++) {
                    int num = advertDeviceMapper.getAdvertNumWhereDid(devices.get(i).getDid());
                    AdminDevice adminDevice = new AdminDevice();
                    adminDevice.setAdvertNum(num);
                    adminDevice.setAddress(devices.get(i).getAddress());
                    adminDevice.setArea(devices.get(i).getArea());
                    adminDevice.setCity(devices.get(i).getCity());
                    adminDevice.setDesc(devices.get(i).getDesc());
                    adminDevice.setDid(devices.get(i).getDid());
                    adminDevice.setDlatitude(devices.get(i).getDlatitude());
                    adminDevice.setDlongitude(devices.get(i).getDlongitude());
                    adminDevice.setHousehold(devices.get(i).getHousehold());
                    adminDevice.setIp(devices.get(i).getIp());
                    adminDevice.setMac(devices.get(i).getMac());
                    adminDevice.setProvince(devices.get(i).getProvince());
                    adminDevice.setHousehold(devices.get(i).getHousehold());
                    adminDevice.setState(devices.get(i).getState());
                    list.add(adminDevice);
                }
                return list;
            }else {
                throw new SysException(Constant.SELECE_ERROR);
            }
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }
}
