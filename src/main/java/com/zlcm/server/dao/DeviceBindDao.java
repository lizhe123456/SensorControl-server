package com.zlcm.server.dao;

import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface DeviceBindDao {

    int isBind(Map<String, Object> map);

    void bindDevice(Map<String, Object> map);

    int usBindDevice(Map<String, Object> map);

}
