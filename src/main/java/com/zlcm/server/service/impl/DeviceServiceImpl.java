package com.zlcm.server.service.impl;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.DeviceBindDao;
import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.DeviceService;
import com.zlcm.server.util.id.UUIDTools;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 设配服务
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Resource
    DeviceDao deviceDao;

    @Resource
    DeviceBindDao deviceBindDao;


    @Override
    public void addDevice(String dName,String dType) {
        String did = UUIDTools.getUUID();
        Device device = new Device();
        device.setDip(did);
        device.setName(dName);
        device.setType(dType);
    }

    @Override
    public int deleteDevice(String mac) {
        deviceDao.deleteDevices(mac);
        return Constant.DEVICE_DELETE_SUCCESS;
    }

    @Override
    public int bingDevice(String uid, String did) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("did",did);
        if (isBind(uid,did) == 0){
            return Constant.DEVICE_BIND;
        }else {
            try {
                deviceBindDao.bindDevice(map);
                return Constant.DEVICE_UNBIND_SUCCESS;
            }catch (Exception e){
                e.printStackTrace();
                return Constant.DEVICE_UNBIND_ERROR;
            }
        }
    }

    @Override
    public int unBindDevice(String uid, String did) {
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("did",did);
        if (isBind(uid,did) != 0){
            return Constant.DEVICE_BIND;
        }else {
            try {
                deviceBindDao.usBindDevice(map);
                return Constant.DEVICE_UNBIND_SUCCESS;
            }catch (Exception e){
                e.printStackTrace();
                return Constant.DEVICE_UNBIND_ERROR;
            }
        }
    }

    @Override
    public int isBind(String uid, String did){
        Map<String, Object> map = new HashMap<>();
        map.put("uid",uid);
        map.put("did",did);
        return deviceBindDao.isBind(map);
    }

    @Override
    public List<Device> findDevices(String uid) {
        return deviceDao.findDevices(uid);
    }

    @Override
    public List<Device> pagingDevices(int page, int size) {
        Map<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("size",size);
        List<Device> list = deviceDao.pagingDevices(map);
        return list;
    }

    @Override
    public List<Device> peripheryDevices(double longitude, double latitude) {
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dis = 0.5;//0.5千米距离
        double dlng =  2*Math.asin(Math.sin(dis/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = dis/r;
        dlat = dlat*180/Math.PI;
        double minlat =latitude-dlat;
        double maxlat = latitude+dlat;
        double minlng = longitude -dlng;
        double maxlng = longitude + dlng;
        Map<String , Object> map = new HashMap<>();
        map.put("minlat",minlat);
        map.put("maxlat",maxlat);
        map.put("minlng",minlng);
        map.put("maxlng",maxlng);
        return deviceDao.peripheryDevices(map);
    }

    @Override
    public void updateDevice(Device device) {
        deviceDao.upDateDevice(device);
    }

    @Override
    public Device findDevice(String did) {
        return deviceDao.findDevice(did);
    }

}
