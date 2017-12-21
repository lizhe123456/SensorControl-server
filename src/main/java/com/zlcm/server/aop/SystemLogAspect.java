package com.zlcm.server.aop;

import com.zlcm.server.annotation.SystemControllerLog;
import com.zlcm.server.annotation.SystemServiceLog;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.Log;
import com.zlcm.server.model.bean.User;
import com.zlcm.server.service.LogService;
import com.zlcm.server.util.DateUtil;
import com.zlcm.server.util.id.LoginId;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * author:lizhe
 * date: 2017-12-19
 * for tomorrow
 * 类介绍:
 */
@Aspect
@Component
public class SystemLogAspect {

    private static final Logger _log = LoggerFactory.getLogger(SystemLogAspect. class);
    private static final ThreadLocal<Date> beginTimeThreadLocal =
            new NamedThreadLocal<Date>("ThreadLocal beginTime");
    private static final ThreadLocal<Log> logThreadLocal = new NamedThreadLocal<Log>("ThreadLocal log");

    private static final ThreadLocal<Integer> currentUser=new NamedThreadLocal<>("ThreadLocal user");

    @Autowired(required=false)
    private HttpServletRequest request;

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Autowired
    private LogService logService;

    /**
     * Controller层切点 注解拦截
     */
    @Pointcut("@annotation(com.zlcm.server.annotation.SystemControllerLog)")
    public void controllerAspect(){}

//    Service层切点
/*	@Pointcut("@annotation(com.myron.ims.annotation.SystemServiceLog)")
	public void serviceAspect(){}*/

    /**
     * 方法规则拦截
     */
    @Pointcut("execution(* com.zlcm.server.controller.*.*(..))")
    public void controllerPointerCut(){}

    /**
     * 前置通知 用于拦截Controller层记录用户的操作的开始时间
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException{
        Date beginTime=new Date();
        beginTimeThreadLocal.set(beginTime);//线程绑定变量（该数据只有当前请求的线程可见）
        if (_log.isDebugEnabled()){//这里日志级别为debug
            _log.debug("开始计时: {}  URI: {}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                    .format(beginTime), request.getRequestURI());
        }

        //读取session中的用户
        HttpSession session = request.getSession();
        try {
            Integer uid = LoginId.getUid(request);
            currentUser.set(uid);
        } catch (Exception e) {
            _log.error(e.getMessage());
        }
//        User user = (User) session.getAttribute("ims_user");
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     * @param joinPoint 切点
     */
    @SuppressWarnings("unchecked")
    @After("controllerAspect()")
    public void doAfter(JoinPoint joinPoint) {
//        Integer uid = currentUser.get();
        Integer uid = 0;

//        if(uid != -1){
            String title="";
            String type="info";                       //日志类型(info:入库,error:错误)
            String remoteAddr=request.getRemoteAddr();//请求的IP
            String requestUri=request.getRequestURI();//请求的Uri
            String method=request.getMethod();        //请求的方法类型(post/get)
            Map<String,String[]> params=request.getParameterMap(); //请求提交的参数

            try {
                title=getControllerMethodDescription2(joinPoint);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 打印JVM信息。
            long beginTime = beginTimeThreadLocal.get().getTime();//得到线程绑定的局部变量（开始时间）
            long endTime = System.currentTimeMillis();  //2、结束时间
            if (_log.isDebugEnabled()){
                _log.debug("计时结束：{}  URI: {}  耗时： {}   最大内存: {}m  已分配内存: {}m  已分配内存中的剩余空间: {}m  最大可用内存: {}m",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(endTime),
                        request.getRequestURI(),
                        DateUtil.formatDateTime(endTime - beginTime),
                        Runtime.getRuntime().maxMemory()/1024/1024,
                        Runtime.getRuntime().totalMemory()/1024/1024,
                        Runtime.getRuntime().freeMemory()/1024/1024,
                        (Runtime.getRuntime().maxMemory()-Runtime.getRuntime().totalMemory()+Runtime.getRuntime().freeMemory())/1024/1024);
            }

            Log log=new Log();
            log.setTitle(title);
            log.setType(type);
            log.setRemoteaddr(remoteAddr);
            log.setRequesturi(requestUri);
            log.setMethod(method);
            log.setMapToParams(params);
            log.setUid(uid);
            Date operateDate=beginTimeThreadLocal.get();
            log.setOperatedate(operateDate);
            log.setTimeout(DateUtil.formatDateTime(endTime - beginTime));

            //1.直接执行保存操作
            //this.logService.createSystemLog(log);

            //2.优化:异步保存日志
            //new SaveLogThread(log, logService).start();

            //3.再优化:通过线程池来执行日志保存
            threadPoolTaskExecutor.execute(new SaveLogThread(log, logService));
            logThreadLocal.set(log);
//        }

    }

    /**
     *  异常通知 记录操作报错日志
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "controllerAspect()", throwing = "e")
    public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        Log log = logThreadLocal.get();
        log.setType("error");
        log.setException(e.toString());
        new UpdateLogThread(log, logService).start();
    }

    /**
     * 获取注解中对方法的描述信息 用于service层注解
     * @param joinPoint 切点
     * @return discription
     */
    public static String getServiceMthodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemServiceLog serviceLog = method.getAnnotation(SystemServiceLog .class);
        String discription = serviceLog.description();
        return discription;
    }

    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     *
     * @param joinPoint 切点
     * @return discription
     */
    public static String getControllerMethodDescription2(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SystemControllerLog controllerLog = method
                .getAnnotation(SystemControllerLog.class);
        String discription = controllerLog.description();
        return discription;
    }

    /**
     * 保存日志线程
     */
    private static class SaveLogThread implements Runnable {
        private Log log;
        private LogService logService;

        public SaveLogThread(Log log, LogService logService) {
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            try {
                logService.save(log);
            } catch (SysException e) {
                _log.error(e.getMessage());
            }
        }
    }

    /**
     * 日志更新线程
     */
    private static class UpdateLogThread extends Thread {
        private Log log;
        private LogService logService;

        public UpdateLogThread(Log log, LogService logService) {
            super(UpdateLogThread.class.getSimpleName());
            this.log = log;
            this.logService = logService;
        }

        @Override
        public void run() {
            try {
                this.logService.update(log);
            } catch (SysException e) {
                _log.error(e.getMessage());
            }
        }
    }

}
