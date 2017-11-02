package com.zlcm.server.service;

import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.record.SendRecord;
import com.zlcm.server.model.user.UserInfo;

import java.util.List;
import java.util.Map;

public interface SendRecordService {

    List<SendRecord> dateUserSendRecord(String startTime, String endTime);

    List<SendRecord> dateDeviceSendRecord(String startTime, String endTime);

    List<SendRecord> selectUserRecord(String uid);

    List<SendRecord> selectDeviceRecord(String did);

    int insertRecord(UserInfo userInfo, Device device, String dataPath, String sendType);

    int deleteRecord(int id);

    List<SendRecord> selectAllRecord(String page,String size);
}
