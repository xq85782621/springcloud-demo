package com.meizhi.controller;

import cn.hutool.core.util.ObjectUtil;
import com.meizhi.common.response.ResponseResult;
import com.meizhi.entity.Order;
import com.meizhi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/list/{userId}/sleep/{time}")
    public ResponseResult orderListByUserId(@PathVariable("userId") Integer userId,@PathVariable Integer time) throws Exception{

        // 开启2个order服务,然后看两个应用的控制台打印的语句来判断负载情况
        String instance_id = System.getProperty("INSTANCE_ID");
        System.err.println("访问了:"+instance_id);

        // 睡眠指定时间,模拟网络延迟
        String port = System.getProperty("PORT");
        if(ObjectUtil.equal(port, "9092")){
            Thread.sleep((time*3));   // port为9092 也就是order2服务的延迟时间是指定延迟时间的 3 倍
            System.err.println("睡眠"+ (time*3));
        }else {
            Thread.sleep(time);
        }

        // 执行查询
        List<Order> orderList = orderService.orderListByUserId(userId);
        return new ResponseResult(orderList);
    }



}
