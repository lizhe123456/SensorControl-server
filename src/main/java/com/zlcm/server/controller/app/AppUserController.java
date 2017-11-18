package com.zlcm.server.controller.app;

import com.alibaba.druid.support.json.JSONUtils;
import com.zlcm.server.interceptor.LoginRequired;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.Sms;
import com.zlcm.server.model.user.UcenterUser;
import com.zlcm.server.model.user.UcenterUserDetails;
import com.zlcm.server.service.*;
import com.zlcm.server.util.*;
import com.zlcm.server.util.id.UUIDTools;
import com.zlcm.server.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.TextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
        code = JavaSmsApi.getRandomStr(6,0);
        try {
            String s = JavaSmsApi.sendM(phone,code);
            Sms sms = JackJsonUtils.fromJson(s, Sms.class);
            if (sms.getCode() == 22){
                return ResponseData.codeError();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(phone);
        if (ucenterUser != null){
            return ResponseData.userFound();
        }
        if (!TextUtils.isEmpty(this.code) && this.code .equals(code)) {
            String salt = MD5Utils.getSalt();
            String ip = IPUtils.getIpAddr(request);
            ucenterUser = new UcenterUser();
            ucenterUser.setRegister_ip(ip);
            ucenterUser.setPassword(MD5Utils.MD5(password + salt));
            ucenterUser.setUsername(phone);
            ucenterUser.setSalt(salt);
            String ua = request.getHeader("user-agent");
            ucenterApiService.insertUser(ucenterUser,UserAgentUtil.getMobileOS(ua),ip,"url/"+request.getRequestURL() +"/手机号注册");
            return ResponseData.ok();
        }else {
            return ResponseData.phoneError();
        }
    }

    /**
     * 手机验证码登录
     */

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "账号密码登录")
    public ResponseData login(@RequestParam("username") String username,
                              @RequestParam("password") String password){
        // 查询用户信息
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);

        if (ucenterUser == null) {
            return ResponseData.userNull();
        }
        if (!ucenterUser.getPassword().equals(MD5Utils.MD5(password + ucenterUser.getSalt()))){
            return ResponseData.passError();
        }
        if (ucenterUser.getLocked() == 1){
            return ResponseData.userLocked();
        }
        ucenterUser.setState(1);
        ucenterUserService.update(ucenterUser);
        String token = JwtUtil.sign(ucenterUser,1000*60*60*24*30);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("token",token);
        responseData.putDataValue("loginId",String.valueOf(ucenterUser.getUser_id()));
        return responseData;
    }

    /**
     * 修改密码
     */

    /**
     * 实名认证
     * type认证方式 0.手机号 1.身份证
     */
    @LoginRequired
    @RequestMapping(value = "/authen",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "实名认证")
    public ResponseData authen(@RequestParam("type") Integer type,
                               @RequestParam("name") String name,
                               @RequestParam("idCard") String idCard,
                               @RequestParam("phone") String phone){
        //0
        if (type == 0){

        }else if (type == 1){

        }
        return null;
    }

    /**
     * 获取用户信息
     */
    @LoginRequired
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取用户信息")
    public ResponseData getUserInfo(@RequestParam("username") String username){
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);
        ResponseData responseData = ResponseData.ok();
        responseData.putDataValue("username",ucenterUser);
        return responseData;
    }

    /**
     * 修改用户信息
     */
    @LoginRequired
    @RequestMapping(value = "/update/info",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改用户信息")
    public void getUpdateUserInfo(HttpServletRequest request,@RequestParam("username") String username,
                                  @RequestParam("name") String name,
                                  @RequestParam("sex") Integer sex,
                                  @RequestParam("email") String email,
                                  @RequestParam("uploadFile") MultipartFile file,
                                  @RequestParam("signature") String signature){
        UcenterUser ucenterUser = upmsApiService.selectUpmsUserByUsername(username);
        UcenterUserDetails ucenterUserDetails = ucenterUserDetailsService.get(ucenterUser.getUser_id());
        ucenterUserDetails.setSex(sex);
        ucenterUserDetails.setNickname(name);
        ucenterUserDetails.setEmail(email);
        String newFileName = DateUtil.getStringDate() + "_" + UUIDTools.getImgName()+".bmp";
        ServletContext servletContext = request.getSession().getServletContext();
        // 设定文件保存的目录
        String path = servletContext.getRealPath("/avatar") + "/";
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
        ucenterUserDetails.setAvatar(serverPath);
        ucenterUserDetails.setSignature(signature);
        ucenterUserService.update(ucenterUser);
    }

    /**
     * 退出登录
     */
    @LoginRequired
    @RequestMapping(value="/logout",method=RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "退出登录")
    public String logout() {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return JSONUtils.toJSONString(result);
    }


    /**
     * 第三方登录
     */
}
