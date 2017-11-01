package com.zlcm.server.service;

import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.device.DeviceInfoPage;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    int addDevice();

    int deleteDevice(String did);

    int bingDevice(String uid,String did);

    int unBindDevice(String uid,String did);

    List<Device> findDevices(String uid);



}
