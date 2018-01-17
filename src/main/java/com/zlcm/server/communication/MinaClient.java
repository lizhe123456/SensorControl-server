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


    private static int[] port = {8083,8084,8085,8086,8087,8088,8089,8090,8091,8092,8093,8094,8095,8096,8097,8098,8099,8101,8102,8103,8104,8105,8106,8107,8108
            ,8109,8110,8111,8112,8113,8114,8115,8116,8117,8118,8119,8120,8121,8122,8123,8124,8125,8126,8127,8128,8129,8130,8131,8132,8133,8134,8135,8136,8137,8138
            ,8139,8140,8141,8142,8143,8144,8145,8146,8147,8148,8149,8150,8151,8152,8153,8154,8155,8156,8157,8158,8159,8161,8162,8163,8164,8165,8167,8168,8169,8170,8171
            ,8172,8173,8174,8175,8176,8177,8178,8179,8180,8181,8182,8183,8184,8185,8186,8187,8188,8189,8190,8191,8192,8193,8194,8195,8196,8197,8198,8199,8200,8201,8202,8203
            ,8204,8205,8206,8207,8208,8209,8210,8211};

    private static int[] sads = {8222,8223,8224};

    public static void main(String[] args) throws UnknownHostException, InterruptedException {
//        for (int i = 0; i < sads.length; i++) {
//            executorService.execute(new ZlRunnable("192.168.1.201",sads[i], BmpUtil.getImagePixel("D:/pa/sdh.png")));
//        }
//        executorService.execute(new ZlRunnable("192.168.1.201",8211, "我爱你mina".getBytes()));
//        send(new String[]{"192.168.1.201"},BmpUtil.getImagePixel("D:/pa/sdh.png"));
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
