package com.zlcm.nosocket;

import java.io.DataInputStream;
import java.io.IOException;

public class PrintThread implements Runnable {


    private DataInputStream din;
    private String ip;
    public PrintThread(DataInputStream din,String ip) {
        super();
        this.din = din;
        this.ip = ip;
    }

    @Override
    public void run() {
        while(true){
            try {
                int msg = din.read();
                System.out.println("["+ip+"]"+":"+msg);
            } catch (IOException e) {
                break;
            }

        }
    }
}
