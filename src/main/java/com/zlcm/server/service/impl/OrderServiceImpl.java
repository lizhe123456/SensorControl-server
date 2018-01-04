package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.dao.DeviceMapper;
import com.zlcm.server.dao.OrderDeviceMapper;
import com.zlcm.server.dao.OrderMapper;
import com.zlcm.server.model.apprep.AppOrder;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.service.OrderService;
import com.zlcm.server.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * author:lizhe
 * date: 2017-12-19
 * for tomorrow
 * 类介绍:
 */
@Transactional
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order,OrderMapper> implements OrderService{

    @Autowired
    OrderDeviceMapper orderDeviceMapper;
    @Autowired
    AdvertMapper advertMapper;
    @Autowired
    DeviceMapper deviceMapper;

    /**
     * 生成订单信息
     * @param devices
     * @param advert
     * @return
     */
    @Override
    public AppOrder getChargingInfo(List<Integer> devices, Advert advert) {
        AppOrder appOrder = new AppOrder();
        int aid = advertMapper.save(advert);
        int day = Integer.parseInt(advert.getDuration()/1000/60/60/24 + "");
        int charging = 1 * day * 24 * devices.size();
        Date date = new Date();
        List<Device> list = deviceMapper.findDevices(devices);
        Order order = new Order();
        order.setAid(aid);
        order.setDuration(day + "天");
        order.setCreateTime(date);
        order.setPrice(new BigDecimal(charging));
        order.setUid(advert.getUid());
        dao.save(order);

        appOrder.setList(list);
        appOrder.setDuration(day);
        appOrder.setStartTime(DateUtil.getDate());
        return appOrder;
    }

    /**
     * 查询订单信息
     */

    /**
     * 支付成功改变订单状态并将广告状态改为待审核状态
     */


}
