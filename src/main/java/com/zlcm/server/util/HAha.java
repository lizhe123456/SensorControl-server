package com.zlcm.server.util;

public class HAha {
    static byte[] b = {1,2,1,3,1};
    static byte[] b1 = {4,1};
    public static void main(String[] a){
        byte[] c = new byte[b.length+b1.length];
        System.arraycopy(b,0,c,0,b.length);
        System.arraycopy(b1,0,c,b.length,b1.length);

        System.out.println(new String(c));
    }
}
