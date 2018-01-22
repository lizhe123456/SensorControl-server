package com.zlcm.apidoc;

import org.springframework.context.annotation.Bean;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public class ApplicationSwaggerConfig {
    @Bean
    public Docket addUserDocket() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2);
        Contact contact = new Contact("李哲", "", "603399531@qq.com");
        ApiInfo apiInfo = new ApiInfo("app后台管理系统", "WEB API文档", "V0.0.1", "", contact, "", "");
        docket.apiInfo(apiInfo);
        return docket;
    }

}
