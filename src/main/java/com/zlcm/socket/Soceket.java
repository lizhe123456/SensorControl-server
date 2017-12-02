package com.zlcm.socket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Soceket {

    //用户集合
    private static ArrayList<Socket> list = new ArrayList<Socket>();

    public static void main(String[] arg){
        //上线用户
        Socket s = null;
        //用户
        String ip = null;
        try {
            //构建服务器对象
            ServerSocket ss = new ServerSocket(10023);

            //构建 用户集合
            list = new ArrayList<Socket>();

            System.out.println("服务器准备就绪 ...");
            //循环监听
            while(true){
                //上线用户
                s = ss.accept();
                //上线的人都添加到 集合中
                list.add(s);
                //获取 Socket IP
                ip = s.getInetAddress().getHostAddress();
                System.err.println( ip + " 用户上线了 , 当前在线用户为: " + list.size() + "人 !" );
                //构建 发送信息线程
                M2MReceive receive = new M2MReceive(s);
                receive.start();
//                M2MSend send = new M2MSend(s);
//                send.start();
            }
        } catch (IOException e) {
            //用户下线
            list.remove(s);
            System.err.println(ip + " 已下线 , 当前在线人数为: " + list.size() + " 人 !");
        }
    }

    public static ArrayList<Socket> getList() {
        return list;
    }

    public static void setList(ArrayList<Socket> list) {
        Soceket.list = list;
    }
}
