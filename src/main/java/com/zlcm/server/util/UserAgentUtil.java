package com.zlcm.server.util;

import nl.bitwalker.useragentutils.Browser;
import nl.bitwalker.useragentutils.OperatingSystem;
import nl.bitwalker.useragentutils.UserAgent;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAgentUtil {

    public static String android="Android";
    public static String iphone="iPhone";
    public static String ipad="iPad";
    public static String noDevice="未知设备";

    //获取用户UA信息
    public static String getUaInfo(HttpServletRequest request){

        if(null == request) return "";
        return request.getHeader("User-Agent");
    }

    //获取用户Browser信息
    public static String getBrowser(String ua){

        if(null == ua) return "";
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        Browser browser = userAgent.getBrowser();
        return browser.toString();
    }


    //获取用户os信息
    public static String getOS(String ua){

        if(null == ua) return noDevice;
        UserAgent userAgent = UserAgent.parseUserAgentString(ua);
        OperatingSystem os = userAgent.getOperatingSystem();
        return os.toString();
    }

    public static String getIphone(String ua){
        Pattern pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?(Build)?/");
        Matcher matcher = pattern.matcher(ua);
        String model = null;
        if(matcher.find()) {
            model = matcher.group(1).trim();
        }
        return model;
    }

    //获取移动用户操作系统
    public static String getMobileOS(String userAgent){
        if (userAgent.contains(android)) {
            return android;
        }else if (userAgent.contains(iphone)){
            return iphone;
        }else if (userAgent.contains(ipad)){
            return ipad;
        }else {
            return "others";
        }
    }

    //获取用户手机型号
    public static String getPhoneModel(String userAgent){

        if(null == userAgent || "" == userAgent) return noDevice;

        String OS=UserAgentUtil.getMobileOS(userAgent);
        if (OS.equals(android)) {
            String rex="[()]+";
            String[] str=userAgent.split(rex);
            str = str[1].split("[;]");
            String[] res=str[str.length-1].split("Build/");
            return res[0];
        }else if (OS.equals(iphone)) {
            String[] str=userAgent.split("[()]+");
            String res="iphone"+str[1].split("OS")[1].split("like")[0];
            return res;
        }else if (OS.equals(ipad)) {
            return ipad;
        }else {
            return getOS(userAgent);
        }


    }

}
