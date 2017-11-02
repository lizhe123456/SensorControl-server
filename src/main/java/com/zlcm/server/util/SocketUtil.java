package com.zlcm.server.util;


import java.io.*;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SocketUtil {

    private String ip;
    private ThreadPoolExecutor executor;
    private static SocketUtil socketUtil;
    private int port;
    private Runnable runnable;

    private SocketUtil() {
    }

    public static SocketUtil getSocketUtil(String ip,int port) {
        if (socketUtil == null){
            socketUtil = new SocketUtil();
            socketUtil.initThreadPool();
            socketUtil.ip = ip;
            socketUtil.port = port;
        }
        return socketUtil;
    }


    private void initThreadPool() {
        executor = new ThreadPoolExecutor(20000, 40000, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10000));
    }

    /**
     * 打开socket的连接
     */
    private Runnable addData(String data){
        runnable = new Runnable() {
            @Override
            public void run() {
                PrintWriter pw = null;
                OutputStream os = null;
                try {
                    Socket socket = new Socket(ip,port);
                    os = socket.getOutputStream();
                    pw = new PrintWriter(os);
                    pw.print(data);
                    pw.flush();
                    Thread.currentThread().sleep(4000);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                        if (pw != null){
                            pw.close();
                        }
                    } catch (IOException e) {
                        LogUtil.getInstance(SocketUtil.class).e("IO流关闭失败");
                        e.printStackTrace();
                    }
                }
            }
        };
        return runnable;
    }

    public void sendData(String data){
        Runnable run = addData(data);
        executor.execute(run);
        executor.shutdown();
    }

}
