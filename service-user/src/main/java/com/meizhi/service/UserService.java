package com.meizhi.service;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.RandomUtil;
import com.meizhi.client.OrderClient;
import com.meizhi.common.response.ResponseResult;
import com.meizhi.entity.Order;
import com.meizhi.entity.User;
import com.meizhi.mapper.UserMapper;
import com.meizhi.model.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderClient orderClient;

    public List<User> userList() {
        List<User> list = userMapper.selectList(null);
        return list;
    }


    /**
     * 这里通过client调用了order服务
     * @param userId
     * @return
     */
    public UserVo userOrderDetail(Integer userId,Integer time) {
        User user = userMapper.selectById(userId);
        // rpc调用订单服务
        ResponseResult result = orderClient.orderListByUserId(userId,time);
        System.err.println("调用order是否成功:" + result.isSuccess());
        List<Order> orderList;
        if(result.isSuccess()){
            orderList = (List<Order>) result.getData();
        }else {
            orderList = new ArrayList<>();
        }
        UserVo userVo = new UserVo();
        BeanUtil.copyProperties(user, userVo);
        userVo.setOrderList(orderList);
        return userVo;
    }


}
