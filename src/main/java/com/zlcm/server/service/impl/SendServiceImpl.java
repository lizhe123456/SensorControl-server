package com.zlcm.server.service.impl;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.SendService;
import com.zlcm.server.util.BmpUtil;
import com.zlcm.server.util.HexStrUtils;
import com.zlcm.server.util.SocketUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SendServiceImpl implements SendService{
    @Resource
    DeviceDao deviceDao;

    @Override
    public int sendAt(String did,String AT) {
        Device device = deviceDao.findDevice(did);
        if (device != null) {
            SocketUtil.getSocketUtil(device.getDid(), 10026).sendData(AT);
        }else if (device == null){
            return Constant.DEVICE_NOT_FOUND;
        }else {
            return Constant.DEVICE_STATE_EXCEPTION;
        }
        return 0;
    }

    @Override
    public int sendImg(String did,String img) {
        Device device = deviceDao.findDevice(did);
        byte[] rgb = BmpUtil.getImagePixel(img);
        String data = HexStrUtils.bytesToHexString(rgb);
        if (device != null) {
            SocketUtil.getSocketUtil(device.getDip(), 10026).sendData(data);
        }else if (device == null){
            return Constant.DEVICE_NOT_FOUND;
        }else {
            return Constant.DEVICE_STATE_EXCEPTION;
        }
        return 0;
    }

    @Override
    public int sendFile(String did,String file) {

        Device device = deviceDao.findDevice(did);
        if (device != null) {
            SocketUtil.getSocketUtil(device.getDip(), 10026).sendData(file);
        }else if (device == null){
            return Constant.DEVICE_NOT_FOUND;
        }else {
            return Constant.DEVICE_STATE_EXCEPTION;
        }
        return 0;
    }
}
