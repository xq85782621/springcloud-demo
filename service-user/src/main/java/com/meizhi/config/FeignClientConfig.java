package com.meizhi.config;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meizhi.entity.User;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;

public class FeignClientConfig {

    @Bean
    public BasicAuthRequestInterceptor getBasicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("admin", "123");
    }

    public static void main(String[] args) {
        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        User user = new User();

        user.setUsername("xuqiang");
        objectObjectHashMap.put(user, "user");
        objectObjectHashMap.put("age", 1);

        String s = JSONUtil.toJsonStr(objectObjectHashMap);
        System.out.println(s);

    }

}
