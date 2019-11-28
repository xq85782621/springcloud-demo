package com.meizhi.controller;


import com.meizhi.common.response.ResponseResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class ConfigTestController {

    /**
     * desc 属性存在于git服务种的配置文件
     */
     @Value("${desc}")
    private String desc;

    @RequestMapping("/")
    public Map test(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("desc", desc);
        return map;
    }

}
