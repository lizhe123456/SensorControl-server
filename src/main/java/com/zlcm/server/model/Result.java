package com.zlcm.server.model;

public class Result {

    private int code;
    private String message;

    public Result(int code, String message){
        super();
        this.code = code;
        this.message = message;
    }

    public int getStateCode() {
        return code;
    }

    public void setStateCode(int stateCode) {
        this.code = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
