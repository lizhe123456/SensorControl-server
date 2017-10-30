package com.zlcm.server.authorization.interceptor;

import com.zlcm.server.util.LogUtil;
import org.apache.commons.logging.Log;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.handler.Handler;

/**
 * 拦截器
 */
public class ApiInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("拦截了");
        LogUtil.getInstance(ApiInterceptor.class).d("拦截了");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        System.out.println("拦截了");
        LogUtil.getInstance(ApiInterceptor.class).d("拦截了");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        LogUtil.getInstance(ApiInterceptor.class).d("拦截了");
    }

}
