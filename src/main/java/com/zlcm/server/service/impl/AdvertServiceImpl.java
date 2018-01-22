package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.dao.DeviceMapper;
import com.zlcm.server.dao.OrderDeviceMapper;
import com.zlcm.server.dao.OrderMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.apprep.AppAdvert;
import com.zlcm.server.model.apprep.AppOrder;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.model.bean.OrderDevice;
import com.zlcm.server.service.AdvertService;
import com.zlcm.server.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AdvertServiceImpl extends BaseServiceImpl<Advert,AdvertMapper> implements AdvertService {

    private final static Logger _log = LoggerFactory.getLogger(AdvertServiceImpl.class);

    @Autowired
    OrderDeviceMapper orderDeviceMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    DeviceMapper deviceMapper;
    @Autowired
    AdvertMapper advertMapper;

    /**
     * 分页获取人文
     * @param page
     * @param size
     * @return
     */
    @Override
    public List<AppAdvert> getAppHotList(Integer page, Integer size) throws SysException {
        try {
            return dao.getAppHotList(page * size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<AppAdvert> getAppRecommend(Integer page, Integer size) throws SysException {
        try {
            return dao.getAppRecommend(page * size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<String> findAdvertFordid(Integer did) throws SysException{
        try{
            return dao.findAdvertFordid(did);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    @Override
    public List<AppAdvert> findAdvertForDid(Integer did, Integer page, Integer size) throws SysException {
        try{
            return dao.findAdvertForDid(did,page * size,size);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.SELECE_ERROR);
        }
    }

    /**
     * 接受app广告并生成订单
     * @param advert
     * @param devices
     */
    @Override
    public AppOrder insertAdvert(Advert advert, List<Integer> devices) throws SysException {
        AppOrder appOrder = new AppOrder();
        try {
            float price = 1 * 24 * devices.size();
            dao.save(advert);
            Order order = new Order();
            order.setUid(advert.getUid());
            order.setAid(advert.getAid());
            order.setPrice(price);
            order.setDuration(DateUtil.getDayNum(advert.getDuration()));
            orderMapper.save(order);
            String time = DateUtil.formatDateTime(orderMapper.get(order.getOid()).getCreateTime());
            for (int i = 0; i < devices.size(); i++) {
                OrderDevice orderDevice = new OrderDevice();
                orderDevice.setDid(devices.get(i));
                orderDevice.setOid(order.getOid());
                orderDeviceMapper.save(orderDevice);
            }
            List<Device> deviceList = deviceMapper.findDevices(devices);
            appOrder.setList(deviceList);
            appOrder.setStartTime(time);
            appOrder.setDuration(DateUtil.getDayNum(advert.getDuration()));
            appOrder.setOrderState(0);
            appOrder.setOrder_number(order.getOid());
            appOrder.setAdvertState(0);
            appOrder.setPrice(price);
        }catch (DataAccessException e){
            _log.error(e.getMessage());
            throw new SysException(Constant.ADD_ERROR);
        }
        return appOrder;
    }


    /**
     * 查询待审广告
     */


    /**
     * 审核广告并发送到设配
     */

    @Override
    public Integer findReleaseNum(Integer uid) {
        return advertMapper.findReleaseNum(uid);
    }

    @Override
    public Integer findAuditingNum(Integer uid) {
        return advertMapper.findAuditingNum(uid);
    }

}
