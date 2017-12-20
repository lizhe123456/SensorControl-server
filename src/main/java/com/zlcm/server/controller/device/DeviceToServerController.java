//package com.zlcm.server.controller.device;
//
//import com.zlcm.server.controller.app.AppUserController;
//import com.zlcm.server.model.ResponseData;
//import com.zlcm.server.model.device.Device;
//import com.zlcm.server.service.DeviceService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.math.BigDecimal;
//
//@Controller
//@RequestMapping("/device/send")
//@Api(value = "设配接口",description = "用户")
//public class DeviceToServerController {
//
//    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);
//
//    @Autowired
//    DeviceService deviceService;
//
//    /**
//     * 上报信息
//     */
//    @RequestMapping(value = "/ip",method = RequestMethod.GET)
//    @ResponseBody
//    @ApiOperation("设配上报ip")
//    public ResponseData reportIp(@RequestParam("mac") String mac,@RequestParam("ip") String ip){
//        Device device = deviceService.getDeviceFormMac(mac);
//        if (device == null){
//            return ResponseData.deviceNull();
//        }
//        //设配状态1可用0不可用
//        if (device.getDstate() != 1) {
//            return ResponseData.deviceStateError();
//        }
//
//        device.setDip(ip);
//        deviceService.report(device);
//        return ResponseData.ok();
//    }
//
//    /**
//     * 上报信息
//     */
//    @RequestMapping(value = "/location",method = RequestMethod.GET)
//    @ResponseBody
//    @ApiOperation("设配上报定位信息")
//    public ResponseData reportLocation(@RequestParam("mac") String mac,@RequestParam("longitude") double longitude,
//                                       @RequestParam("latitude") double latitude){
//        Device device = deviceService.getDeviceFormMac(mac);
//        if (device == null){
//            return ResponseData.deviceNull();
//        }
//        //设配状态1可用0不可用
//        if (device.getDstate() != 1) {
//            return ResponseData.deviceStateError();
//        }
//
//        device.setDlatitude(BigDecimal.valueOf(latitude));
//        device.setDlongitude(BigDecimal.valueOf(longitude));
//        deviceService.report(device);
//        return ResponseData.ok();
//    }
//
//
//}
