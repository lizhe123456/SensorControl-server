package com.zlcm.server.service;

public interface SendService {

    int sendAt(String did,String AT);

    int sendImg(String did,String img);

    int sendFile(String did,String file);

}
