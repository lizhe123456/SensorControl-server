package com.zlcm.server.service;

import com.zlcm.server.model.record.SendRecord;
import java.util.List;

public interface SendRecordService {

    List<SendRecord> dateUserSendRecord(String startTime, String endTime);

    List<SendRecord> dateDeviceSendRecord(String startTime, String endTime);

    List<SendRecord> selectUserRecord(String uid);

    List<SendRecord> selectDeviceRecord(String did);

}
