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
import com.zlcm.server.util.sms.SmsApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping(value = "/pay",method = RequestMethod.POST)
    @SystemControllerLog(description = "订单支付")
    @ApiOperation("广告订单支付")
    @ResponseBody
    public Result<String> pay(HttpServletRequest request) {
        Integer uid = LoginId.getUid(request);
        Integer oid = Integer.parseInt(request.getParameter("order"));
        String istype = request.getParameter("type");
        Result<String> responseData = Result.ok();
        try {
            Order order = orderService.get(oid);
            float price = order.getPrice();
            Map<String, String> remoteMap = new HashMap<String, String>();
            remoteMap.put("price", String.valueOf(price));
            if (istype.equals("0")){
                istype = "1";
            }else if (istype.equals("1")){
                istype = "2";
            }
            remoteMap.put("istype", istype);
            remoteMap.put("orderid", PayUtil.getOrderIdByUUId());
            remoteMap.put("orderuid", "uid: "+uid);
            remoteMap.put("goodsname", "智领广告");
            String json = PayUtil.post(PayUtil.payOrder(remoteMap));
            responseData.setInfo(json);
            return responseData;
        }catch (SysException e) {
            return Result.notFound();
        }catch (Exception e){
            return Result.notFound();
        }
    }

    @RequestMapping("/notifyPay")
    @ResponseBody
    public void notifyPay(HttpServletRequest request, HttpServletResponse response, PaySaPi paySaPi) {
        // 保证密钥一致性
        if (PayUtil.checkPayKey(paySaPi)) {
            //做自己想做的

        } else {
            //该怎么做就怎么做
        }
    }

    @RequestMapping("/returnPay")
    @ResponseBody
    public ResponseData returnPay(HttpServletRequest request, HttpServletResponse response, String orderid) {
        boolean isTrue = false;
        // 根据订单号查找相应的记录:根据结果跳转到不同的页面
        if (isTrue) {
            return ResponseData.ok();
        } else {
            return ResponseData.no();
        }
    }

}
