package com.zlcm.server.util.id;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Created by Sloong on 2015/12/10.
 */
public class UUIDTools {

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return Numbers.toString(hi | (val & (hi - 1)), Numbers.MAX_RADIX)
                .substring(1);
    }

    /**
     * 以62进制（字母加数字）生成19位UUID，最短的UUID
     *
     * @return
     */
    public static String uuid() {
        UUID uuid = UUID.randomUUID();
        StringBuilder sb = new StringBuilder();
        sb.append(digits(uuid.getMostSignificantBits() >> 32, 8));
        sb.append(digits(uuid.getMostSignificantBits() >> 16, 4));
        sb.append(digits(uuid.getMostSignificantBits(), 4));
        sb.append(digits(uuid.getLeastSignificantBits() >> 48, 4));
        sb.append(digits(uuid.getLeastSignificantBits(), 12));
        return sb.toString();
    }

    /**
     * 自动生成32位的UUid，对应数据库的主键id进行插入用。
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成图片文件名
     *
     */
    public static String getImgName(){
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++)
        {
            String str      = uuid.substring(i * 4, i * 4 + 4);
            int strInteger  = Integer.parseInt(str, 16);
            stringBuffer.append(chars[strInteger % 0x3E]);
        }

        return stringBuffer.toString();
    }
    /**
     * 生成token
     */
    public static String getToken(){
        StringBuffer stringBuffer = new StringBuffer();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        for (int i = 0; i < 8; i++) {
            String str      = uuid.substring(i * 4, i *  4 + 4);
            int strInteger  = Integer.parseInt(str, 16);
            stringBuffer.append(chars[strInteger % 0x3E]);
        }
        String token = null;
        try {
            token = Base64.getEncoder().encodeToString(stringBuffer.toString().getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String[] chars = new String[]{
            "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z",
            "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V","W", "X", "Y", "Z"
    };

    public static void main(String[] arg){
        while (true) {
            String u = getToken();
            System.out.println(u);
            System.out.println(u.length());
        }

    }


}
