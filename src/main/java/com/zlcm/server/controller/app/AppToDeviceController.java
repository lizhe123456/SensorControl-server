package com.zlcm.server.controller.app;

import com.alibaba.fastjson.JSON;
import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.base.BaseController;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.apprep.AppDevice;
import com.zlcm.server.model.apprep.Periphery;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.UserDetails;
import com.zlcm.server.service.*;
import com.zlcm.server.util.id.LoginId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/device")
@Api(value = "APP设配接口",description = "设配")
public class AppToDeviceController extends BaseController {

    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    DeviceService deviceService;
    @Autowired
    AdvertService advertService;
    @Autowired
    UserDetailsService userDetailsService;


    /**
     * 获取设配计费信息
     */
    @RequestMapping(value = "/charging",method = RequestMethod.POST)
    @ResponseBody
    @SystemControllerLog(description = "获取设配计费信息")
    @ApiOperation(value = "获取设配计费信息")
    public ResponseData charging(HttpServletRequest request,@RequestParam("did") int did){
        ResponseData responseData;
        try {
            Device device = deviceService.get(did);
            Integer uid = LoginId.getUid(request);
            responseData = ResponseData.ok();
            responseData.putDataValue("address",device.getAddress());
            responseData.putDataValue("charging",1);
            responseData.putDataValue("did",device.getDid());
            responseData.putDataValue("household",device.getHousehold());
            responseData.putDataValue("visitorsflowrate",((device.getHousehold() * 0.8f) * 3));
            if (uid != null){
                UserDetails userDetails = userDetailsService.get(uid);
                if (userDetails != null && !TextUtils.isEmpty(userDetails.getRealName())){
                    if (userDetails.getStorId() == null || userDetails.getStorId() == 0){
                        responseData.putDataValue("authCode",2);
                    }else {
                        responseData.putDataValue("authCode",1);
                    }
                }else {
                    responseData.putDataValue("authCode",0);
                }
            }else {
                responseData.putDataValue("authCode",0);
            }
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        } catch (Exception e) {
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/periphery", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("我的周边")
    @SystemControllerLog(description = "获取我的周边")
    public ResponseData getMyperiphery(HttpServletRequest request){
        double longitude = Double.parseDouble(request.getParameter("longitude"));
        double latitude = Double.parseDouble(request.getParameter("latitude"));
        ResponseData responseData = ResponseData.ok();
        try {
            List<AppDevice> devices = deviceService.findPeriphery(longitude,latitude,20,5);
            if (devices == null){
                responseData.putDataValue("devices",null);
                return responseData;
            }
            responseData.putDataValue("devices",generatePeriphery(devices));
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/delivery", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("投放设配列表")
    @SystemControllerLog(description = "获取投放设配列表")
    public ResponseData getDeliveryList(HttpServletRequest request, @RequestParam(value = "devices",defaultValue = "") String json, @RequestParam(value = "page" , defaultValue = "0") int page, @RequestParam(value = "size",defaultValue = "5") int size){
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        List<Integer> devices = JSON.parseArray(json,Integer.class);
        if (devices.size() == 0){
            devices = null;
        }

        ResponseData responseData = ResponseData.ok();
        try {
            List<AppDevice> device = deviceService.findDevicesList(devices,province,city,area,size,page);
            if (device == null){
                responseData.putDataValue("devices",null);
                return responseData;
            }
            responseData.putDataValue("devices", generatePeriphery(device));
            return responseData;
        } catch (SysException e){
            return ResponseData.notFound();
        } catch (Exception e){
            _log.error(e.getMessage());
            return ResponseData.notFound();
        }
    }

    private List<Periphery> generatePeriphery(List<AppDevice> devices) throws SysException {
        List<Periphery> peripheries = new ArrayList<>();
        for (AppDevice d : devices) {
            Periphery periphery = new Periphery();
            periphery.setAddress(d.getAddress());
            List<String> list = advertService.findAdvertFordid(d.getDid());
            periphery.setDid(d.getDid());
            periphery.setAdvert(list);
            peripheries.add(periphery);
        }
        return peripheries;
    }


}
