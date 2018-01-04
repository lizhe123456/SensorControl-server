package com.zlcm.server.model;


public class Result<T>{
    private final String message;
    private final int code;
    private T info;

    public Result(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public static Result ok() {
        return new Result("success", 200);
    }

    public static Result notFound() {
        return new Result("Not Found", 404);
    }
}
