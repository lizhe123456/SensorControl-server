package com.zlcm.server.controller.app;

import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.base.BaseController;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.interceptor.LoginRequired;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.apprep.AppDevice;
import com.zlcm.server.model.bean.*;
import com.zlcm.server.service.*;
import com.zlcm.server.util.*;
import com.zlcm.server.util.id.LoginId;
import com.zlcm.server.util.id.UUIDTools;
import com.zlcm.server.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.util.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/api/user")
@Api(value = "APP用户接口",description = "用户")
@Scope("session")
public class AppUserController extends BaseController{

    private static Logger _log = LoggerFactory.getLogger(AppUserController.class);

    @Autowired
    AppUserService appUserService;

    @Autowired
    UserService userService;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    StoreService storeService;


    /**
     * 获取手机验证码
     */
    @RequestMapping(value = "/phoneCode", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取手机验证码", notes = "")
    @SystemControllerLog(description = "获取手机验证码")
    public ResponseData getPhoneCode(@RequestParam("mobile") String phone,HttpServletRequest request){
        String code = appUserService.getPhoneCode(phone);
        if (code == null || code.equals("")){
            return ResponseData.codeError();
        }else {
            request.getServletContext().setAttribute("code", code);
        }
        return ResponseData.ok();
    }
    /**
     * 测试接口
     */
    @RequestMapping(value = "/hehe", method = RequestMethod.GET)
    @ResponseBody
    @SystemControllerLog(description = "测试heh")
    public ResponseData heiehi(@RequestParam("heh") String hee,HttpServletRequest request){
        return ResponseData.ok();
    }


    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "用户登录", notes = "")
    @SystemControllerLog(description = "用户登录")
    public ResponseData userRegister(HttpServletRequest request,
                                     @RequestParam("mobile") String mobile,
                                     @RequestParam("code") String code){
        String code1 = (String) request.getServletContext().getAttribute("code");
        try {
            User user;
            if (!TextUtils.isEmpty(code1) && code1 .equals(code)) {
                user = appUserService.login(mobile,request);
                if (user.getLocked() == 1){
                    return ResponseData.userLocked();
                }
                String token = JwtUtil.sign(user,1000*60*60*24*30);
//                String loginId = new String(RSAUtils.encryptByPublicKey(String.valueOf(user.getUid()).getBytes(), Constant.PUBLIC_KEY),"utf-8");
                ResponseData responseData = ResponseData.ok();
                responseData.putDataValue("token",token);
//                responseData.putDataValue("loginId",loginId);
                request.getServletContext().removeAttribute("code");
                return responseData;
            }else {
                return ResponseData.phoneError();
            }
        } catch (SysException e) {
            return ResponseData.sqlError();
        } catch (Exception e) {
            return ResponseData.forbidden();
        }
    }


    /**
     * 实名认证
     * type认证方式 0.手机号 1.身份证
     */
    @LoginRequired
    @RequestMapping(value = "/authen",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "实名认证")
    @SystemControllerLog(description = "实名认证")
    public ResponseData authen(@RequestParam("type") Integer type){
        //0
        if (type == 0){

        }else if (type == 1){

        }
        return null;
    }

    /**
     * 店家认证
     */


    /**
     * 获取用户信息
     */
    @LoginRequired
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取用户信息")
    @SystemControllerLog(description = "获取用户信息")
    public ResponseData getUserInfo(@RequestParam("username") String username){
        AppUserInfo userInfo = new AppUserInfo();
        try {
            User user = userService.getUserForName(username);
            UserDetails userDetails = appUserService.getUserInfo(user.getUid());
            if (userDetails != null) {
                Store store = storeService.get(userDetails.getStorId());
                if (store != null){
                    userInfo.setStorename(store.getName());
                    userInfo.setStorephone(store.getIphone());
                    userInfo.setAddress(store.getAddress());
                }
                if (!TextUtils.isEmpty(userDetails.getIdcrad())){
                    userInfo.setReal_name(userDetails.getRealName());
                    userInfo.setBirthday(DateUtil.dateToStr(userDetails.getBirthday()));
                    userInfo.setSex(userDetails.getSex() == 0 ? "男" : "女");
                    userInfo.setIdCrad(StringReplaceUtil.IdReplaceWithStar(userDetails.getIdcrad()));
                }
                userInfo.setPhone(StringReplaceUtil.phoneReplaceWithStar(userDetails.getPhone()));
                userInfo.setNickname(userDetails.getNickname());
            }else {
                //userDetails为null
            }
            ResponseData responseData = ResponseData.ok();
            responseData.putDataValue("userInfo",userInfo);
            return responseData;
        } catch (SysException e) {
            return ResponseData.sqlError();
        }
    }



    /**
     * 上传头像
     */
    @RequestMapping(value = "/update/avatar",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传头像")
    @SystemControllerLog(description = "上传头像")
    public void getUpdateUserAvatar(HttpServletRequest request, @RequestParam("uploadFile") MultipartFile file){
        try {
            Integer uid = LoginId.getUid(request);
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
            appUserService.updateAvatar(Integer.valueOf(uid),serverPath);
        } catch (SysException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update/info",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "修改用户信息")
    @SystemControllerLog(description = "修改用户信息")
    public ResponseData updateUserInfo(HttpServletRequest request){
        try {
            Integer uid = LoginId.getUid(request);
            String email = request.getParameter("email");
            String nickname = request.getParameter("nickname");
            UserDetails userDetails = userDetailsService.get(uid);
            if (userDetails != null){
                userDetails.setEmail(email);
                userDetails.setNickname(nickname);
                userDetailsService.update(userDetails);
            }
        } catch (Exception e) {
            return ResponseData.notFound();
        }
        return ResponseData.ok();
    }

    /**
     * 退出登录
     */
    @RequestMapping(value="/logout",method=RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "退出登录")
    @SystemControllerLog(description = "退出登录")
    public ResponseData logout(HttpServletRequest request) {
        try {
            Integer uid = LoginId.getUid(request);
            User user  = userService.get(uid);
            if (user != null){
                user.setState((byte) 0);
                userService.update(user);
                return ResponseData.ok();
            }
            return ResponseData.notFound();
        } catch (Exception e) {
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取首页信息")
    @SystemControllerLog(description = "获取首页信息")
    public ResponseData homepage(HttpServletRequest request,@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude
            ,@RequestParam(value = "range", defaultValue = "5") double range,@RequestParam(value = "size", defaultValue = "1000") int size){
        ResponseData responseData;
        try {
            Integer uid = LoginId.getUid(request);
            responseData = ResponseData.ok();
            if (uid != null && uid != 0){
                UserDetails userDetails = userDetailsService.get(uid);
                responseData.putDataValue("head",null);
                responseData.putDataValue("pushInfo",null);
                responseData.putDataValue("logo",null);
                responseData.putDataValue("wallet",null);
            }
            List<AppDevice> devices = deviceService.findPeriphery(longitude,latitude,range,size);
            responseData.putDataValue("hean",null);
            responseData.putDataValue("pushInfo",null);
            responseData.putDataValue("deviceList",devices);
            responseData.putDataValue("logo",null);
            responseData.putDataValue("wallet",null);
            return responseData;
        } catch (Exception e) {
            _log.error(e.toString());
            return ResponseData.notFound();
        }
    }


}
