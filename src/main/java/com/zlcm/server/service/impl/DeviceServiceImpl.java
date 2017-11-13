package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.device.DeviceMapper;
import com.zlcm.server.dao.device.UserBindDeviceMapper;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.device.UserBindDevice;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device,DeviceMapper> implements DeviceService{

    @Autowired
    UserBindDeviceMapper userBindDeviceMapper;

    @Override
    public List<UcenterUser> findUsers(String did) {
        return dao.findUsers(did);
    }

    @Override
    public List<Device> findDevices(Integer uid) {
        return dao.findDevices(uid);
    }

    @Override
    public void report(Device device) {
        dao.update(device);
    }

    @Override
    public void bind(Integer uid, String did) {
        UserBindDevice userBindDevice = new UserBindDevice();
        userBindDevice.setDid(did);
        userBindDevice.setUserId(uid);
        userBindDeviceMapper.save(userBindDevice);
    }

    @Override
    public void unbind(Integer uid, String did) {
        Map<String, Object> map = new HashMap<>();
        map.put("did", did);
        map.put("uid", uid);
        userBindDeviceMapper.unbind(map);
    }

    /**
     * range单位千米
     * @param longitude
     * @param latitude
     * @param range
     * @return
     */
    @Override
    public List<Device> findPeriphery(double longitude, double latitude, double range) {
        //先计算查询点的经纬度范围
        double r = 6371;//地球半径千米
        double dlng =  2*Math.asin(Math.sin(range/(2*r))/Math.cos(latitude*Math.PI/180));
        dlng = dlng*180/Math.PI;//角度转为弧度
        double dlat = range/r;
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
        return dao.peripheryDevices(map);
    }

    @Override
    public Device get(String pk) {
        return dao.get(pk);
    }

    @Override
    public Device getDeviceFormMac(String mac) {
        return dao.getDeviceFormMac(mac);
    }


}
