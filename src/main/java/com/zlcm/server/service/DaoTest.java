//package com.zlcm.server.service;
//
//import com.zlcm.server.model.device.Device;
//import com.zlcm.server.util.id.UUIDTools;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.math.BigDecimal;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:spring-mybatis.xml"})
//public class DaoTest {
//
//    @Autowired
//    DeviceService deviceService;
//
//    @Test
//    public void saas(){
//        Device device = new Device();
//        device.setDlongitude(BigDecimal.valueOf(114.1603088379));
//        device.setDlatitude(BigDecimal.valueOf(30.6068904120));
//        device.setName("asdkasl");
//        device.setMac("25:88:77:88");
//        device.setDid(UUIDTools.getUUID());
//        device.setType("BSP8266");
//        deviceService.save(device);
//
////        List<Device> li  = deviceService.findPeriphery(114.1365337372,30.6181922948,0.5);
////        System.out.println(li.size());
//    }
//}
