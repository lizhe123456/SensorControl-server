package com.zlcm.server.dao;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.apprep.AppOrder;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order>{

    List<Order> getUidForOrder(@Param("uid") Integer uid, @Param("page")Integer page, @Param("size") Integer size);

    List<Device> getDeviceWhereAid(@Param("aid") Integer aid);
}