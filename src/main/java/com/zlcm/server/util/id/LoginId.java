package com.zlcm.server.util.id;


import org.apache.http.util.TextUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginId {

    public static Integer getUid(HttpServletRequest request) throws Exception {
        if (!TextUtils.isEmpty(request.getHeader("loginId"))) {
            String uid = request.getHeader("loginId");
            return Integer.valueOf(uid);
        }
        return null;
    }
}
