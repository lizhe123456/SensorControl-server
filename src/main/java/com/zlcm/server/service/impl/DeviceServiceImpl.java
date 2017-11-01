package com.zlcm.server.service.impl;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.DeviceBindDao;
import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.device.DeviceInfoPage;
import com.zlcm.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设配服务
 */

public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceDao deviceDao;

    @Resource
    DeviceBindDao deviceBindDao;

    @Override
    public int addDevice() {

        return 0;
    }

    @Override
    public int deleteDevice(String mac) {
        return 0;
    }

    @Override
    public int bingDevice(String uid, String did) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("did",did);
        if (deviceBindDao.isBind(map) != 1){
            return Constant.DEVICE_BIND;
        }else {
            try {
                deviceBindDao.bindDevice(map);
                return Constant.DEVICE_BIND_SUCCESS;
            }catch (Exception e){
                e.printStackTrace();
                return Constant.DEVICE_BIND_ERROR;
            }
        }
    }

    @Override
    public int unBindDevice(String uid, String did) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("did",did);
        if (deviceBindDao.isBind(map) != 1){
            return Constant.DEVICE_BIND;
        }else {
            try {
                deviceBindDao.usBindDevice(map);
                return Constant.DEVICE_UNBIND_SUCCESS;
            }catch (Exception e){
                e.printStackTrace();
                return Constant.DEVICE_UNBIND_ERROR;
            }
        }
    }

    @Override
    public List<Device> findDevices(String uid) {
        return deviceDao.findDevices(uid);
    }


}
