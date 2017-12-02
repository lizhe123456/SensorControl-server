package com.zlcm.nosocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static String ip;

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        @SuppressWarnings("resource")
        ServerSocket ss = new ServerSocket(5432);

        Socket s = ss.accept();
        ip = s.getInetAddress().toString();
        ip = ip.substring(ip.indexOf("/")+1);
        System.out.println(ip+"上线了");

        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();

        DataInputStream din = new DataInputStream(in);
        DataOutputStream dout = new DataOutputStream(out);

        SendThread it = new SendThread(dout);
        PrintThread ot = new PrintThread(din,ip);

        new Thread(ot).start();
        new Thread(it).start();
    }
}
