//package com.zlcm.server.controller.admin;
//
//import com.alibaba.fastjson.JSON;
//import com.zlcm.server.base.BaseController;
//import com.zlcm.server.model.LayuiResponse;
//import com.zlcm.server.model.user.UcenterUser;
//import com.zlcm.server.service.UcenterUserService;
//import com.zlcm.server.service.UpmsApiService;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.apache.shiro.SecurityUtils;
//import org.apache.shiro.subject.Subject;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/manage")
//@Api(value = "后台管理", description = "后台管理")
//public class AdminLoginController extends BaseController{
//
//    private static Logger _log = LoggerFactory.getLogger(AdminLoginController.class);
//
//    @Autowired
//    UpmsApiService upmsApiService;
//    @Autowired
//    UcenterUserService ucenterUserService;
//
//
//    @ApiOperation(value = "后台首页")
//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String index(ModelMap modelMap){
//        Subject subject = SecurityUtils.getSubject();
//        String username = (String) subject.getPrincipal();
//        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);
//        List<UpmsPermission> upmsPermissions =
//                upmsApiService.selectUpmsPermissionByUpmsUserId(ucenterUser.getUser_id());
//        modelMap.put("upmsPermissions",upmsPermissions);
//        return "/login.html";
//    }
//
//    @ApiOperation(value = "获取用户")
//    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
//    @ResponseBody
//    public LayuiResponse getList(@RequestParam(value = "page") Integer page, @RequestParam(value = "limit") Integer limit){
//        LayuiResponse layuiResponse;
//        List<UcenterUser> list = ucenterUserService.getPageList(page,limit);
//
//        if (list != null){
//            layuiResponse = LayuiResponse.ok();
//            layuiResponse.addDataValue(list);
//            String s = JSON.toJSONString(layuiResponse);
//            return layuiResponse;
//        }
//        return LayuiResponse.customerError();
//    }
//}
