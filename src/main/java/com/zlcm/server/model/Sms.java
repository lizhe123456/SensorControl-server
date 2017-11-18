package com.zlcm.server.model;

/**
 * Created by lizhe on 2017/11/14 0014.
 * 目标定在月亮之上，即使失败，也可以落在众星之间。
 */

public class Sms {


    /**
     * http_status_code : 400
     * code : 22
     * msg : 验证码类短信1小时内同一手机号发送次数不能超过3次
     * detail : 验证码类短信1小时内同一手机号发送次数不能超过3次
     */

    private int http_status_code;
    private int code;
    private String msg;
    private String detail;

    public int getHttp_status_code() {
        return http_status_code;
    }

    public void setHttp_status_code(int http_status_code) {
        this.http_status_code = http_status_code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
