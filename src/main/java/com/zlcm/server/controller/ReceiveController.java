package com.zlcm.server.controller;

import com.zlcm.server.model.Result;
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
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@Controller
@RequestMapping("/send")
public class ReceiveController {


    @RequestMapping(value = "/img",method = RequestMethod.POST)
    public @ResponseBody Result receiveImg(@RequestParam("uploadFile") MultipartFile file,
                                           HttpServletRequest request, ModelMap model) {
        String type = file.getContentType();
        String fileName = file.getOriginalFilename();
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
        return new Result(200,"上传成功");
    }

    @RequestMapping()
    public @ResponseBody Result receiveAT(){
        Result result = null;

        return result;
    }


}
