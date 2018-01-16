package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.*;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.apprep.AppOrder;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.Income;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.service.OrderService;
import com.zlcm.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    private static Logger _log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    OrderDeviceMapper orderDeviceMapper;
    @Autowired
    AdvertMapper advertMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    IncomeMapper incomeMapper;
    @Autowired
    ExpenditureMapper expenditureMapper;

    /**
     * 生成订单信息
     * @param devices
     * @param advert
     * @return
     */
    @Override
    public AppOrder getChargingInfo(List<Integer> devices, Advert advert) throws SysException {
        AppOrder appOrder = new AppOrder();
        try {
            int aid = advertMapper.save(advert);
            int day = Integer.parseInt(advert.getDuration()/1000/60/60/24 + "");
            int charging = 1 * day * 24 * devices.size();
            Date date = new Date();
            List<Device> list = deviceMapper.findDevices(devices);
            Order order = new Order();
            order.setAid(aid);
            order.setDuration(day + "天");
            order.setCreateTime(date);
            order.setPrice(charging);
            order.setUid(advert.getUid());
            dao.save(order);

            appOrder.setList(list);
            appOrder.setDuration(day+"");
            appOrder.setStartTime(DateUtil.getDate());
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
        return appOrder;
    }

    /**
     * 查询订单信息
     */
    @Override
    public List<AppOrder> getOrderList(Integer uid,int page,int size) throws SysException {
        List<AppOrder> appOrderList = new ArrayList<>();
        try {
            List<Order> list = dao.getUidForOrder(uid,page*size,size);
            if (list == null || list.size() == 0){
                return appOrderList;
            }
            for (int i = 0; i < list.size(); i++) {
                Order order = list.get(i);
                List<Device> devices = orderDeviceMapper.getDeviceInfo(order.getOid());
                AppOrder appOrder = new AppOrder();
                Advert advert = advertMapper.get(order.getAid());
                appOrder.setDuration(order.getDuration());
                appOrder.setOrder_number(order.getOid());
                appOrder.setAdvertState(advert.getState());
                appOrder.setPrice(order.getPrice());
                appOrder.setAdvert(advert.getAdvertImg());
                appOrder.setDesc(advert.getTextInfo());
                appOrder.setOrderState(order.getState());
                appOrder.setStartTime(DateUtil.formatDate(order.getCreateTime(),"yyyy年MM月dd"));
                appOrder.setList(devices);
                appOrderList.add(appOrder);
            }
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }catch (Exception e){
            e.printStackTrace();
        }
        return appOrderList;
    }




    /**
     * 支付成功改变订单状态并将广告状态改为待审核状态
     */
    @Override
    public void payOrder(Integer oid, String amount, String trade_no, String passback_params,int type) {
        Order order = orderMapper.get(oid);
        order.setState((byte) 1);
        orderMapper.update(order);
        Income income = new Income();
        income.setOid(oid);
        income.setTime(new Date());
        income.setPrice(BigDecimal.valueOf(Double.parseDouble(amount)));
        income.setPassbackParams(passback_params);
        income.setType((byte) type);
        income.setTradeNo(trade_no);
        incomeMapper.save(income);
    }

    /**
     * 退款
     * @param uid
     * @param oid
     */
    @Override
    public void refund(Integer uid, Integer oid) {

    }

    @Override
    public List<String> aidfindIP(Integer aid) {
        return null;
    }

}
