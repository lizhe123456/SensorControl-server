package com.zlcm.server.model;

import java.util.HashMap;
import java.util.Map;

public class ResponseData {

    private final String message;
    private final int code;
    private final Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "success");
    }
    public static ResponseData phoneError() {
        return new ResponseData(601, "验证码有误");
    }
    public static ResponseData codeError() {
        return new ResponseData(602, "验证码获取次数过多");
    }

    public static ResponseData userNull() {
        return new ResponseData(501, "用户不存在");
    }
    public static ResponseData passError() {
        return new ResponseData(502, "密码错误");
    }
    public static ResponseData userFound() {
        return new ResponseData(503, "手机号已注册");
    }
    public static ResponseData userLocked() {
        return new ResponseData(504, "由于不正规使用，用户已停用，请联系客服");
    }
    public static ResponseData deviceNull() {
        return new ResponseData(701, "设配为空");
    }
    public static ResponseData deviceStateError() {
        return new ResponseData(702, "设配不可用");
    }
    public static ResponseData sendError() {
        return new ResponseData(703, "发送错误");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "customer Error");
    }


}

