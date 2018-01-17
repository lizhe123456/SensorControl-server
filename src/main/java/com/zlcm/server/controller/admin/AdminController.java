package com.zlcm.server.controller.admin;

import com.zlcm.admin.BaseAdminController;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.AdminResponse;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.admin.AdminAdvert;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.service.OrderService;
import com.zlcm.server.service.SystemService;
import com.zlcm.server.util.IPUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/manage")
@Api(value = "后台管理", description = "后台管理")
public class AdminController extends BaseAdminController {

    @Autowired
    OrderService orderService;
    @Autowired
    SystemService systemService;

    @RequestMapping(value = "/auditInfo", method = RequestMethod.GET)
    @ApiOperation("待审列表")
    @ResponseBody
    public AdminResponse<List<AdminAdvert>> getOrderInfo(HttpServletRequest request){
        try {
            List<AdminAdvert> list = new ArrayList<>();
            AdminResponse<List<AdminAdvert>> adminResponse = AdminResponse.ok();
            String ip = IPUtils.getIpAddr(request);
            Integer page = Integer.parseInt(request.getParameter("offset"));
            Integer size = Integer.parseInt(request.getParameter("limit"));
            adminResponse.setData(systemService.auditingInfo(page,size) == null ? list : systemService.auditingInfo(page,size));
            return adminResponse;
        } catch (SysException e) {
            e.printStackTrace();
            return AdminResponse.notFound();
        }
//        if (ip.equals("")){
//            return "";
//        }else {
//            return "404";
//        }

    }

    @RequestMapping(value = "/auditing", method = RequestMethod.POST)
    @ApiOperation("审核通过发送")
    @ResponseBody
    public ResponseData auditing(HttpServletRequest request){
        Integer aid = Integer.valueOf(request.getParameter("aid"));
        Integer state = Integer.valueOf(request.getParameter("state"));
        String auditingInfo = request.getParameter("auditingInfo");
        systemService.auditing(aid,state,auditingInfo);
        return ResponseData.ok();
    }

    @RequestMapping(value = "/user/list",method = RequestMethod.GET)
    @ApiOperation("用户列表")
    @ResponseBody
    public AdminResponse<List<User>> userList(HttpServletRequest request){
        try {
            AdminResponse<List<User>> adminResponse = AdminResponse.ok();
            Integer page = Integer.parseInt(request.getParameter("offset"));
            Integer size = Integer.parseInt(request.getParameter("limit"));
            List<User> list = systemService.userList(page,size);
            adminResponse.setCount(list.size());
            adminResponse.setData(list);
            return adminResponse;
        } catch (SysException e) {
            e.printStackTrace();
            return AdminResponse.notFound();
        }
    }

}
