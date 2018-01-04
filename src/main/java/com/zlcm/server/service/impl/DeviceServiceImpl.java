package com.zlcm.server.service.impl;

import com.zlcm.server.base.BaseServiceImpl;
import com.zlcm.server.dao.DeviceMapper;
import com.zlcm.server.model.apprep.AppDevice;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class DeviceServiceImpl extends BaseServiceImpl<Device,DeviceMapper> implements DeviceService{


    @Override
    public List<Device> findDevices(Integer uid) {
        return null;
    }

    @Override
    public void report(Device device) {

    }

    /**
     * range单位千米
     * @param longitude
     * @param latitude
     * @param range
     * @return
     */
    @Override
    public List<AppDevice> findPeriphery(double longitude, double latitude, double range, int size) {
        Map<String , Object> map = new HashMap<>();
        map.put("lng",longitude);
        map.put("lat",latitude);
        map.put("range",range);
        map.put("size",size);
        return dao.peripheryDevices(map);
    }

    @Override
    public Device getDeviceFormMac(String mac) {
        return dao.getDeviceFormMac(mac);
    }

    @Override
    public int getHouseholdNum(Integer did) {
        int num = dao.findHouseholdNum(did);
        return (int) ((num * 0.8f) * 3);
    }

    @Override
    public List<AppDevice> findDevicesList(List<Integer> devices, String province, String city, String area,int size,int page) {
        Map map = new HashMap();
        map.put("list",devices);
        map.put("province",province);
        map.put("city",city);
        map.put("area",area);
        map.put("size",size);
        map.put("page",page*size);
        return dao.findDevicesList(map);
    }

}
