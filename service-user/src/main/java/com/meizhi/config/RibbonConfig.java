package com.meizhi.config;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;

public class RibbonConfig {
    @Bean
    public IRule ribbonRule() { // 其中IRule就是所有规则的标准
        return new com.netflix.loadbalancer.RoundRobinRule(); // 通过返回的对象指定策略
    }
}
