package com.restManager.opeartor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class},scanBasePackages = {"com.restManager"})
@EnableDiscoveryClient //声明当前服务是注册中心的客户端
public class OperatorCenterWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(OperatorCenterWebApplication.class, args);
    }
}
