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
        for (int i = miny; i < height; i++) {
            for (int j = minx; j < width; j++) {
                int pixel = bi.getRGB(j, i); // 下面三行代码将一个数字转换为RGB数字
                byte red = (byte) ((pixel & 0xff0000) >> 16);
                byte green = (byte) ((pixel & 0xff00) >> 8);
                byte blue = (byte) (pixel & 0xff);
                list.add(red);
                list.add(green);
                list.add(blue);
            }
        }
        byte[] rgb = new byte[list.size()];
        for (int i = 0; i < list.size(); i++) {
            rgb[i] = list.get(i);
        }

        return rgb;
    }

}
