package com.zlcm.server.controller;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.Result;
import com.zlcm.server.service.ReceiveService;
import com.zlcm.server.util.BmpUtil;
import com.zlcm.server.util.DateUtil;
import com.zlcm.server.util.HexStrUtils;
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
@RequestMapping("/receive")
public class ReceiveController {

    @Resource
    ReceiveService receiveService;

    @RequestMapping(value = "/api/img",method = RequestMethod.POST)
    public @ResponseBody ResponseData receiveImg(@RequestParam("uploadFile") MultipartFile file,
                                           HttpServletRequest request, ModelMap model) {
        String newFileName = DateUtil.getStringDate()+"_"+UUIDTools.getImgName()+".bmp";
        //获取项目路径
        ServletContext servletContext = request.getSession().getServletContext();
        // 设定文件保存的目录
        String path = servletContext.getRealPath("/img") + "/";

        File f = new File(path);
        if (!f.exists())
            f.mkdirs();
        if (!file.isEmpty()){
            try {
                FileOutputStream fos = new FileOutputStream(path + newFileName);
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

        System.out.println("上传图片到:" + path + newFileName);
        // 保存文件地址，用于JSP页面回显
        model.addAttribute("fileUrl", path + newFileName);
        byte[] rgb = BmpUtil.getImagePixel(path + newFileName);
        String s = HexStrUtils.bytesToHexString(rgb);

        System.out.println(s);
        return ResponseData.ok();
    }

    @RequestMapping("/device/info")
    public void receiveIP(HttpServletRequest request, HttpServletResponse response) {
        String ip = request.getParameter("ip");
        if (ip != null) {
            receiveService.reportDeviceIp(ip);
        }

        String longitude = request.getParameter("longitude");
        String latitude = request.getParameter("latitude");
        if (longitude != null & latitude != null){
            receiveService.reportLocation(longitude,latitude);
        }
        String state = request.getParameter("state");
        if (state != null){
            receiveService.reportDeviceState(state);
        }
        String info = request.getParameter("info");
        if (info != null){
            receiveService.reportDeviceInfo(info);
        }
    }


    @RequestMapping("/device/info")
    public void receiveDeviceInfo(){

    }




}
