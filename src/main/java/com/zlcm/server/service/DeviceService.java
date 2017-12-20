package com.zlcm.server.service;


import com.zlcm.server.base.BaseService;
import com.zlcm.server.model.bean.Device;
import java.util.List;

public interface DeviceService extends BaseService<Device> {

    /**
     *
     */
    List<Device> findDevices(Integer uid);

    /**
     * 上报数据
     */
    void report(Device device);


    List<Device> findPeriphery(double longitude, double latitude, double range);

    Device get(String pk);

    Device getDeviceFormMac(String mac);

}
