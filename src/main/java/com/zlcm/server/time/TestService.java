package com.zlcm.server.time;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration
        ({"classpath:spring-mybatis.xml","classpath:spring-context.xml","classpath:applicationContext-alipay.xml","classpath:spring-time.xml","classpath:spring-mvc.xml"}) //加载配置文件
public class TestService {
}
