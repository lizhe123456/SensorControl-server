package com.zlcm.server.controller.app;

import com.zlcm.server.dao.user.UcenterUserLogDao;
import com.zlcm.server.interceptor.LoginRequired;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.device.Device;
import com.zlcm.server.model.upms.Advert;
import com.zlcm.server.model.user.UcenterUserLog;
import com.zlcm.server.service.AdvertService;
import com.zlcm.server.service.DeviceService;
import com.zlcm.server.service.UcenterApiService;
import com.zlcm.server.service.UcenterUserLogService;
import com.zlcm.server.util.*;
import com.zlcm.server.util.id.UUIDTools;
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
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/api/send")
@Api(value = "APP设配接口",description = "设配")
public class AppToDeviceController {

    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    DeviceService deviceService;
    @Autowired
    UcenterUserLogService ucenterUserLogService;
    @Autowired
    AdvertService advertService;

    /**
     * 发送数据
     */
    @LoginRequired
    @RequestMapping(value = "/advert",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "发送广告")
    public ResponseData sendData(HttpServletRequest request, @RequestParam("did") String did,
                                 @RequestParam("uploadFile") MultipartFile file,
                                 @RequestParam(value = "desc", defaultValue = "") String desc,
                                 @RequestParam(value = "continuedTime",defaultValue = "60000") String continuedTime){
        Device device = deviceService.get(did);
        if (device == null){
            return ResponseData.deviceNull();
        }
        String newFileName = DateUtil.getStringDate() + "_" + UUIDTools.getImgName()+".bmp";
        ServletContext servletContext = request.getSession().getServletContext();
        // 设定文件保存的目录
        String path = servletContext.getRealPath("/img") + "/";
        File f = new File(path);
        String serverPath = path + newFileName;
        if (!f.exists())
            f.mkdirs();
        if (!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(serverPath);
                InputStream in = file.getInputStream();
                int b = 0;
                while ((b = in.read()) != -1){
                    fos.write(b);
                }
                fos.close();
                in.close();
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }
        byte[] rgb = BmpUtil.getImagePixel(serverPath);
        String data = HexStrUtils.bytesToHexString(rgb);
        if (device.getDstate() == 1) {
            int code = SocketUtil.getSocketUtil(device.getDip(), 10026).sendData(data);
            if (code == 203){
                return ResponseData.sendError();
            }
            String ip = IPUtils.getIpAddr(request);
            String ua = request.getHeader("user-agent");
            UcenterUserLog log =
            GenerationLogUtils.generationUcenterUserLog(Integer.valueOf(request.getParameter("loginId")),ip,UserAgentUtil.getMobileOS(ua),"url/"+request.getRequestURL() +"/发出广告");
            ucenterUserLogService.save(log);
            Advert advert = new Advert();
            advert.setDid(did);
            advert.setDesc(desc);
            advert.setContinuedTime(continuedTime);
            advert.setInfo(serverPath);
            advert.setState((byte) 0);
            advert.setUserId(Integer.valueOf(request.getParameter("loginId")));
            advertService.save(advert);
            return ResponseData.ok();
        }else {
            return ResponseData.deviceStateError();
        }
    }

    /**
     * app上报用户信息
     */

}
