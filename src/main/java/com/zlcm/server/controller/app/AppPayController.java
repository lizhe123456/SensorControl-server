package com.zlcm.server.controller.app;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.base.BaseController;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.PayConstant;
import com.zlcm.server.model.bean.Order;
import com.zlcm.server.model.wx.WechatOrder;
import com.zlcm.server.service.OrderService;
import com.zlcm.server.util.HttpUtils;
import com.zlcm.server.util.PropertiesFileUtil;
import com.zlcm.server.util.RequestUtil;
import com.zlcm.server.util.StringUtils;
import com.zlcm.server.util.id.LoginId;
import com.zlcm.server.util.wx.WXSignUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/pay")
@Api(value = "APP支付接口",description = "支付")
public class AppPayController extends BaseController{

    @Autowired
    AlipayTradeAppPayRequest alipayTradeAppPayRequest;
    @Autowired
    AlipayClient alipayClient;
    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/alipay",method = RequestMethod.POST)
    @SystemControllerLog(description = "支付宝支付")
    @ApiOperation("广告订单支付（支付宝支付）")
    @ResponseBody
    public String aliPay(HttpServletRequest request, String orderNo, Float totalPrice){
        try {
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            bizContent.put("total_amount", totalPrice.toString());
            bizContent.put("subject", "用户"+ LoginId.getUid(request) + ",订单"+orderNo);
            bizContent.put("product_code", "QUICK_MSECURITY_PAY");
            alipayTradeAppPayRequest.setBizContent(bizContent.toString());
            AlipayTradeAppPayResponse response = alipayClient.sdkExecute(alipayTradeAppPayRequest);
            return response.getBody();
        } catch (AlipayApiException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping(value = "/wxpay", method = RequestMethod.POST)
    @SystemControllerLog(description = "微信支付")
    @ApiOperation("广告订单支付（微信）")
    @ResponseBody
    public String wxpay(HttpServletRequest request,float price, String orderId, String info){
        try {
            String orderSn = request.getParameter("orderId");
            Order order = orderService.get(Integer.parseInt(orderId));
            String desc = "";
            String detail = "";
            String openId = String.valueOf(LoginId.getUid(request));
            String ip = RequestUtil.getIpAddr(request);
            String goodSn = order.getAid()+"";

            String amount = String.valueOf(price);
            String type = "APP";
            String randomNum = WXSignUtils.getRandomStringByLength(16);
            JSONObject result = createOrder(detail,desc,openId,ip,goodSn,orderSn,amount,type,randomNum);
            if (result != null){
                return result.toString();
            }else {
                return "";
            }
        } catch (SysException e) {
            e.printStackTrace();
            return "error";
        }
    }


    // 异步通知
    @RequestMapping(value = "/notifyUrl/ali",method = RequestMethod.POST)
    @ResponseBody
    public Object notifyUrl(HttpServletRequest request) throws Exception {
        Map<String, String> parameterMap = RequestUtil.getParameterMap(request);
        // 验签
        boolean signVerified = AlipaySignature.rsaCheckV1(
                parameterMap,
                PropertiesFileUtil.getInstance().get("alipay.alipay_public_key"),
                PropertiesFileUtil.getInstance().get("alipay.charset"),
                PropertiesFileUtil.getInstance().get("alipay.sign_type"));
        if (!signVerified) {
            return PayConstant.FAILED;
        }
        if("TRADE_SUCCESS".equals(parameterMap.get("trade_status"))){
            //付款金额
            String amount = parameterMap.get("buyer_pay_amount");
            //商户订单号
            String out_trade_no = parameterMap.get("out_trade_no");
            //支付宝交易号
            String trade_no = parameterMap.get("trade_no");
            //附加数据
            String passback_params = URLDecoder.decode(parameterMap.get("passback_params"));
            // 再做数据库修改支付状态的操作。。。
            orderService.payOrder(Integer.parseInt(out_trade_no),amount,trade_no,passback_params,0);
        }
        return PayConstant.SUCCESS;
    }


    // 异步通知
    @RequestMapping(value = "/notifyUrl/wx",produces = "text/html;charset=UTF-8",method = RequestMethod.POST)
    @ResponseBody
    public Object wxNotifyUrl(HttpServletRequest request) throws DocumentException, IOException {
        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

        //判断是否支付成功
        if(map.get("return_code").equals("SUCCESS")) {
            /**
             *支付成功之后的业务处理
             */
            String amount = map.get("total_fee");
            String out_trade_no = map.get("out_trade_no");
            String trade_no = map.get("transaction");
            String attach = map.get("attach");
            orderService.payOrder(Integer.parseInt(out_trade_no),amount,trade_no,attach,1);
            return "SUCCESS";
        }
        if (map.get("return_code").equals("FAIL")) {

        /**
         *支付失败后的业务处理
         */


        return "SUCCESS";
      }
        //释放资源
        inputStream.close();
        inputStream = null;

        return "SUCCESS";
    }

    public static synchronized JSONObject createOrder(String detail, String desc, String openid, String ip, String goodSn, String orderSn, String amount, String type,String randomNum) {
        JSONObject result = new JSONObject();
        double relAmount = 0;// 对应微信支付的真实数目
        try {
            //微信接收的金额单位是分，如果客户端不转换，服务端要讲金额转换成分
            relAmount = Double.parseDouble(amount) * 100;
        } catch (Exception e) {
            return result;
        }

        if (relAmount == 0) {
            //微信支付的支付金额必须为大于0的int类型，单位为分
            return result;
        }

        if (!("JSAPI".equalsIgnoreCase(type) || "NATIVE".equalsIgnoreCase(type) || "APP".equalsIgnoreCase(type))) {

            return result;
        }

        // 获取系统配置信息
        String wx_order = PropertiesFileUtil.getInstance().get("wxpay.url");//获取统一下单接口地址
        String mchappid = PropertiesFileUtil.getInstance().get("wxpay.appid");// 商户appid
        String mchid = PropertiesFileUtil.getInstance().get("wxpay.mchId");// 商户ID
        String wx_callback = PropertiesFileUtil.getInstance().get("wx_callback");// 获取微信支付回调接口
        String wx_key = PropertiesFileUtil.getInstance().get("wx_key");//微信商户后台设置的key
        String app_mchid = PropertiesFileUtil.getInstance().get("app_mchid");//APP调起微信支付的商户ID
        String app_mchappid = PropertiesFileUtil.getInstance().get("app_mchappid");//APP调起微信的APPID

        if (StringUtils.isBlank(wx_order) || StringUtils.isBlank(mchappid)|| StringUtils.isBlank(mchid) || StringUtils.isBlank(wx_callback)) {
            return result;
        }

        // 发送报文模板,其中部分字段是可选字段
        String xml = "" +
                "<xml>" +
                "<appid>APPID</appid>" +//公众号ID
                "<device_info>WEB</device_info>" +//设备信息
                "<detail>DETAIL</detail>" +//商品详情
                "<body>BODY</body>" +//商品描述
                "<mch_id>MERCHANT</mch_id>" +//微信给的商户ID
                "<nonce_str>FFHH</nonce_str>" +//32位随机字符串
                "<notify_url><![CDATA[URL_TO]]></notify_url>" +//微信回调信息通知页面
                "<openid>UserFrom</openid>" +//支付的用户ID
                "<fee_type>CNY</fee_type>" +//支付货币
                "<spbill_create_ip>IP</spbill_create_ip>" +//用户IP
                "<time_start>START</time_start>" +//订单开始时间
                "<time_expire>STOP</time_expire>" +//订单结束时间
                "<goods_tag>WXG</goods_tag>" +//商品标记
                "<product_id>GOODID</product_id>" +//商品ID
                "<limit_pay>no_credit</limit_pay>" +//支付范围，默认不支持信用卡支付
                "<out_trade_no>PAY_NO</out_trade_no>" +//商城生成的订单号
                "<total_fee>TOTAL</total_fee>" +//总金额
                "<trade_type>TYPE</trade_type>" +//交易类型，JSAPI，NATIVE，APP，WAP
                "<sign>SIGN</sign>" +//加密字符串
                "</xml>";

        //生成订单起始时间，订单7天内有效
        DateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
        String start_time = df.format(new Date());
        String stop_time = df.format(new Date().getTime() + 7 * 24 * 60 * 60 * 1000);

        //xml数据封装

        //APP调起的时候，可能和公众号调起的商户号是不同的，所以需要分开设置
        if ("APP".equalsIgnoreCase(type)) {
            xml = xml.replace("MERCHANT", app_mchid);
            xml = xml.replace("APPID", app_mchappid);
        } else {
            xml = xml.replace("MERCHANT", mchid);
            xml = xml.replace("APPID", mchappid);
        }
        xml = xml.replace("FFHH", randomNum);
        xml = xml.replace("DETAIL", detail);
        xml = xml.replace("BODY", desc);
        xml = xml.replace("URL_TO", wx_callback);
        xml = xml.replace("IP", ip);
        xml = xml.replace("START", start_time);
        xml = xml.replace("STOP", stop_time);
        xml = xml.replace("GOODID", goodSn);
        xml = xml.replace("PAY_NO", orderSn);
        xml = xml.replace("TOTAL", (int) relAmount + "");
        xml = xml.replace("TYPE", type);
        if ("NATIVE".equalsIgnoreCase(type) || "APP".equalsIgnoreCase(type)) {
            xml = xml.replace("<openid>UserFrom</openid>", openid);
        } else {
            xml = xml.replace("UserFrom", openid);
        }
        // 4、加密
        Map<String, String> map = new HashMap<String, String>();
        map.put("device_info", "WEB");
        map.put("detail", detail);
        map.put("body", desc);
        if ("APP".equalsIgnoreCase(type)) {
            map.put("mch_id", app_mchid);
            map.put("appid", app_mchappid);
        } else {

            map.put("mch_id", mchid);
            map.put("appid", mchappid);
        }

        map.put("nonce_str", randomNum);
        map.put("notify_url", wx_callback);
        map.put("fee_type", "CNY");
        map.put("spbill_create_ip", ip);
        map.put("time_start", start_time);
        map.put("time_expire", stop_time);
        map.put("goods_tag", "WXG");
        map.put("product_id", goodSn);
        map.put("limit_pay", "no_credit");
        map.put("out_trade_no", orderSn);
        map.put("total_fee", (int) relAmount + "");
        map.put("trade_type", type);

        String sign = WXSignUtils.signature(map, wx_key);
        xml = xml.replace("SIGN", sign);

        // 请求
        String response = "";
        try {
            //注意，此处的httputil一定发送请求的时候一定要注意中文乱码问题，中文乱码问题会导致在客户端加密是正确的，可是微信端返回的是加密错误
            response = HttpUtils.post(wx_order, xml);
        } catch (Exception e) {
            return result;
        }
        //处理请求结果
        XStream s = new XStream(new DomDriver());
        s.alias("xml", WechatOrder.class);
        WechatOrder order = (WechatOrder) s.fromXML(response);

        if ("SUCCESS".equals(order.getReturn_code()) && "SUCCESS".equals(order.getResult_code())) {
            //支付成功的处理逻辑

        } else {
            //支付失败的处理逻辑

        }

        HashMap<String, String> back = new HashMap<String, String>();

        //生成客户端调时需要的信息对象

        //APP调起的时候,请注意，安卓端不能用驼峰法，所有的key必须使用小写
        String time = Long.toString(System.currentTimeMillis());
        back.put("appid", app_mchappid);
        back.put("timestamp", time);
        back.put("partnerid", app_mchid);
        back.put("noncestr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        back.put("prepayid", order.getPrepay_id());
        back.put("package", "Sign=WXPay");
        String sign2 = WXSignUtils.signature(back, wx_key);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("appid", app_mchappid);
        jsonObject.put("timestamp", time);
        jsonObject.put("partnerid", app_mchid);
        jsonObject.put("noncestr", "5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
        jsonObject.put("prepayid", order.getPrepay_id());
        //jsonObject.put("package", "Sign=WXPay");
        jsonObject.put("sign", sign2);
        result.put("status", "success");
        result.put("msg", "下单成功");
        result.put("obj", jsonObject);

        return result;

    }
}
