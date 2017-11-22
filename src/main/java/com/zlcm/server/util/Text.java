package com.zlcm.server.util;

public class Text {

    static byte[] s = {1,2,6,4,7,9,8,3,6,5,4};

    public static void main(String[] arg){
        char j = Crc16Calc(s,s.length);
        byte[] b = charToByte(j);
        System.out.println(HexStrUtils.bytesToHexString(s));
        System.out.println(HexStrUtils.bytesToHexString(b));
    }

    public static char Crc16Calc(byte[] data_arr, int data_len) {
        char crc16 = 0;
        int i;
        for(i =0; i < (data_len); i++) {
            crc16 = (char)(( crc16 >> 8) | (crc16 << 8));
            crc16 ^= data_arr[i]& 0xFF;
            crc16 ^= (char)(( crc16 & 0xFF) >> 4);
            crc16 ^= (char)(( crc16 << 8) << 4);
            crc16 ^= (char)((( crc16 & 0xFF) << 4) << 1);
        }
        return crc16;
    }

    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }


}
