package com.zlcm.server.util.sms;

import com.zlcm.server.util.SocketUtil;

public class heh {

    public static void main(String[] arg){
        SocketUtil socketUtil = SocketUtil.getSocketUtil("13.102.25.195",8080);
        socketUtil.sendData(new byte[]{0x02,0x01});

    }
}
