package com.zlcm.server.service;


import com.zlcm.server.base.BaseService;
import com.zlcm.server.model.apprep.AppDevice;
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


    List<AppDevice> findPeriphery(double longitude, double latitude, double range, int size);

    Device getDeviceFormMac(String mac);

    int getHouseholdNum(Integer did);

    List<AppDevice> findDevicesList(List<Integer> devices,String province,String city,String area,int size,int page);
}
