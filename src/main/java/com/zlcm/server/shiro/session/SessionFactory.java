package com.zlcm.server.shiro.session;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.web.session.mgt.WebSessionContext;

import javax.servlet.http.HttpServletRequest;

public class SessionFactory implements org.apache.shiro.session.mgt.SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {
        com.zlcm.server.shiro.session.Session session = new com.zlcm.server.shiro.session.Session();
        if (null != sessionContext && sessionContext instanceof WebSessionContext){
            WebSessionContext webSessionContext = (WebSessionContext) sessionContext;
            HttpServletRequest request = (HttpServletRequest) webSessionContext.getServletRequest();
            if (null != request){
                session.setHost(request.getRemoteHost());
                session.setUserAgent(request.getHeader("User-Agent"));
            }
        }
        return session;
    }
}
