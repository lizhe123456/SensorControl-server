package com.zlcm.server.communication;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ZLExecutor{

    private ThreadPoolExecutor pool = null;
    private static ZLExecutor zlExecutor;

    private ZLExecutor() {
        init();
    }

    public static ZLExecutor getZlExecutor() {
        if (zlExecutor == null){
            return zlExecutor = new ZLExecutor();
        }else {
            return zlExecutor;
        }

    }

    /**
     * 线程池初始化方法
     *
     * corePoolSize 核心线程池大小----10
     * maximumPoolSize 最大线程池大小----30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     *                          即当提交第41个任务时(),
     *                                任务会交给RejectedExecutionHandler来处理
     */
    public void init() {
        pool = new ThreadPoolExecutor(
                100,
                1000,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(500),
                new ZlThreadFactory(),
                new ZlRejectedExecutionHandler());
    }

    public void destory() {
        if(pool != null) {
            pool.shutdownNow();
        }
    }

    public ExecutorService getZlThreadPoolExecutor() {
        return this.pool;
    }

}
