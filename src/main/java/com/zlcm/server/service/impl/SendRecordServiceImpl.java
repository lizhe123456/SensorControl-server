package com.zlcm.server.service.impl;

import com.zlcm.server.dao.SendRecordDao;
import com.zlcm.server.model.record.SendRecord;
import com.zlcm.server.service.SendRecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SendRecordServiceImpl implements SendRecordService{

    @Resource
    SendRecordDao sendRecordDao;


    @Override
    public List<SendRecord> dateUserSendRecord(String startTime, String endTime) {
        Map<String,Object> map = new HashMap<>();
        map.put("beginDate",startTime);
        map.put("endDate",endTime);
        List<SendRecord> list = sendRecordDao.selectDateUserRecord(map);
        return list;
    }

    @Override
    public List<SendRecord> dateDeviceSendRecord(String startTime, String endTime) {
        Map<String,Object> map = new HashMap<>();
        map.put("beginDate",startTime);
        map.put("endDate",endTime);
        List<SendRecord> list = sendRecordDao.selectDateDeviceRecord(map);
        return list;
    }

    @Override
    public List<SendRecord> selectUserRecord(String uid) {
        List<SendRecord> list = sendRecordDao.selectUserRecord(uid);
        return list;
    }

    @Override
    public List<SendRecord> selectDeviceRecord(String did) {
        List<SendRecord> list = sendRecordDao.selectUserRecord(did);
        return list;
    }

}
