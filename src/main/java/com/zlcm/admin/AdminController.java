package com.zlcm.admin;

import com.zlcm.server.service.OrderService;
import com.zlcm.server.service.SystemService;
import com.zlcm.server.util.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class AdminController extends BaseAdminController{

    @Autowired
    OrderService orderService;
    @Autowired
    SystemService systemService;

    @RequestMapping(value = "/auditInfo", method = RequestMethod.POST)
    @ApiOperation("待审列表")
    public String getOrderInfo(HttpServletRequest request){
        String ip = IPUtils.getIpAddr(request);
        if (ip.equals("")){
            return "";
        }else {
            return "404";
        }
    }

    @RequestMapping(value = "/auditing", method = RequestMethod.POST)
    @ApiOperation("审核通过发送")
    public String auditing(HttpServletRequest request){
        Integer aid = Integer.valueOf(request.getParameter("aid"));
        Integer state = Integer.valueOf(request.getParameter("state"));
        String auditingInfo = request.getParameter("auditingInfo");
        systemService.auditing(aid,state,auditingInfo);
        return "404";
    }
}
