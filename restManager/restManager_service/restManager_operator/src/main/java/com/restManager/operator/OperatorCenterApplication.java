package com.restManager.operator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @ClassName OperatorCenterApplication
 * @Description TODO
 * @date 2021/5/15 15:27
 * @Version 1.0
 */
@SpringBootApplication(scanBasePackages = {"com.restManager"})
@EnableDiscoveryClient
@MapperScan(basePackages = {"com.restManager.operator.mapper"})
public class OperatorCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperatorCenterApplication.class, args);
    }
}
