package com.zlcm.server.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片
 */
public class BmpUtil {

    /**
     * 读取一张图片的RGB值
     *
     * @throws Exception
     */
    public static byte[] getImagePixel(String image){
        File file = new File(image);
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int width = bi.getWidth();
        int height = bi.getHeight();
        int minx = bi.getMinX();
        int miny = bi.getMinY();
        List<Byte> list = new ArrayList<>();
        for (int i = miny; i < width; i++) {
            for (int j = minx; j < height; j++) {
                int pixel = bi.getRGB(i, j); // 下面三行代码将一个数字转换为RGB数字
                byte blue = (byte) (pixel & 0xff);
                byte green = (byte) ((pixel & 0xff00) >> 8);
                byte red = (byte) ((pixel & 0xff0000) >> 16);
                list.add(blue);
                list.add(green);
                list.add(red);

            }
        }
        byte[] rgb = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rgb[i] = list.get(i);
        }

        return rgb;
    }

    public static void main(String[] args){
        byte[] b = getImagePixel("C:\\Users\\Administrator\\Desktop\\未标题-1.bmp");
        System.out.println("length: " + b.length);
        String s = HexStrUtils.bytesToHexString(b);
        String[] strings = stringSpilt(s,32);
        for (int i = 0; i < leng; i++) {
            System.out.println(strings[i]);
        }

    }

    static int leng;
    public  static String[] stringSpilt(String s,int len){
        int spiltNum;//len->想要分割获得的子字符串的长度
        int i;
        int cache = len;//存放需要切割的数组长度
        spiltNum = (s.length())/len;
        String[] subs;//创建可分割数量的数组
        if((s.length()%len)>0){
            subs = new String[spiltNum+1];
        }else{
            subs = new String[spiltNum];
        }

        //可用一个全局变量保存分割的数组的长度
        //System.out.println(subs.length);
        leng = subs.length;
        int start = 0;
        if(spiltNum>0){
            for(i=0;i<subs.length;i++){
                if(i==0){
                    subs[0] = s.substring(start, len);
                    start = len;
                }else if(i>0 && i<subs.length-1){
                    len = len + cache ;
                    subs[i] = s.substring(start,len);
                    start = len ;
                }else{
                    subs[i] = s.substring(len,s.length());
                }
            }
        }
        return subs;
    }
}
