package com.zlcm.server.service.impl;

import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.device.DeviceInfoPage;
import com.zlcm.server.service.DeviceService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 设配服务
 */

public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceDao deviceDao;

    @Override
    public void addDevice() {
        
    }

    @Override
    public void deleteDevice(int mac) {

    }

    @Override
    public void bingDevice(int uid, int did) {

    }

    @Override
    public void unBindDevice(int uid, int did) {

    }

    @Override
    public List<Device> getListByPage(Map<String, Object> map) {
        return null;
    }

}
