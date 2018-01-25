package com.zlcm.server.dao;


import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.admin.AdminDevice;
import com.zlcm.server.model.apprep.AppDevice;
import com.zlcm.server.model.bean.Device;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DeviceMapper extends BaseMapper<Device>{

    List<AppDevice> peripheryDevices(Map<String , Object> map);

    List<AppDevice> peripheryDevicesImg(Map<String , Object> map);

    Device getDeviceFormMac(String mac);

    int findHouseholdNum(@Param("did") Integer did);

    List<Device> findDevices(List<Integer> dids);

    List<AppDevice> findDevicesList(Map<String,Object> map);

    List<AdminDevice> findAdminDevicesList(@Param("page") int page, @Param("size") int size);

}