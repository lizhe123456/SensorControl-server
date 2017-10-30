package com.zlcm.server.controller;

import com.zlcm.server.model.Result;
import com.zlcm.server.model.user.LoginOauth;
import com.zlcm.server.model.user.UserUcenter;
import com.zlcm.server.service.UserService;
import com.zlcm.server.service.impl.UserServiceImpl;
import com.zlcm.server.util.jwt.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Resource
    UserService userService;
    @RequestMapping(method= RequestMethod.GET)
    public LoginOauth login(@RequestParam(value = "username",required=false) String username,
                            @RequestParam(value = "pass",required=false) String pass){
        LoginOauth result;
        UserUcenter userUcenter = userService.getLogin(username,pass);
        if (userUcenter == null){
            result = new LoginOauth(101,"用户名不存在，请先注册");
            return result;
        }
        int code = userUcenter.getCode();
        if (code == UserServiceImpl.SCS_SUCCESS){
            result = new LoginOauth(code, "登录成功",userUcenter.getToken());
            return result;
        }else if (code == UserServiceImpl.SCS_USER_ERROR){
            result = new LoginOauth(code, "账号或密码错误");
            return result;
        } else {
            result = new LoginOauth(404,"未知错误");
            return result;
        }
    }


}
