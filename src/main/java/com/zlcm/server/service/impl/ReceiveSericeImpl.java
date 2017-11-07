package com.zlcm.server.service.impl;

import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.ReceiveService;
import com.zlcm.server.util.DateUtil;
import com.zlcm.server.util.id.UUIDTools;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;

@Service
public class ReceiveSericeImpl implements ReceiveService{

    @Resource
    DeviceDao deviceDao;

    @Override
    public void apiForDevice() {

    }

    @Override
    public void receiveAT(String at) {

    }

    @Override
    public void receiveFile(byte[] img) {

    }

    @Override
    public String receiveImg(MultipartFile file, HttpServletRequest request, ModelMap model) {
        String newFileName = DateUtil.getStringDate()+"_"+ UUIDTools.getImgName()+".bmp";
        //获取项目路径
        ServletContext servletContext = request.getSession().getServletContext();
        // 设定文件保存的目录
        String path = servletContext.getRealPath("/img") + "/";

        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
        if (!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1){
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }
        return path + newFileName;
    }


    @Override
    public void reportLocation(String longitude, String latitude) {
        Device device = new Device();
        device.setDlongitude(Double.parseDouble(longitude));
        device.setDlatitude(Double.parseDouble(latitude));
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
