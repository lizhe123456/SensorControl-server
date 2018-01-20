package com.zlcm.server.dao;

import com.zlcm.server.base.BaseMapper;
import com.zlcm.server.model.bean.Log;
import org.apache.ibatis.annotations.Param;

public interface LogMapper extends BaseMapper<Log>{

    void deleteUid();

    Integer findFormDate(@Param("start") String start,@Param("end") String end);

}