package com.zlcm.nosocket;

import java.io.DataOutputStream;
import java.io.IOException;

public class SendThread extends Thread {

    private DataOutputStream dout;

    public SendThread(DataOutputStream dout) {
        super();
        this.dout = dout;
    }

    @Override
    public void run() {
        while(true){
            try {
                dout.write(new byte[]{0x05});
            } catch (IOException e) {
                break;
            }
        }
    }
}
