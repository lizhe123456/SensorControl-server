package com.zlcm.server.controller;

import com.zlcm.server.service.UserService;
import org.apache.log4j.net.SocketServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping("/showUser.do")
    public void selectUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        String userId = Integer.parseInt(request.getParameter("id"));
//        UserUcenter user = this.userService.getUser(userId);
//        ObjectMapper mapper = new ObjectMapper();
//        response.getWriter().write(mapper.writeValueAsString(user));
//        response.getWriter().close();
    }

    @RequestMapping("/sendCodeSMS")
    @ResponseBody
    public void sendCodeSMS(String smstel,HttpServletRequest request,HttpSession session){

        String message = "发送成功";
    }


}
