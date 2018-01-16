package com.zlcm.server.util.wx;

import com.zlcm.server.util.StringUtils;
import java.security.MessageDigest;
import java.util.*;

public class WXSignUtils {

    /**
     * 微信支付加密工具
     */
    public static String signature(Map<String, String> map, String key) {
        Set<String> keySet = map.keySet();
        String[] str = new String[map.size()];
        StringBuilder tmp = new StringBuilder();
        // 进行字典排序
        str = keySet.toArray(str);
        Arrays.sort(str);
        for (int i = 0; i < str.length; i++) {
            String t = str[i] + "=" + map.get(str[i]) + "&";
            tmp.append(t);
        }
        if (StringUtils.isNotBlank(key)) {
            tmp.append("key=" + key);
        }
        String tosend = tmp.toString();
        MessageDigest md = null;
        byte[] bytes = null;
        try {

            md = MessageDigest.getInstance("MD5");
            bytes = md.digest(tosend.getBytes("utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String singe = StringUtils.byteToStr(bytes);
        return singe.toUpperCase();

    }

    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}
