package com.zlcm.server.controller.app;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.model.user.UcenterUserDetails;
import com.zlcm.server.model.user.UcenterUserLog;
import com.zlcm.server.service.*;
import com.zlcm.server.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/api/user")
@Api(value = "APP用户接口",description = "用户")
@Scope("session")
public class AppUserController{

    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);
    private String code;

    @Autowired
    UcenterUserService ucenterUserService;
    @Autowired
    UpmsApiService upmsApiService;
    @Autowired
    UcenterUserLogService ucenterUserLogService;
    @Autowired
    UcenterUserDetailsService ucenterUserDetailsService;

    @Autowired
    UcenterApiService ucenterApiService;
    /**
     * 获取手机验证码
     */
    @RequestMapping(value = "/phoneCode", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取手机验证码", notes = "")
    public ResponseData getPhoneCode(@RequestParam("phone") String phone){
        code = JavaSmsApi.getRandomStr(6,2);
        JavaSmsApi.sendM(phone,code);
        return ResponseData.ok();
    }

    /**
     * 注册(密码最大18位)
     */
    @RequestMapping(value = "/register",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "手机号注册", notes = "")
    public ResponseData phoneRegister(HttpServletRequest request,@RequestParam("phone") String phone,
                                      @RequestParam("code") String code,
                                      @RequestParam("password") String password){
        if (this.code == code) {
            String salt = MD5Utils.getSalt();
            String ip = IPUtils.getIpAddr(request);
            UcenterUser ucenterUser = new UcenterUser();
            ucenterUser.setRegister_ip(ip);
            ucenterUser.setPassword(MD5Utils.MD5(password + salt));
            ucenterUser.setUsername(phone);
            ucenterUser.setPhone(phone);
            ucenterUser.setSalt(salt);
            UserAgent userAgent = UserAgentUtil.getUserAgent(request.getHeader("user-agent"));
            ucenterApiService.insertUser(ucenterUser,userAgent.getPlatformType(),ip,"url/"+request.getRequestURL() +"/手机号注册");
            return ResponseData.ok();
        }else {
            return ResponseData.phoneError();
        }
    }


    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "账号密码登录")
    public ResponseData login(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);
        if (ucenterUser == null){
            //账号不存在
        }
        if (!MD5Utils.MD5(password+ucenterUser.getSalt()).equals(ucenterUser.getPassword())){
            //密码错误
        }
        return ResponseData.ok();
    }

    /**
     * 修改密码
     */

    /**
     * 实名认证
     */

    /**
     * 获取用户信息
     */

    /**
     * 修改用户信息
     */

    /**
     * 第三方登录
     */
}
