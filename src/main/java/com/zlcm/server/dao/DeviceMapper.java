package com.zlcm.server.dao;


import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.bean.Device;

import java.util.List;
import java.util.Map;

public interface DeviceMapper extends BaseMapper<Device>{

    List<Device> peripheryDevices(Map<String , Object> map);

    Device getDeviceFormMac(String mac);
}