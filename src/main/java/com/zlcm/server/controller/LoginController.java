package com.zlcm.server.controller;

import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.user.LoginOauth;
import com.zlcm.server.model.user.UserUcenter;
import com.zlcm.server.service.UserService;
import com.zlcm.server.service.impl.UserServiceImpl;
import com.zlcm.server.util.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class LoginController {

    @Resource
    UserService userService;
    @RequestMapping(value = "login",method= RequestMethod.GET, produces = "application/json; charset=utf-8")
    public @ResponseBody ResponseData login(@RequestParam(value = "username",required=false) String username,
                              @RequestParam(value = "pass",required=false) String pass){
        ResponseData result = null;
        UserUcenter userUcenter = userService.getLogin(username,pass);
        if (userUcenter == null){
            result = new ResponseData(101,"用户名不存在，请先注册");
            return result;
        }
        int code = userUcenter.getCode();
        if (code == UserServiceImpl.SCS_SUCCESS){
            result = ResponseData.ok();
            String token = JwtUtil.sign(userUcenter,30l*60L*24L*1000L);
            result = new ResponseData(code, "登录成功");
            result.putDataValue("token",token);
            result.putDataValue("loginId", userUcenter.getUid());
            return result;
        }else if (code == UserServiceImpl.SCS_USER_ERROR){
            result = new ResponseData(code, "账号或密码错误");
            return result;
        } else {
            result = new ResponseData(404,"未知错误");
            return result;
        }
    }


}
