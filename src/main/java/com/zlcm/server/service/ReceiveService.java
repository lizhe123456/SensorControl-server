package com.zlcm.server.service;

public interface ReceiveService {

    void apiForDevice();

    void receiveAT(String AT);

    void receiveImage(byte[] img);

    void receiveFile(byte[] file);

    void reportLocation(double longitude,double latitude);

    void reportDeviceState(String state);

    void reportDeviceIp(String ip);

    void reportDeviceInfo(String info);
}
