package com.meizhi.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


/**
 * 直接访问该项目,可以从name得到配置文件的内容
 */
@RestController("/")
public class TestController {

    @Value("${spring.application.name}")
    private String applicationName;


    @RequestMapping
    public Map test(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", applicationName);
        return map;
    }
}
