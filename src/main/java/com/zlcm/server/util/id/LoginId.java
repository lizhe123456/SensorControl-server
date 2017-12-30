package com.zlcm.server.util.id;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.util.RSAUtils;

import javax.servlet.http.HttpServletRequest;

public class LoginId {

    public static Integer getUid(HttpServletRequest request) throws Exception {
        if (request.getParameter("loginId") != null) {
//            String uid = new String(RSAUtils.decryptByPrivateKey(request.getHeader("loginId").getBytes(),
//                    Constant.APP_PRIVATE_KEY), "utf-8");
//            return Integer.valueOf(uid);
        }
        return null;
    }
}
