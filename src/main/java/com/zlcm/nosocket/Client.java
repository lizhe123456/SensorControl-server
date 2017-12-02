package com.zlcm.nosocket;

import java.io.*;
import java.net.Socket;

public class Client {

    private static String ip = "127.0.0.1";

    public static void main(String[] args) throws IOException {
        @SuppressWarnings("resource")
        Socket s = new Socket(ip,5432);
        OutputStream out = s.getOutputStream();
        InputStream in = s.getInputStream();

        DataOutputStream dout = new DataOutputStream(out);
        DataInputStream din = new DataInputStream(in);

        SendThread it = new SendThread(dout);
        PrintThread ot = new PrintThread(din,ip);

        new Thread(ot).start();
        new Thread(it).start();

    }
}
