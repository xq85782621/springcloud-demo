package com.meizhi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.meizhi.entity.Order;
import com.meizhi.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;


    public List<Order>  orderListByUserId(Integer userId){
        List<Order> orderList = orderMapper.selectList(new QueryWrapper<Order>().eq("user_id", userId));
        return orderList;
    }
}
