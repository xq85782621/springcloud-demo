package com.meizhi.controller;

import cn.hutool.json.JSONUtil;
import com.meizhi.common.response.ResponseResult;
import com.meizhi.entity.User;
import com.meizhi.model.UserVo;
import com.meizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController  {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseResult userList(){
        List<User> list = userService.userList();
        return new ResponseResult(list);
    }


    /**
     * 测试feign远程调用
     */
    @GetMapping("/detail/{userId}/sleep/{time}")
    public ResponseResult userOrder(@PathVariable("userId") Integer userId,@PathVariable("time") Integer time) throws InterruptedException {
        System.err.println("------------------------------------------------------------");
        long begin = System.currentTimeMillis();
        UserVo userVo = userService.userOrderDetail(userId,time);
        long end = System.currentTimeMillis();
        System.err.println("用户订单详情接口耗时:" + (end-begin));
        return new ResponseResult(userVo);
    }

}
