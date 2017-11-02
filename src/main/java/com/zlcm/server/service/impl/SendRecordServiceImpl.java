package com.zlcm.server.service.impl;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.SendRecordDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.record.SendRecord;
import com.zlcm.server.model.user.UserInfo;
import com.zlcm.server.service.SendRecordService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.xml.registry.infomodel.User;
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

    @Override
    public int insertRecord(UserInfo userInfo, Device device,String dataPath, String sendType) {
        SendRecord sendRecord = new SendRecord();
        sendRecord.setSendId(userInfo.getUid());
        sendRecord.setSendName(userInfo.getName());
        sendRecord.setReceiveId(device.getDid());
        sendRecord.setSendId(device.getDid());
        sendRecord.setSendName(device.getName());
        sendRecord.setInfoType(sendType);
        sendRecord.setInfo(dataPath);
        sendRecordDao.insertRecord(sendRecord);
        return Constant.RECORD_INSERT_SUCCESS;
    }

    @Override
    public int deleteRecord(int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        sendRecordDao.deleteRecord(map);
        return Constant.RECORD_DELETE_SUCCESS;
    }

    @Override
    public List<SendRecord> selectAllRecord(String page,String size) {
        Map<String,Object> map = new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        return sendRecordDao.selectAllRecord(map);
    }

}
