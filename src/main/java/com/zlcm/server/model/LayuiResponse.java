package com.zlcm.server.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lizhe on 2017/12/9 0009.
 * 目标定在月亮之上，即使失败，也可以落在众星之间。
 */
public class LayuiResponse {
    private final int code;
    private final String msg;
    private int count;
    private final List<Object> data = new ArrayList<>();

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getCount() {
        return count;
    }

    public List<Object> getData() {
        return data;
    }

    public LayuiResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public LayuiResponse addDataValue(Object value) {
        data.addAll((List<?>) value);
        count = data.size();
        return this;
    }


    public static LayuiResponse ok() {
        return new LayuiResponse(200, "success");
    }

    public static LayuiResponse customerError() {
        return new LayuiResponse(1001, "customer Error");
    }
}
