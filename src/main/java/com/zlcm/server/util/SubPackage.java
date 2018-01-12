package com.zlcm.server.util;


import com.zlcm.server.constant.Constant;
import com.zlcm.server.exception.SysException;

public class SubPackage {

    public static final byte NAK = 0x15;
    public static final byte EOT = 0x20;
    public static final byte SOH = 0x01;
    public static final byte ACK = 0x06;
    public static final byte CAN = 0x18;
    private static final int len = 1460;

    private static final int seizeASeat = 5;
    private static int dataLen = len - seizeASeat;

    public static byte[] chuli(int i,byte[] bytes) throws SysException {
//        Log.d("asjdhk", i+"");
        byte[] b = subpackage(bytes,i);
        byte[] head = new byte[3];
        byte[] tail;
        head[0] = SOH;
        head[1] = (byte) i;
        head[2] = (byte) (0xff - i);
        char check = Crc16Utils.Crc16Calc(b,b.length);
        tail = charToByte(check);
        byte[] data = new byte[head.length + b.length];
        System.arraycopy(head, 0, data, 0, head.length);
        System.arraycopy(b, 0, data, head.length, b.length);
        byte[] sendData = new byte[data.length + tail.length];
        System.arraycopy(data, 0, sendData, 0, data.length);
        System.arraycopy(tail, 0, sendData, data.length, tail.length);
        return sendData;
    }

    public static byte[] subpackage(byte[] img,int packageNum) throws SysException {
        try {
            byte[] resp = new byte[dataLen];
            int yu = img.length%dataLen;

            if (yu == 0){
                int position = packageNum * dataLen;
                for (int i = 0; i < dataLen; i++) {
                    resp[i] = img[i + position];
                }
            }else {
                int pLength = (img.length/dataLen);
//            Log.d("pLength",pLength+"");
                int position;
                if (packageNum == pLength){
                    position = (packageNum-1) * dataLen;
                }else {
                    position = packageNum * dataLen;
                }
                if (packageNum == pLength) {
                    resp = new byte[dataLen];
                    for (int i = 0; i < yu; i++) {
                        resp[i] = img[i + position];
                    }
                }else {
                    for (int i = 0; i < dataLen; i++) {
                        resp[i] = img[i + position];
                    }
                }
            }
            return resp;
        }catch (ArrayIndexOutOfBoundsException e){
            throw new SysException(Constant.ADD_ERROR);
        }

    }

    public static byte[] charToByte(char c) {
        byte[] b = new byte[2];
        b[0] = (byte) ((c & 0xFF00) >> 8);
        b[1] = (byte) (c & 0xFF);
        return b;
    }

    public static void main(String[] arg){
        byte[] bytes = BmpUtil.getImagePixel("D:/pa/sdh.png");
        byte[] b = new byte[0];
        try {
            b = chuli(124,bytes);
        } catch (SysException e) {
            return;
        }
        System.out.println(HexStrUtils.bytesToHexString(b));
    }

}
