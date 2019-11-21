package com.meizhi;

import com.meizhi.config.RibbonConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication // boot
@MapperScan("com.meizhi.mapper")  // mybatis-plus
@EnableDiscoveryClient  //eureka
@EnableCircuitBreaker  //hystrix
@EnableFeignClients // 开启Feign功能
@RibbonClient(name = "service-order", configuration = RibbonConfig.class) // 对order服务单独指定负载策略
public class UserApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }




}
