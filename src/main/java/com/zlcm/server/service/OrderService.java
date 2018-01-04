package com.zlcm.server.service;

import com.zlcm.server.base.BaseService;
import com.zlcm.server.model.apprep.AppOrder;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Order;

import java.util.List;

/**
 * author:lizhe
 * date: 2017-12-19
 * for tomorrow
 * 类介绍:
 */
public interface OrderService extends BaseService<Order> {

    AppOrder getChargingInfo(List<Integer> devices, Advert advert);
}
