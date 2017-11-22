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
    private String response = null;

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
    private String addData(byte[] data){
        executor.execute(new Runnable() {
             @Override
             public void run() {
                 OutputStream os = null;
                 Socket socket = null;
                try {
                    socket = new Socket(ip,port);
                    os = socket.getOutputStream();
                    while (true){
                        os.write(data);
                        os.flush();
                        Thread.currentThread().sleep(1000);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                        if (socket != null){
                            socket.close();
                        }
                    } catch (IOException e) {
                        LogUtil.getInstance(SocketUtil.class).e("IO流关闭失败");
                        e.printStackTrace();
                    }
                }
            }
        });
        executor.shutdown();
       return response;
    }

    public int sendData(byte[] data){
        String response = addData(data);
        if (response != null && response.equals("AAAAAAA")){
            return 203;
        }
        return 200;
    }

}
