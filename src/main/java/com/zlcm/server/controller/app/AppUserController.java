package com.zlcm.server.controller.app;

import com.alibaba.fastjson.JSON;
import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.base.BaseController;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.interceptor.LoginRequired;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.Result;
import com.zlcm.server.model.apprep.AppDevice;
import com.zlcm.server.model.bean.*;
import com.zlcm.server.service.*;
import com.zlcm.server.util.*;
import com.zlcm.server.util.id.LoginId;
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
import javax.servlet.http.HttpServletRequest;
import java.io.*;
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

    @RequestMapping(value = "/proving/phone",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("更换手机号")
    @SystemControllerLog(description = "更换手机号")
    public ResponseData updatePhone(HttpServletRequest request){
        Integer uid = LoginId.getUid(request);
        String phone = request.getParameter("mobile");
        String code = request.getParameter("code");
        String code1 = (String) request.getServletContext().getAttribute("code");
        if (!TextUtils.isEmpty(code1) && code1 .equals(code)) {
            try {
                appUserService.updatePhone(uid,phone);
                request.getServletContext().removeAttribute("code");
                return ResponseData.ok();
            } catch (SysException e) {
                request.getServletContext().removeAttribute("code");
                return ResponseData.no();
            }
        }else {
            request.getServletContext().removeAttribute("code");
            return ResponseData.notFound();
        }

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
                UserDetails userDetails = userDetailsService.get(user.getUid());
                String token = JwtUtil.sign(user,1000L*60L*60L*24L*30L);
//                String loginId = new String(RSAUtils.encryptByPublicKey(String.valueOf(user.getUid()).getBytes(), Constant.PUBLIC_KEY),"utf-8");
                ResponseData responseData = ResponseData.ok();
                responseData.putDataValue("token",token);
                responseData.putDataValue("loginId",user.getUid());
                responseData.putDataValue("avatar",Constant.ADDRESS + userDetails.getAvatar());
                responseData.putDataValue("nickName",userDetails.getNickname());
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
     */
    @RequestMapping(value = "/authen",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "实名认证")
    @SystemControllerLog(description = "实名认证")
    public ResponseData authen(HttpServletRequest request) {
        String name = request.getParameter("name");
        String idCard = request.getParameter("idCard");
        String front = request.getParameter("front");
        String back = request.getParameter("back");
        Integer uid = LoginId.getUid(request);
        ResponseData responseData = ResponseData.ok();
        try {
            appUserService.nameAuthen(uid, name, idCard, front, back);
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

    /**
     * 店家认证
     */
    @RequestMapping(value = "/storeAuthen",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "实名认证")
    @SystemControllerLog(description = "实名认证")
    public ResponseData storeAuthen(HttpServletRequest request){
        ResponseData responseData = ResponseData.ok();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String image = request.getParameter("image");
        Integer uid = LoginId.getUid(request);
        try {
            appUserService.storeAuthen(uid,name,address,phone,image);
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

    /**
     * 获取用户信息
     */
    @LoginRequired
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "获取用户信息")
    @SystemControllerLog(description = "获取用户信息")
    public Result<AppUserInfo> getUserInfo(@RequestParam("username") String username){
        AppUserInfo userInfo = new AppUserInfo();
        Result<AppUserInfo> responseData = Result.ok();
        try {
            User user = userService.getUserForName(username);
            UserDetails userDetails = appUserService.getUserInfo(user.getUid());
            if (userDetails != null) {
                Store store = storeService.get(userDetails.getStorId());
                if (!TextUtils.isEmpty(userDetails.getIdcrad())){
                    userInfo.setReal_name(userDetails.getRealName());
                    userInfo.setBirthday(DateUtil.dateToStr(userDetails.getBirthday()));
                    userInfo.setSex(userDetails.getSex() == 0 ? "男" : "女");
                    userInfo.setIdCrad(StringReplaceUtil.IdReplaceWithStar(userDetails.getIdcrad()));
                }
                if (store != null){
                    userInfo.setStoreState(store.getState());
                    userInfo.setStorename(store.getName());
                    userInfo.setStorephone(store.getIphone());
                    userInfo.setAddress(store.getAddress());
                }

                userInfo.setPhone(StringReplaceUtil.phoneReplaceWithStar(userDetails.getPhone()));
                userInfo.setNickname(userDetails.getNickname());
            }else {
                //userDetails为null
            }
            responseData.setInfo(userInfo);
            return responseData;
        } catch (SysException e) {
            return Result.notFound();
        }
    }

    /**
     * 上传头像
     */
    @RequestMapping(value = "/update/avatar",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "上传头像")
    @SystemControllerLog(description = "上传头像")
    public ResponseData getUpdateUserAvatar(HttpServletRequest request, @RequestParam("avatar") MultipartFile file){
        Integer uid = LoginId.getUid(request);
        try {
            String path = UploadUtil.uploadImg(file, Constant.AVATAR_IMG_URL,"avatar/");
            appUserService.updateAvatar(uid,path);
        } catch (IOException e) {
            return ResponseData.notFound();
        }
        return ResponseData.ok();
    }

    /**
     * 修改用户信息
     */
    @RequestMapping(value = "/update/info",method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "修改用户信息")
    @SystemControllerLog(description = "修改用户信息")
    public ResponseData updateUserInfo(HttpServletRequest request) {
        try {
            Integer uid = LoginId.getUid(request);
            String email = request.getParameter("email");
            String nickname = request.getParameter("nickName");
            UserDetails userDetails = userDetailsService.get(uid);
            if (userDetails != null) {
                userDetails.setEmail(email);
                userDetails.setNickname(nickname);
                userDetailsService.update(userDetails);
                return ResponseData.ok();
            }else {
                return ResponseData.notFound();
            }
        } catch (Exception e) {
            return ResponseData.notFound();
        }
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
            ,@RequestParam(value = "range", defaultValue = "5") double range,@RequestParam(value = "size", defaultValue = "1000") int size
            ,@RequestParam("first") int first){
        ResponseData responseData = ResponseData.ok();
        try {
            Integer uid = LoginId.getUid(request);
            List<AppDevice> devices = deviceService.findPeriphery(longitude,latitude,range,size);
            if (first == 0){
                if (uid != null && uid != 0){
                    UserDetails userDetails = userDetailsService.get(uid);
                    responseData.putDataValue("wallet",null);
                    responseData.putDataValue("credit",userDetails.getCredit());
                }
                File head = new File(Constant.HEAD_JSON_URL);
                responseData.putDataValue("hean",JSON.parse(FileUtils.txt2String(head)));
                File file = new File(Constant.PUSH_JSON_URL);
                String push = FileUtils.txt2String(file);
                responseData.putDataValue("pushInfo", JSON.parse(push));
                responseData.putDataValue("logo",null);
                responseData.putDataValue("wallet",null);
            }
            responseData.putDataValue("deviceList",devices);
            return responseData;
        } catch (Exception e) {
            _log.error(e.toString());
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/navigation", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "获取导航页图片")
    @SystemControllerLog(description = "获取首页信息")
    public Result<String> navigation(){
        try {
            Result<String> result = Result.ok();
            File file = new File(Constant.NAVIGATION_JSON_URL);
            String navigation = FileUtils.txt2String(file);
            result.setInfo(Constant.ADDRESS + navigation.trim());
            return result;
        }catch (Exception e){
            return Result.notFound();
        }
    }
}
