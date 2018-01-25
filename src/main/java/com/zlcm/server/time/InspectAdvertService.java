package com.zlcm.server.time;

import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.service.AdvertService;
import com.zlcm.server.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class InspectAdvertService {

    @Autowired
    AdvertService advertService;

    /**
     * 每晚4点更新广告状态
     */
    public void inspectAdvertService(){
        try {
            List<Advert> list = advertService.findAll();
            if (list ==  null){
                return;
            }
            for (int i = 0; i < list.size(); i++) {
                Advert advert = list.get(i);
                long duration = advert.getDuration();
                Date start = advert.getStartTime();
                Date date = new Date();
                if (start != null && duration <= DateUtil.pastS(date) - DateUtil.pastS(start)){
                    //过期
                    advert.setState((byte) 3);
                    advertService.update(advert);
                }
            }
        } catch (SysException e) {
            e.printStackTrace();
        }
    }

    /**
     * 清理头像
     */


}
