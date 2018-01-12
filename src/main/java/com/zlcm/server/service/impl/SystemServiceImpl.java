package com.zlcm.server.service.impl;

import com.zlcm.server.communication.MinaClient;
import com.zlcm.server.dao.AdvertDeviceMapper;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.dao.OrderMapper;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.service.SystemService;
import com.zlcm.server.util.BmpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    AdvertMapper advertMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    AdvertDeviceMapper advertDeviceMapper;


    @Override
    public void auditing(int aid,int state,String auditingInfo) {
        Advert advert = advertMapper.get(aid);
        if (advert != null){
            //0待审，1通过，2未通过
            advert.setState((byte) state);
            advert.setAuditingInfo(auditingInfo);
            if (state == 1){
                List<Device> list = orderMapper.getDeviceWhereAid(aid);
                new MinaClient().send(advertDeviceMapper,advertMapper,list,advert,BmpUtil.getImagePixel(advert.getAdvertImg()));
            }
            advertMapper.update(advert);
        }
    }

    @Override
    public void auditingInfo(int state, int page, int size) {
        List<Advert> list = advertMapper.findAdvertWhereState(state,page*size,size);
    }
}
