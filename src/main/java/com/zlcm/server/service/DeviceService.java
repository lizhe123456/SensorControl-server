package com.zlcm.server.service;

import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.device.DeviceInfoPage;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    void addDevice();

    void deleteDevice(int mac);

    void bingDevice(int uid,int did);

    void unBindDevice(int uid,int did);

    List<Device> getListByPage(Map<String,Object> map);



}
