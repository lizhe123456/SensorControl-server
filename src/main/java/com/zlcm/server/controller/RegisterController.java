package com.zlcm.server.controller;

import com.zlcm.server.model.Result;
import com.zlcm.server.service.UserService;
import com.zlcm.server.service.impl.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.faces.annotation.RequestMap;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @Resource
    UserService userService;

    @RequestMapping(method= RequestMethod.GET)
    public Result register(@RequestParam(value = "username",required=false) String username,
                           @RequestParam(value = "pass",required=false) String pass){
        int code = userService.register(username,pass);
        Result result;
        if (code == UserServiceImpl.SCS_ISREGISTER){
            result = new Result(code,"账号已被注册");
        }else if (code == UserServiceImpl.SCS_SUCCESS){
            result = new Result(code,"注册成功");
        }else {
            result = new Result(404,"未知错误");
        }
        return result;
    }
}
