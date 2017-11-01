package com.zlcm.server.controller;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseData getDeviceList(HttpServletRequest request){
        ResponseData responseData = null;
        String uid = request.getParameter("loginId");
        List<Device> list = deviceService.findDevices(uid);
        responseData = ResponseData.ok();
        responseData.putDataValue("deviceList",list);
        return responseData;
    }

    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public ResponseData bindDevice(){
        ResponseData responseData = null;
        return responseData;
    }

    
}
