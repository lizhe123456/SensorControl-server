package com.zlcm.server.dao;

import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.user.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeviceDao {

    List<Device> findDevices(Map<String, Object> map);

    void insertDevices(Device device);

    void insertDevice(String did);

    void upDateDevice(Device device);
}
