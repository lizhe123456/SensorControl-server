package com.zlcm.server.dao;


import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.OrderDevice;

import java.util.List;

public interface OrderDeviceMapper extends BaseMapper<OrderDevice>{

    List<Device> getDeviceInfo(Integer oid);
}