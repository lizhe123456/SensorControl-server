package com.zlcm.server.service;

import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.device.DeviceInfoPage;

import java.util.List;
import java.util.Map;

public interface DeviceService {

    void addDevice(String dName,String dType);

    int deleteDevice(String did);

    int bingDevice(String uid,String did);

    int unBindDevice(String uid,String did);

    List<Device> findDevices(String uid);

    List<Device> pagingDevices(int page,int size);

    List<Device> peripheryDevices(double longitude,double latitude);

    void updateDevice(Device device);

    Device findDevice(String did);
}
