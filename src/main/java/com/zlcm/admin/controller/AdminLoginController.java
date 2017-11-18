package com.zlcm.admin.controller;

import com.zlcm.server.base.BaseController;
import com.zlcm.server.model.upms.UpmsPermission;
import com.zlcm.server.model.upms.UpmsSystem;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.service.UpmsApiService;
import com.zlcm.server.service.UpmsSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class AdminLoginController extends BaseController{

    private static Logger _log = LoggerFactory.getLogger(AdminLoginController.class);

    @Autowired
    UpmsApiService upmsApiService;

    @ApiOperation(value = "后台首页")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String index(ModelMap modelMap){
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);
        List<UpmsPermission> upmsPermissions =
                upmsApiService.selectUpmsPermissionByUpmsUserId(ucenterUser.getUser_id());
        modelMap.put("upmsPermissions",upmsPermissions);
        return "/login.html";
    }
}
