package com.zlcm.server.dao.device;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.device.UserBindDevice;

import java.util.Map;

public interface UserBindDeviceMapper extends BaseMapper<UserBindDevice>{

    void unbind(Map<String, Object> map);
}