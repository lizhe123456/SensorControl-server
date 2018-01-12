package com.zlcm.server.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.zlcm.server.model.PaySaPi;
import com.zlcm.server.util.sms.SmsApi;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PayUtil {

	private static Logger logger = LogManager.getLogger();

	public static String UID = "5c64226bafafcd4dd1416603";

	public static String NOTIFY_URL = "http://192.168.1.201:8080/api/order/notifyPay";

	public static String RETURN_URL = "http://192.168.1.201:8080/api/order/returnPay";

	public static String BASE_URL = "https://pay.paysapi.com?format=json";

	public static String TOKEN = "da2d83ddd1643f612e740bf66ee11113";

	public static Map<String, String> payOrder(Map<String, String> remoteMap) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("uid", UID);
		paramMap.put("notify_url", NOTIFY_URL);
		paramMap.put("return_url", RETURN_URL);
		paramMap.putAll(remoteMap);
		paramMap.put("key", getKey(paramMap));
		return paramMap;
	}

	public static String post(Map<String,String> map){
		return SmsApi.post(BASE_URL,map);
	}

	public static String getKey(Map<String, String> remoteMap) {
		String key = "";
		if (null != remoteMap.get("goodsname")) {
			key += remoteMap.get("goodsname");
		}
		if (null != remoteMap.get("istype")) {
			key += remoteMap.get("istype");
		}
		if (null != remoteMap.get("notify_url")) {
			key += remoteMap.get("notify_url");
		}
		if (null != remoteMap.get("orderid")) {
			key += remoteMap.get("orderid");
		}
		if (null != remoteMap.get("orderuid")) {
			key += remoteMap.get("orderuid");
		}
		if (null != remoteMap.get("price")) {
			key += remoteMap.get("price");
		}
		if (null != remoteMap.get("return_url")) {
			key += remoteMap.get("return_url");
		}
		key += TOKEN;
		if (null != remoteMap.get("uid")) {
			key += remoteMap.get("uid");
		}
		return MD5Util.encryption(key);
	}

	public static boolean checkPayKey(PaySaPi paySaPi) {
		String key = "";
		if (!StringUtils.isBlank(paySaPi.getOrderid())) {
			logger.info("支付回来的订单号：" + paySaPi.getOrderid());
			key += paySaPi.getOrderid();
		}
		if (!StringUtils.isBlank(paySaPi.getOrderuid())) {
			logger.info("支付回来的支付记录的ID：" + paySaPi.getOrderuid());
			key += paySaPi.getOrderuid();
		}
		if (!StringUtils.isBlank(paySaPi.getPaysapi_id())) {
			logger.info("支付回来的平台订单号：" + paySaPi.getPaysapi_id());
			key += paySaPi.getPaysapi_id();
		}
		if (!StringUtils.isBlank(paySaPi.getPrice())) {
			logger.info("支付回来的价格：" + paySaPi.getPrice());
			key += paySaPi.getPrice();
		}
		if (!StringUtils.isBlank(paySaPi.getRealprice())) {
			logger.info("支付回来的真实价格：" + paySaPi.getRealprice());
			key += paySaPi.getRealprice();
		}
		logger.info("支付回来的Key：" + paySaPi.getKey());
		key += TOKEN;
		logger.info("我们自己拼接的Key：" + MD5Util.encryption(key));
		return paySaPi.getKey().equals(MD5Util.encryption(key));
	}

	public static String getOrderIdByUUId() {
		int machineId = 1;// 最大支持1-9个集群机器部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {// 有可能是负数
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0;d 代表参数为正数型
		return machineId + String.format("%01d", hashCodeV);
	}

}
