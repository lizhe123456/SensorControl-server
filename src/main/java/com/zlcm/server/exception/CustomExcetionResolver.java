package com.zlcm.server.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 全局异常处理器
 */
public class CustomExcetionResolver implements HandlerExceptionResolver{
    /**
     * 系统抛出的异常
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @return
     */
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //解析出异常类型
        CustomException customExcetion = null;
        if (e instanceof CustomException){
            customExcetion = (CustomException) e;
        }else {
            customExcetion = new CustomException("未知错误");
        }
        String message = customExcetion.getMessage();
        ModelAndView modelAndView = new ModelAndView();
        //将错误信息传到页面
        modelAndView.addObject("message",message);
        //指向错误页面
        modelAndView.setViewName("error");
        return modelAndView;
    }

}
