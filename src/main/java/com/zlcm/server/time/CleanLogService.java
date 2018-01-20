package com.zlcm.server.time;

import com.zlcm.server.dao.LogMapper;
import com.zlcm.server.dao.ReqMapper;
import com.zlcm.server.model.bean.Req;
import com.zlcm.server.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 每月月底清理当月日志
 */
public class CleanLogService {

    @Autowired
    LogMapper logMapper;
    @Autowired
    ReqMapper reqMapper;

    /**
     * 每月日志清理
     */
    public void cleanLog(){
        logMapper.deleteUid();
    }

    /**
     * 统计每日请求量
     */
    public void DayR(){
        Date date = new Date();
        String start = DateUtil.formatDate(date) + " 00:00:00";
        String end = DateUtil.formatDateTime(date);
        Integer num = logMapper.findFormDate(start,end);
        String time = DateUtil.formatDate(date);
        reqMapper.save(new Req(time,num));
    }

}
