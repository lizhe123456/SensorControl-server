package com.zlcm.socket;

import com.zlcm.server.util.HexStrUtils;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class M2MReceive extends Thread{

    private static final int DELIMITER = '\n';
    private Socket s;
    private int len;
    public M2MReceive(Socket s) {
        this.s = s;
    }

    @Override
    public void run() {
        try {
            //构建输入流
            //不断的接收信息
            while (true) {
                InputStream in = s.getInputStream();
                BufferedInputStream br = new BufferedInputStream(in);
                OutputStream os = s.getOutputStream();
                BufferedOutputStream outputStream = new BufferedOutputStream(os);
                int r = -1;

                List<Byte> l = new LinkedList<Byte>();
                while ((r = br.read()) != -1){
                    byte num = Byte.valueOf((byte)r);
                    l.add(num);
                }
                byte[] b = new byte[l.size()];
                for (int i = 0; i < l.size(); i++) {
                    b[i] = l.get(i);
                }

                if (b.length == 1460){
                    System.out.println(HexStrUtils.bytesToHexString(b));
                    outputStream.write(new byte[]{0x06});
                    outputStream.flush();
                    s.shutdownOutput();
                }

                if (b.length!=0 && b[0] == 0x04){
                    System.out.println(HexStrUtils.bytesToHexString(b));
                    outputStream.write(new byte[]{0x15});
                    outputStream.flush();
                    s.shutdownOutput();
                }

                if (b.length != 0 && b[0] == 0x02){
                    System.out.println(HexStrUtils.bytesToHexString(b));
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 读入输入流,直到读取到了定界符,并返回定界符前面的所有字符
     *
     * 1.包含定界符的信息 2.不包含定界符的信息
     *
     * @return
     * @throws IOException
     */
    public byte[] nextMsg(InputStream in) throws IOException {
        ByteArrayOutputStream messageBuffer = new ByteArrayOutputStream();
        int nextByte;
        
        while ((nextByte = in.read()) != DELIMITER) {
            if (nextByte == -1) {
                
                if (messageBuffer.size() == 0)
                {
                    return null;
                }
                else
                {
                    throw new EOFException(
                            "Non-empty message without delimiter");
                }
            }
            messageBuffer.write(nextByte);
        }
        return messageBuffer.toByteArray();
    }
}
