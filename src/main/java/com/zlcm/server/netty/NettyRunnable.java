package com.zlcm.server.netty;

import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.AdvertDeviceMapper;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.util.BmpUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class NettyRunnable {

    private Timer timer = new Timer();

    public void send(AdvertDeviceMapper advertDeviceMapper, AdvertMapper advertMapper, List<Device> devices, Advert advert) {
        NioEventLoopGroup group = new NioEventLoopGroup(4, new DefaultThreadFactory("client", true));
        Bootstrap b = new Bootstrap();
        byte[] bytes = BmpUtil.getImagePixel(Constant.ZS_ADDRESS+advert.getAdvertImg());
        try {
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
            // 等待连接关闭
            for (int i = 0; i < devices.size(); i++) {
                int finalI = i;
                b.handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        p.addLast(new SimpleClientHandler(bytes,devices.get(finalI).getDid(),advert.getAid(),advertDeviceMapper));
                    }
                });
                ChannelFuture f = b.connect(devices.get(finalI).getIp(),8080).sync();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                group.shutdownGracefully();
                List<Integer> de = advertDeviceMapper.getDeviceWhereAid(advert.getAid());
                if (de.size() == devices.size()){
                    advert.setState((byte) 1);
                    advertMapper.update(advert);
                }else {
                    advert.setState((byte) 3);
                    advertMapper.update(advert);
                }
            }
        },30000);
    }

}
