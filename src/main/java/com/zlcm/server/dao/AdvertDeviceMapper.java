package com.zlcm.server.dao;


import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.bean.AdvertDevice;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvertDeviceMapper extends BaseMapper<AdvertDevice> {
    List<Integer> getDeviceWhereAid(@Param("aid") Integer aid);
}