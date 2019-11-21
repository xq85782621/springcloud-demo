package com.meizhi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 *   zuul在2.1.2版本及以上的springboot中,支持很差
 */
@SpringBootApplication
@EnableZuulProxy //此工程是一个zuul网关
@EnableDiscoveryClient
public class ZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication.class, args);
    }


}
