package com.zlcm.server.communication;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class ZlThreadFactory implements ThreadFactory {

    private AtomicInteger count = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        String threadName = ZLExecutor.class.getSimpleName() + count.addAndGet(1);
        System.out.println(threadName);
        t.setName(threadName);
        return t;
    }
}
