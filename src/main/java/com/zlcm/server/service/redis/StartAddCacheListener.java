package com.zlcm.server.service.redis;

import com.zlcm.server.redis.RedisCacheUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class StartAddCacheListener implements ApplicationListener<ContextRefreshedEvent> {
    //日志
    private final Logger log = LoggerFactory.getLogger(StartAddCacheListener.class);

    @Autowired
    private RedisCacheUtil<Object> redisCache;

//    @Autowired
//    private BrandStoreService brandStoreService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //spring 启动的时候缓存城市和国家等信息
//        if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")) {
//            System.out.println("\n\n\n_________\n\n缓存数据 \n\n ________\n\n\n\n");
//            List<City> cityList = brandStoreService.selectAllCityMessage();
//            List<Country> countryList = brandStoreService.selectAllCountryMessage();
//
//            Map<Integer,City> cityMap = new HashMap<Integer,City>();
//
//            Map<Integer,Country> countryMap = new HashMap<Integer, Country>();
//
//            int cityListSize = cityList.size();
//            int countryListSize = countryList.size();
//
//            for(int i = 0 ; i < cityListSize ; i ++ )
//            {
//                cityMap.put(cityList.get(i).getCity_id(), cityList.get(i));
//            }
//
//            for(int i = 0 ; i < countryListSize ; i ++ )
//            {
//                countryMap.put(countryList.get(i).getCountry_id(), countryList.get(i));
//            }
//
//            redisCache.setCacheIntegerMap("cityMap", cityMap);
//            redisCache.setCacheIntegerMap("countryMap", countryMap);
//        }
    }

}
