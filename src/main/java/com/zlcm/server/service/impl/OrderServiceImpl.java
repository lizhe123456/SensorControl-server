package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.OrderMapper;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * author:lizhe
 * date: 2017-12-19
 * for tomorrow
 * 类介绍:
 */
@Transactional
@Service
public class OrderServiceImpl extends BaseServiceImpl<Order,OrderMapper> implements OrderService{
}
