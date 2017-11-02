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

    List<Device> findUsers(@Param(value = "did") String did);

    Device findDevice(@Param(value = "did") String did);

    void insertDevices(Device device);

    void upDateDevice(Device device);

    void deleteDevices(@Param(value = "did") String did);

    List<Device> pagingDevices(Map<String, Object> map);

    List<Device> peripheryDevices(Map<String,Object> map);
}
