package com.zlcm.server.controller;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.service.DeviceService;
import com.zlcm.server.service.SendRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/device")
public class DeviceController {

    @Resource
    DeviceService deviceService;

    /**
     * 查询用户绑定设配
     * @param request
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public @ResponseBody ResponseData getDeviceList(HttpServletRequest request){
        ResponseData responseData = null;
        String uid = request.getParameter("loginId");
        List<Device> list = deviceService.findDevices(uid);
        responseData = ResponseData.ok();
        responseData.putDataValue("deviceList",list);
        return responseData;
    }

    /**
     * 绑定设配
     * @param request
     * @param device
     * @return
     */
    @RequestMapping(value = "/bind", method = RequestMethod.GET)
    public @ResponseBody ResponseData bindDevice(HttpServletRequest request,@RequestParam(value = "device") Device device){
        String uid = request.getParameter("loginId");
        String did = device.getDid();
        ResponseData responseData = null;
        int code = deviceService.bingDevice(uid,did);
        switch (code){
            case Constant.DEVICE_BIND:
                responseData = new ResponseData(code,"已绑定");
                break;
            case Constant.DEVICE_BIND_SUCCESS:
                responseData = new ResponseData(code,"绑定成功");
                break;
            case Constant.DEVICE_BIND_ERROR:
                responseData = new ResponseData(code,"绑定失败");
                break;
        }
        return responseData;
    }

    /**
     * 解除绑定
     * @param request
     * @param device
     * @return
     */
    @RequestMapping(value = "/unbind", method = RequestMethod.GET)
    public @ResponseBody ResponseData unBindDevice(HttpServletRequest request,@RequestParam(value = "device") Device device){
        String uid = request.getParameter("loginId");
        String did = device.getDid();
        ResponseData responseData = null;
        int code = deviceService.unBindDevice(uid,did);
        switch (code){
            case Constant.DEVICE_BIND:
                responseData = new ResponseData(code,"未绑定");
                break;
            case Constant.DEVICE_UNBIND_SUCCESS:
                responseData = new ResponseData(code,"解绑成功");
                break;
            case Constant.DEVICE_UNBIND_ERROR:
                responseData = new ResponseData(code,"解绑失败");
                break;
        }
        return responseData;
    }

    /**
     * 分页查询
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/list/paging", method = RequestMethod.GET)
    public @ResponseBody ResponseData getPagingData(@RequestParam(value = "page") int page,
                                                    @RequestParam(value = "size") int size){
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("device",deviceService.pagingDevices(page,size));
        return responseData;
    }

    /**
     * 查询周边500米
     * @param request
     * @return
     */
    @RequestMapping(value = "/periphery",method = RequestMethod.GET)
    public @ResponseBody ResponseData getPeripheryList(HttpServletRequest request){
        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        List<Device> list = deviceService.peripheryDevices(Double.parseDouble(longitude),Double.parseDouble(latitude));
        ResponseData responseData = null;
        responseData = ResponseData.ok();
        responseData.putDataValue("device",list);
        return responseData;
    }

    /**
     * 添加设配
     * @param request
     * @return
     */
    @RequestMapping(value = "/addDevice",method = RequestMethod.GET)
    public @ResponseBody ResponseData insertDevice(HttpServletRequest request){
        String name = request.getParameter("name");
        String type = request.getParameter("type");
        deviceService.addDevice(name,type);
        return ResponseData.ok();
    }

}
