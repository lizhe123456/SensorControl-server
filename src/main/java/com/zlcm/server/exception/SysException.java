package com.zlcm.server.exception;

public class SysException extends Exception{

    int code;

    public SysException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
