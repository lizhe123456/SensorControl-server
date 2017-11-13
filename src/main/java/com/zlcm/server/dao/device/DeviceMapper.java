package com.zlcm.server.dao.device;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.user.UcenterUser;

import java.util.List;
import java.util.Map;

public interface DeviceMapper extends BaseMapper<Device>{

    /**
     * 查询周边
     */
    List<Device> peripheryDevices(Map<String, Object> map);

    /**
     * 查询该用户绑定设配
     */
    List<Device> findDevices(Integer uid);

    /**
     * 查询该设配绑定用户
     */
    List<UcenterUser> findUsers(String did);

    Device get(String pk);

    Device getDeviceFormMac(String mac);
}