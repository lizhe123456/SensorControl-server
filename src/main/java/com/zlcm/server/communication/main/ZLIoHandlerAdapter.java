package com.zlcm.server.communication.main;

import com.zlcm.server.dao.AdvertDeviceMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.AdvertDevice;
import com.zlcm.server.util.SubPackage;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ZLIoHandlerAdapter extends IoHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ZLIoHandlerAdapter.class);

    private byte[] data;
    private Integer did;
    private Integer aid;
    private int num;
    @Autowired
    AdvertDeviceMapper advertDeviceMapper;

    public ZLIoHandlerAdapter(byte[] data, Integer did, Integer aid) {
        this.data = data;
        this.did = did;
        this.aid = aid;
    }

    //  当一个新客户端连接后触发此方法.
    @Override
    public void sessionCreated(IoSession session) throws Exception {
    }
    //当连接后打开时触发此方法，一般此方法与 sessionCreated 会被同时触发
    @Override
    public void sessionOpened(IoSession session) throws Exception {
    }
    // 当连接被关闭时触发，例如客户端程序意外退出等等.
    @Override
    public void sessionClosed(IoSession session) throws Exception {
    }
    //当连接空闲时触发此方法.
    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
    }

    //当信息已经传送给客户端后触发此方法.
    @Override
    public void messageSent(IoSession session, Object message){
        logger.info("Sent message " + message.toString());
        boolean flag = true;
        byte[] b = new byte[0];
        try {
            b = SubPackage.chuli(num,data);
        } catch (SysException e) {
//            session.close(true);
            flag = false;
            num = 0;
        }
        if (flag) {
            session.write(IoBuffer.wrap(b));
            num++;
        }

    }

    // 当接收到客户端的请求信息后触发此方法.
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        byte[] received = (byte[]) message;
        switch (received[0]){
            case SubPackage.ACK:
                break;
            case SubPackage.NAK:
                break;
            case SubPackage.EOT:
                session.close(true);
                advertDeviceMapper.save(new AdvertDevice(aid,did));
                break;
            case SubPackage.CAN:
                session.close(true);
                break;
        }
//        byte[] b = SubPackage.chuli(num,data);
//        session.write(b);
        System.out.println(message.toString());
        super.messageReceived(session, message);
    }

    //当信息已经传送给客户端后触发此方法.
    @Override
    public void exceptionCaught(IoSession session, Throwable cause)
            throws Exception {
        session.close(true);
        logger.error("客户端发生异常...", cause);
        System.out.println("客户端发生异常..." + cause.getMessage());
    }
}
