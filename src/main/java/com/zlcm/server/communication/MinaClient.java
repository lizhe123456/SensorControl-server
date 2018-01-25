package com.zlcm.server.communication;
import com.zlcm.server.constant.Constant;
import com.zlcm.server.dao.AdvertDeviceMapper;
import com.zlcm.server.dao.AdvertMapper;
import com.zlcm.server.dao.OrderMapper;
import com.zlcm.server.model.bean.Advert;
import com.zlcm.server.model.bean.AdvertDevice;
import com.zlcm.server.model.bean.Device;
import com.zlcm.server.netty.NettyRunnable;
import com.zlcm.server.service.OrderService;
import com.zlcm.server.util.BmpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.UnknownHostException;
import java.util.List;
import java.util.concurrent.ExecutorService;

public class MinaClient {

    public static void main(String[] args) throws UnknownHostException, InterruptedException {

    }

    public void send(AdvertDeviceMapper advertDeviceMapper,AdvertMapper advertMapper,List<Device> devices, Advert advert){

        try {
            ExecutorService executorService = ZLExecutor.getZlExecutor().getZlThreadPoolExecutor();
            byte[] bytes = BmpUtil.getImagePixel(Constant.ZS_ADDRESS+advert.getAdvertImg());
            for (int i = 0; i < devices.size(); i++) {
//                executorService.execute(new ZlRunnable(devices.get(i), advert.getAid(), sads[i], bytes));
            }
//            executorService.shutdown();
            while (true) {
                if (executorService.isTerminated()) {
                    //所有线程结束
                    List<Integer> de = advertDeviceMapper.getDeviceWhereAid(advert.getAid());
                    if (de.size() == devices.size()){
                        advert.setState((byte) 1);
                        advertMapper.update(advert);
                    }else {
                        advert.setState((byte) 3);
                        advertMapper.update(advert);
                    }
                    break;
                }
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
