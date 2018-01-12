package com.zlcm.server.service;

public interface SystemService {

    void auditing(int aid,int state,String auditingInfo);

    void auditingInfo(int state,int page,int size);

}
