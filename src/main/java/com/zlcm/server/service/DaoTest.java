package com.zlcm.server.service;

import com.zlcm.server.dao.DeviceDao;
import com.zlcm.server.dao.UserDao;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.user.UserInfo;
import com.zlcm.server.model.user.UserUcenter;
import com.zlcm.server.util.id.UUIDTools;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
public class DaoTest {

    @Autowired
    UserDao userDao;
    @Autowired
    DeviceDao deviceDao;

    @Test
    public void testQueryUser(){
        Map<String,Object> map = new HashMap();
        map.put("username","woshitigexi");
        map.put("password","123456");
        int userUcenter = userDao.selectUser("woshitigexi","123456");
    }

    @Test
    public void testInsertUser(){
        String uid = getUid();
        int num = userDao.selectUserFormName("woshitigexi");
        if (num == 0) {
            UserUcenter userUcenter = new UserUcenter();
            userUcenter.setUsername("woshitigexi");
            userUcenter.setPassword("woshitigexi");
            userUcenter.setUid(uid);
            UserInfo userInfo = new UserInfo();
            userInfo.setUid(uid);
            boolean t = userDao.insertUserUcenter(userUcenter);
            boolean t1 = userDao.insertUserInfo(userInfo);
        }
    }
    @Test
    public void testSelectByMap() {
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("start",0);
            map.put("pagesize",4);
            List<Device> re = deviceDao.findDevices(map);
            for(Device ui:re){
                System.out.println(ui);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void insertD(){
        for (int i = 0; i < 100; i++) {
            Device device = new Device();
            device.setDid(UUIDTools.getUUID());
            device.setName("测试设配"+i);
            device.setType("BSP8266");
            device.setDip("192.168.11."+i);
            deviceDao.insertDevices(device);
        }
    }

    @Test
    public void updateDevice(){
        Device device = new Device();
        device.setDid("00567c18f0ab45618e4a5c7e8780f6a6");
        device.setName("粉刷本领强");
        device.setType("bsks566");
        device.setDstate("在线");
        deviceDao.upDateDevice(device);
    }


    public String getUid(){
        String uid = UUIDTools.uuid();
        if (userDao.selectUid(uid) != 0){
            getUid();
        }
        return uid;
    }
}