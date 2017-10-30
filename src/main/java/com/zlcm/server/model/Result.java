package com.zlcm.server.model;

public class Result {

    private int stateCode;
    private String message;

    public Result(int stateCode, String message){
        super();
        this.stateCode = stateCode;
        this.message = message;
    }

    public int getStateCode() {
        return stateCode;
    }

    public void setStateCode(int stateCode) {
        this.stateCode = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
