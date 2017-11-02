package com.zlcm.server.service;

import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ReceiveService {

    void apiForDevice();

    void receiveAT(String at);

    void receiveFile(byte[] img);

    String receiveImg(MultipartFile file, HttpServletRequest request, ModelMap model);

    void reportLocation(String longitude,String latitude);

    void reportDeviceState(String state);

    void reportDeviceIp(String ip);

    void reportDeviceInfo(String info);

}
