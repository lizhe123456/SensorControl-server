package com.zlcm.server.dao;

import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.user.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DeviceDao {

    List<Device> findDevices(@Param(value = "uid") String uid);

    void insertDevices(Device device);

    void insertDevice(String did);

    void upDateDevice(Device device);
}
