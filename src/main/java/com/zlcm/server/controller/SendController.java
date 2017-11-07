package com.zlcm.server.controller;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.Result;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.user.UserInfo;
import com.zlcm.server.service.*;
import com.zlcm.server.util.BmpUtil;
import com.zlcm.server.util.DateUtil;
import com.zlcm.server.util.HexStrUtils;
import com.zlcm.server.util.SocketUtil;
import com.zlcm.server.util.id.UUIDTools;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/send")
public class SendController {

    @Resource
    ReceiveService receiveService;
    @Resource
    SendService sendService;
    @Resource
    SendRecordService sendRecordService;
    @Resource
    UserService userService;
    @Resource
    DeviceService deviceService;

    /**
     * 向指定设配发送图片
     * @param file
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/img",method = RequestMethod.POST)
    public @ResponseBody ResponseData sendImg(@RequestParam("uploadFile") MultipartFile file,
                                           HttpServletRequest request, ModelMap model) {
        String path = receiveService.receiveImg(file,request,model);
        String did = request.getParameter("did");
        String uid = request.getParameter("loginId");
        UserInfo userInfo = userService.getUserInfo(uid);
        Device device = deviceService.findDevice(did);
        if (deviceService.isBind(uid,did) == 0){
            return ResponseData.customerError();
        }else {
            sendService.sendImg(did, path);
            sendRecordService.insertRecord(userInfo, device, path, "img");
            return ResponseData.ok();
        }
    }

    /**
     * 向指定设配发送at指令
     * @param request
     * @return
     */
    @RequestMapping(value = "/at",method = RequestMethod.POST)
    public @ResponseBody ResponseData sendAt(HttpServletRequest request) {
        String at = request.getParameter("at");
        String did = request.getParameter("did");
        String uid = request.getParameter("loginId");
        UserInfo userInfo = userService.getUserInfo(uid);
        Device device = deviceService.findDevice(did);
        if (deviceService.isBind(uid,did) == 0){
            return ResponseData.customerError();
        }else {
            sendService.sendAt(did, at);
            sendRecordService.insertRecord(userInfo, device, at, "at");
            return ResponseData.ok();
        }
    }



}
