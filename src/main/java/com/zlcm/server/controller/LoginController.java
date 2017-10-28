package com.zlcm.server.controller;

import com.zlcm.server.model.Result;
import com.zlcm.server.service.UserService;
import com.zlcm.server.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping("/login")
@RestController
public class LoginController {

    @Resource
    UserService userService;
    @RequestMapping(method= RequestMethod.GET)
    public Result login(@RequestParam(value = "username",required=false) String username,
                        @RequestParam(value = "pass",required=false) String pass){
        Result result;
        int code = userService.getLogin(username,pass);
        if (code == UserServiceImpl.SCS_SUCCESS){
            result = new Result(code, "登录成功");
            return result;
        }else if (code == UserServiceImpl.SCS_USER_ERROR){
            result = new Result(code, "账号或密码错误");
            return result;
        }else if (code == UserServiceImpl.SCS_ISREGISTER){
            result = new Result(code,"用户名不存在，请先注册");
            return result;
        }else {
            result = new Result(404,"未知错误");
            return result;
        }
    }


}
