package com.zlcm.server.util;


import org.apache.log4j.*;

public class LogUtil {

    private static LogUtil logUtil = null;
    private static Logger logger;

    private LogUtil() {
    }

    public static LogUtil getInstance(Class c){
        if (logUtil == null) {
            logUtil = new LogUtil();
            Logger.getLogger(c.getClass());
        }
        return logUtil;
    }


    public void e(Object error){
        logger.error(error);
    }

    public void d(Object debug){
        logger.debug(debug);
    }

    public void info(Object info){
        logger.info(info);
    }

}
