package com.zlcm.server.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.util.RSAUtils;
import com.zlcm.server.util.jwt.JwtUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Base64;

public class TokenInterceptor implements HandlerInterceptor {

    private static Logger _log = LoggerFactory.getLogger(TokenInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setCharacterEncoding("utf-8");
        String token = request.getHeader("token");
        ResponseData responseData = ResponseData.ok();
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

//        String name = request.getServletPath().toString();
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
//        if (methodAnnotation != null) {
            //token不存在
            if (null != token) {
                User login = JwtUtil.unsign(token, User.class);
                String loginId = request.getHeader("loginId");
                //解密token后的loginId与用户传来的loginId不一致，一般都是token过期
                String uid = null;
                if (null != uid && null != login) {
                    if (uid.equals(String.valueOf(login.getUid()))) {
                        return true;
                    } else {
                        responseMessage(response, response.getWriter(), responseData);
                        return false;
                    }
                } else {
                    responseMessage(response, response.getWriter(), responseData);
                    return false;
                }
            } else {
                responseMessage(response, response.getWriter(), responseData);
                return false;
            }
//        }
//        return true;
    }

    //请求不通过，返回错误信息给客户端
    private void responseMessage(HttpServletResponse response, PrintWriter out, ResponseData responseData) {
        responseData = ResponseData.forbidden();
        response.setContentType("application/json; charset=utf-8");
        String json = JSONObject.toJSONString(responseData);
        out.print(json);
        out.flush();
        out.close();
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        String name = httpServletRequest.getServletPath().toString();
        _log.debug("========"+name+"===>LoginInterceptor postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        String name = httpServletRequest.getServletPath().toString();
        _log.debug("========"+name+"===>LoginInterceptor afterCompletion");
    }
}
