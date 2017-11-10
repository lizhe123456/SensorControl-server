package com.zlcm.server.util;

import com.zlcm.server.model.user.UcenterUserLog;

public class GenerationLogUtils {

    public static UcenterUserLog generationUcenterUserLog(Integer userId
            ,String ip,String agent,String content){
        UcenterUserLog ucenterUserLog = new UcenterUserLog();
        ucenterUserLog.setAgent(agent);
        ucenterUserLog.setUser_id(userId);
        ucenterUserLog.setContent(content);
        ucenterUserLog.setIp(ip);
        return ucenterUserLog;
    }
}
