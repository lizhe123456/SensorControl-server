package com.zlcm.server.controller.app;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.DeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/device")
@Api(value = "APP设配接口",description = "设配")
public class AppDeviceController {

    private static Logger _log = LoggerFactory.getLogger(AppDeviceController.class);

    @Autowired
    DeviceService deviceService;


    /**
     * 获取设配列表
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取设配列表")
    public ResponseData getDeviceList(@RequestParam("uid") Integer uid){
        List<Device> list = deviceService.findDevices(uid);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("list",list);
        return responseData;
    }

    /**
     * 绑定设配
     */
    @RequestMapping(value = "/bind",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "绑定设配")
    public ResponseData bindDevice(@RequestParam("uid") Integer uid, @RequestParam("did") String did){
        deviceService.bind(uid, did);
        return ResponseData.ok();
    }

    /**
     * 解绑设配
     */
    @RequestMapping(value = "/unbind",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "解绑设配")
    public ResponseData unbindDevice(@RequestParam("uid") Integer uid, @RequestParam("did") String did){
        deviceService.unbind(uid, did);
        return ResponseData.ok();
    }
    /**
     * 查询周边设配
     */
    @RequestMapping(value = "/periphery",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "查询周边设配")
    public ResponseData peripheryDevice(@RequestParam("longitude") double longitude,
                                        @RequestParam("latitude") double latitude,
                                        @RequestParam(value = "range",defaultValue = "0.5") double range){
        List<Device> list = deviceService.findPeriphery(longitude, latitude,range);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("device", list);
        return responseData;
    }

}
