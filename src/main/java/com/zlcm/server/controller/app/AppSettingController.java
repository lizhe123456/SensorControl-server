package com.zlcm.server.controller.app;

import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.base.BaseController;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.service.AppUserService;
import com.zlcm.server.util.FileUtils;
import com.zlcm.server.util.id.LoginId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.File;

@Controller
@Api(value = "APP应用接口",description="应用相关")
@RequestMapping("/api/setting")
public class AppSettingController extends BaseController{

    @Autowired
    AppUserService appUserService;

    @RequestMapping(value = "/newversion",method = RequestMethod.POST)
    @ApiOperation("获取最新版本")
    @SystemControllerLog(description = "获取最新版本")
    @ResponseBody
    public ResponseData getNewVersion(){
        ResponseData responseData = ResponseData.ok();
        File file = new File("/alidata/server/version.txt");
        String version = FileUtils.txt2String(file);
        responseData.putDataValue("version",version);
        return responseData;
    }

    @RequestMapping(value = "/feedback",method = RequestMethod.POST)
    @ApiOperation("提交意见反馈")
    @SystemControllerLog(description = "意见反馈")
    @ResponseBody
    public ResponseData getFeedBack(HttpServletRequest request){
        Integer uid = LoginId.getUid(request);
        String desc = request.getParameter("desc");
        String phone = request.getParameter("phone");
        try {
            appUserService.feedBack(uid,desc,phone);
            return ResponseData.ok();
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

}
