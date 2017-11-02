package com.zlcm.server.controller;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.DeviceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Resource
    DeviceService deviceService;

    /**
     * 设配上传
     * @param request
     * @return
     */
    @RequestMapping(value = "/report")
    public @ResponseBody ResponseData report(HttpServletRequest request){
        Device device = new Device();
        String ip = request.getParameter("ip");
        String state = request.getParameter("state");
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        String info = request.getParameter("info");
        device.setDip(ip);
        device.setDstate(state);
        device.setDlongitude(new BigDecimal(longitude));
        device.setDlatitude(new BigDecimal(latitude));
        device.setDinfo(info);
        deviceService.updateDevice(device);
        return ResponseData.ok();
    }

}
