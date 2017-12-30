package com.zlcm.server.controller.app;

import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.interceptor.LoginRequired;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.apprep.AppDevice;
import com.zlcm.server.model.apprep.Periphery;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.model.bean.User;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/device")
@Api(value = "APP设配接口",description = "设配")
public class AppToDeviceController {

    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    DeviceService deviceService;
    @Autowired
    AdvertService advertService;
    @Autowired
    UserDetailsService userDetailsService;

    /**
     * 发送数据
     */
    @RequestMapping(value = "/advert",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "发送广告")
    public ResponseData sendData(HttpServletRequest request, @RequestParam("did") String did,
                                 @RequestParam("uploadFile") MultipartFile file,
                                 @RequestParam(value = "desc", defaultValue = "") String desc,
                                 @RequestParam(value = "continuedTime",defaultValue = "60000") String continuedTime){
        return null;

    }



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
            responseData.putDataValue("charging",10);
            responseData.putDataValue("household",device.getHousehold());
            responseData.putDataValue("visitorsflowrate",7000);
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
        List<Periphery> peripheries = new ArrayList<>();
        try {
            List<AppDevice> devices = deviceService.findPeriphery(longitude,latitude,20,5);
            if (devices == null){
                responseData.putDataValue("devices",null);
                return responseData;
            }
            for (AppDevice device : devices) {
                Periphery periphery = new Periphery();
                periphery.setAddress(device.getAddress());
                List<String> list = advertService.findAdvertFordid(device.getDid());
                periphery.setDid(device.getDid());
                periphery.setAdvert(list);
                peripheries.add(periphery);
            }
            responseData.putDataValue("devices",peripheries);
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }
}
