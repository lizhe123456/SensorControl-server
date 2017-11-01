package com.zlcm.server.dao;

import com.zlcm.server.model.record.SendRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SendRecordDao {

    //添加记录
    void insertRecord(SendRecord sendRecord);

    //查询用户发送记录
    List<SendRecord> selectUserRecord(String uid);

    //查询设配接收记录
    List<SendRecord> selectDeviceRecord(String did);

    //查询某时间段用户发送记录
    List<SendRecord> selectDateUserRecord(Map<String,Object> map);

    //查询某时间段设配接收记录
    List<SendRecord> selectDateDeviceRecord(Map<String,Object> map);

    //删除设配
    void deleteRecord(Map<String,Object> map);

    //分页查询
    List<SendRecord> selectAllRecord(Map<String, Object> map);
}
