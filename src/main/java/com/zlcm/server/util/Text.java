package com.zlcm.server.util;

public class Text {

    public static void main(String[] args){
        SocketUtil.getSocketUtil("192.168.1.108",8080).sendData("xixi");
    }

}
