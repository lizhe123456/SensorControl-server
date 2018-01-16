package com.zlcm.server.model;

public class AdminResponse<T> {

    private int code;
    private String msg;
    private int count;
    T data;

    public AdminResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public static AdminResponse ok() {
        return new AdminResponse(200, "success");
    }

    public static AdminResponse notFound() {
        return new AdminResponse(404, "Not Found");
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
