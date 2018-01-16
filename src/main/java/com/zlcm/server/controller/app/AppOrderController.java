package com.zlcm.server.controller.app;

import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.base.BaseController;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.PaySaPi;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.Result;
import com.zlcm.server.model.apprep.AppOrder;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.service.OrderService;
import com.zlcm.server.util.PayUtil;
import com.zlcm.server.util.id.LoginId;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/order")
@Api(value = "APP订单接口",description = "订单")
public class AppOrderController extends BaseController{

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取订单列表")
    @ApiOperation("订单列表(我的发布)")
    @ResponseBody
    public ResponseData getOrderList(HttpServletRequest request){
        ResponseData responseData = ResponseData.ok();
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        Integer uid = LoginId.getUid(request);
        List<AppOrder> list = null;
        try {
            list = orderService.getOrderList(uid,page,size);
            responseData.putDataValue("order",list);
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }
}
