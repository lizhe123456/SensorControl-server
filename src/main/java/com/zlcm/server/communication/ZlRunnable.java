package com.zlcm.server.communication;

import com.zlcm.server.communication.main.ZLIoHandlerAdapter;
import com.zlcm.server.model.bean.Device;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;

public class ZlRunnable implements Runnable{
    private Device device;
    private Integer aid;
    private int port;
    private byte[] data;
    private static final Logger logger = LoggerFactory.getLogger(ZlRunnable.class);

    public ZlRunnable(Device device, Integer aid, int port, byte[] data) {
        this.device = device;
        this.aid = aid;
        this.port = port;
        this.data = data;
    }

    @Override
    public void run() {
        sendData(device,aid,port,data);

    }

    public static void sendData(Device device,Integer aid,int port,byte[] data){

        // 创建一个非阻塞的客户端程序
        IoConnector connector = new NioSocketConnector();
        // 设置链接超时时间
        connector.setConnectTimeoutMillis(10 * 1000);

        // 添加过滤器
        connector.getFilterChain().addLast("codec",
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset
                        .forName("UTF-8"), LineDelimiter.WINDOWS.getValue(),
                        LineDelimiter.WINDOWS.getValue())));
        // 添加业务逻辑处理器类
        connector.setHandler(new ZLIoHandlerAdapter(data,device.getDid(),aid));
        IoSession session = null;
        try {
            ConnectFuture future = connector.connect(new InetSocketAddress(
                    device.getIp(), port));// 创建连接
            future.awaitUninterruptibly();// 等待连接创建完成
            session = future.getSession();// 获得session
            session.write(IoBuffer.wrap(new byte[]{0x04}));// 发送消息
        } catch (Exception e) {
            logger.error("客户端链接异常...", e);
        }
        session.getCloseFuture().awaitUninterruptibly();
        connector.dispose();
    }
}
