package com.zlcm.server.model.user;

import com.zlcm.server.model.Result;

/**
 * 登录
 */
public class LoginOauth extends Result{
    private String token;

    public LoginOauth(int stateCode, String message,String token) {
        super(stateCode, message);
        this.token = token;
    }
    public LoginOauth(int stateCode, String message) {
        super(stateCode, message);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
