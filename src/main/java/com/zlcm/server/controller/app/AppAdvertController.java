package com.zlcm.server.controller.app;

import com.alibaba.fastjson.JSON;
import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.ResponseData;
import com.zlcm.server.model.apprep.AppAdvert;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.service.AdvertService;
import com.zlcm.server.service.DeviceService;
import com.zlcm.server.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Controller
@Api(value = "APP广告接口",description="广告")
@RequestMapping("/api/advert")
public class AppAdvertController {

    @Autowired
    AdvertService advertService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/hot",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取热门广告信息")
    @ApiOperation("热门广告")
    @ResponseBody
    public ResponseData getHotInfo(HttpServletRequest request){
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        ResponseData responseData;
        try {
            List<AppAdvert> list = advertService.getAppHotList(page,size);
            responseData = ResponseData.ok();
            responseData.putDataValue("hot",list);
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/recommend",method = RequestMethod.POST)
    @SystemControllerLog(description = "获取推荐广告")
    @ApiOperation("推荐广告")
    @ResponseBody
    public ResponseData getRecommendInfo(HttpServletRequest request){
        Integer page = Integer.parseInt(request.getParameter("page"));
        Integer size = Integer.parseInt(request.getParameter("size"));
        ResponseData responseData;
        try {
            List<AppAdvert> list = advertService.getAppRecommend(page,size);
            responseData = ResponseData.ok();
            responseData.putDataValue("hot",list);
            return responseData;
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

    @RequestMapping(value = "/fordevice", method = RequestMethod.POST)
    @SystemControllerLog(description = "获取显示屏广告")
    @ApiOperation("显示屏广告")
    @ResponseBody
    public ResponseData getPeripheryDetailsInfo(HttpServletRequest request){
        ResponseData responseData = ResponseData.ok();
        try {
            int did = Integer.parseInt(request.getParameter("did"));
            int page = Integer.parseInt(request.getParameter("page"));
            int size = Integer.parseInt(request.getParameter("size"));
            int type = Integer.parseInt(request.getParameter("type"));
            size = size == 0 ? 5 : size;
            List<AppAdvert> list = advertService.findAdvertForDid(did, page, size);
            if (type == 0) {
                int visitorsFlowrate = deviceService.getHouseholdNum(did);
                int hot = 0;
                for (int i = 0; i < list.size(); i++) {
                    hot =+ list.get(i).getHits();
                }
                responseData.putDataValue("hot", hot);
                responseData.putDataValue("visitorsFlowrate", visitorsFlowrate);
                BigDecimal bg = new BigDecimal( 5.0 * ((double) visitorsFlowrate / 1000.0));
                double charging = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                responseData.putDataValue("charging",charging);
                responseData.putDataValue("advert", list);
                return responseData;
            }else {
                responseData.putDataValue("advert", list);
                return responseData;
            }
        } catch (SysException e) {
            return ResponseData.notFound();
        }
    }

//    @RequestMapping(value = "/submit", method = RequestMethod.POST)
//    @SystemControllerLog(description = "App上传广告生成订单")
//    @ApiOperation("")
//    @ResponseBody
//    public ResponseData getAppAdvert(HttpServletRequest request,@RequestParam("advert") MultipartFile file, @RequestParam("desc") String desc
//            ,@RequestParam("duration") long duration, @RequestParam("address") String address){
//        String devices = request.getParameter("device");
//        List<Integer> d = (List<Integer>) JSON.parse(devices);
//        Order order = new Order();
//
//        Advert advert = new Advert();
//        advert.setAddress();
//        advert.setIphone();
//        advert.setTextInfo();
//        advert.setDid();
//
//        int aid = advertService.save(advert);
//        advert.setDuration(duration);
//        order.setCreateTime(new Date());
//        order.setDuration(duration);
//        order.setAid(aid);
//        order.getUid();
//        orderService.save();
//    }
}
