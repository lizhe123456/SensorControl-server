package com.zlcm.admin;

import com.zlcm.server.base.BaseController;
import com.zlcm.server.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseAdminController {

    private final static Logger _log = LoggerFactory.getLogger(BaseController.class);

    @ExceptionHandler
    public ResponseData exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e){

        _log.error("统一异常处理：",e);
        request.setAttribute("ex",e);
        if (null != request.getHeader("X-Requested-With") && request.getHeader("X-Requested-With").equalsIgnoreCase("XMLHttpRequest")) {
            request.setAttribute("requestHeader", "ajax");
        }

        return ResponseData.notFound();
    }


}
