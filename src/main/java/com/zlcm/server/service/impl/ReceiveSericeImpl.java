package com.zlcm.server.service.impl;

import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.ReceiveService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class ReceiveSericeImpl implements ReceiveService{

    @Resource
    DeviceDao deviceDao;

    @Override
    public void apiForDevice() {

    }

    @Override
    public void receiveAT(String AT) {

    }

    @Override
    public void receiveImage(byte[] img) {

    }

    @Override
    public void receiveFile(byte[] file) {

    }

    @Override
    public void reportLocation(String longitude, String latitude) {
        Device device = new Device();
        device.setDlongitude(new BigDecimal(longitude));
        device.setDlatitude(new BigDecimal(latitude));
        deviceDao.upDateDevice(device);
    }

    @Override
    public void reportDeviceState(String state) {
        Device device = new Device();
        device.setDstate(state);
        deviceDao.upDateDevice(device);
    }

    @Override
    public void reportDeviceIp(String ip) {
        Device device = new Device();
        device.setDip(ip);
        deviceDao.upDateDevice(device);
    }

    @Override
    public void reportDeviceInfo(String info) {
        Device device = new Device();
        device.setDinfo(info);
        deviceDao.upDateDevice(device);
    }

}
