package com.zlcm.server.service;

import com.zlcm.server.base.BaseService;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.user.UcenterUser;

import java.util.List;

public interface DeviceService extends BaseService<Device>{

    /**
     * 查询绑定用户
     */
    List<UcenterUser> findUsers(String did);

    /**
     *
     */
    List<Device> findDevices(Integer uid);

    /**
     * 上报数据
     */
    void report(Device device);

    /**
     * 绑定
     */
    void bind(Integer uid,String did);
    /**
     * 解绑
     */
    void unbind(Integer uid,String did);


    List<Device> findPeriphery(double longitude, double latitude, double range);

    Device get(String pk);

    Device getDeviceFormMac(String mac);

}
