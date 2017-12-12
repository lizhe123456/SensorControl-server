//package com.zlcm.server.interceptor;
//
//import com.alibaba.fastjson.JSON;
//import org.apache.commons.lang.StringUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.lang.reflect.Method;
//
//public class LoginInterceptor extends HandlerInterceptorAdapter{
//
//    @WebTokenService
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        final HttpServletRequest httpServletRequest = (HttpServletRequest) handler;
//        final HttpServletResponse httpResponse = (HttpServletResponse) response;
//        final String authHeaderVal = request.getHeader("token");
//
//        if (!(handler instanceof HandlerMethod)) {
//            return true;
//        }
//
//        String name = request.getServletPath().toString();
//
//        HandlerMethod handlerMethod = (HandlerMethod) handler;
//        Method method = handlerMethod.getMethod();
//
//        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
//        if (methodAnnotation != null) {
//            if (StringUtils.isNotEmpty(authHeaderVal)) {
//                try {
//                    WebToken webToken = tokenService.getToken(authHeaderVal);
//                    int userId = Integer.valueOf(webToken.getId());
//                    System.out.println("========"+name+"===>LoginInterceptor preHandle 验证成功放行");
//                    return true;
//                } catch (Exception e) {
//                    response.setCharacterEncoding("UTF-8");
//                    response.getWriter().write(JSON.toJSONString(new JsonResult(ResultCode.INVALID_AUTHCODE, "登录已过期，请重新登录！")));
//                    System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，登录已过期，请重新登录！");
//                    return false;
//                }
//            } else {
//                response.setCharacterEncoding("UTF-8");
//                response.getWriter().write(JSON.toJSONString(new JsonResult(ResultCode.NOT_LOGIN,"尚未登录")));
//                System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，尚未登录！");
//                return false;
//            }
//        }
//        System.out.println("========"+name+"===>LoginInterceptor preHandle 没加验证注解放行");
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//        String name = request.getServletPath().toString();
//        System.out.println("========"+name+"===>LoginInterceptor postHandle");
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        String name = request.getServletPath().toString();
//        System.out.println("========"+name+"===>LoginInterceptor afterCompletion");
//    }
//
//
//}
