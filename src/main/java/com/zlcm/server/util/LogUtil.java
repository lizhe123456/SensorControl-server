package com.zlcm.server.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {

    private static LogUtil logUtil = null;
    private static Logger logger;

    private LogUtil() {
    }

    public static LogUtil getInstance(Class c){
        if (logUtil == null) {
            logUtil = new LogUtil();
            LoggerFactory.getLogger(c.getClass());
        }
        return logUtil;
    }


    public void e(Object error){
        logger.error(error.toString());
    }

    public void d(Object debug){
        logger.debug(debug.toString());
    }

    public void info(Object info){
        logger.info(info.toString());
    }

}
