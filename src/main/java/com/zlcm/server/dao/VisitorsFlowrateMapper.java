package com.zlcm.server.dao;


import com.zlcm.server.model.bean.VisitorsFlowrate;

public interface VisitorsFlowrateMapper {
    int insert(VisitorsFlowrate record);

    int insertSelective(VisitorsFlowrate record);
}