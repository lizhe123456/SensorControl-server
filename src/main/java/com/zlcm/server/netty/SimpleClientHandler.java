package com.zlcm.server.netty;

import com.zlcm.server.dao.AdvertDeviceMapper;
import com.zlcm.server.exception.SysException;
import com.zlcm.server.model.bean.AdvertDevice;
import com.zlcm.server.util.SubPackage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    private byte[] data;
    private Integer did;
    private Integer aid;
    private int num;
    private boolean flag = true;
    private AdvertDeviceMapper advertDeviceMapper;

    public SimpleClientHandler(byte[] data, Integer did, Integer aid,AdvertDeviceMapper advertDeviceMapper) {
        this.data = data;
        this.did = did;
        this.aid = aid;
        this.advertDeviceMapper = advertDeviceMapper;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("Server said:" + new String(result1,"gbk"));
        switch (result1[0]){
            case SubPackage.ACK:
                try {
                    if (flag) {
                        byte[] b = SubPackage.chuli(num, data);
                        ByteBuf encoded = ctx.alloc().buffer(b.length);
                        encoded.writeBytes(b);
                        ctx.write(encoded);
                        ctx.flush();
                        num++;
                    }
                } catch (SysException e) {
                    flag = false;
                    num = 0;
                }
                break;
            case SubPackage.NAK:
                break;
            case SubPackage.EOT:
                ctx.channel().close();
                advertDeviceMapper.save(new AdvertDevice(aid,did));
                break;
            case SubPackage.CAN:
                ctx.channel().close();
                break;
        }
        result.release();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }


    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        byte[] bytes = {0x04};
        ByteBuf encoded = ctx.alloc().buffer(4 * bytes.length);
        encoded.writeBytes(bytes);
        ChannelFuture future = ctx.write(encoded);
        ctx.flush();
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);
    }
}
