package com.zlcm.server.time;


import com.zlcm.server.dao.LogMapper;
import com.zlcm.server.dao.ReqMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Req;
import com.zlcm.server.service.AdvertService;
import com.zlcm.server.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public class Test extends TestService{

    @Autowired
    LogMapper logMapper;
    @Autowired
    ReqMapper reqMapper;

    @Autowired
    AdvertService advertService;

    @org.junit.Test
    @Transactional
    @Rollback(false)
    public void getList(){
        Date date = new Date();
        String start = DateUtil.formatDate(date) + " 00:00:00";
        String end = DateUtil.formatDateTime(date);
        Integer num = logMapper.findFormDate(start,end);
        System.out.println(num);
        String time = DateUtil.formatDate(date);
        reqMapper.save(new Req(time,num));
    }



    @org.junit.Test
    @Transactional
    @Rollback(false)
    public void getAdvert(){
        try {
            List<Advert> list = advertService.findAll();
            if (list ==  null){
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                Advert advert = list.get(i);
                long duration = advert.getDuration();
                Date start = advert.getStartTime();
                if (start != null && duration < DateUtil.pastS(start)){
                    //过期
                    advert.setState((byte) 3);
                    advertService.update(advert);
                }
            }
        } catch (SysException e) {
            e.printStackTrace();
        }
    }


}
