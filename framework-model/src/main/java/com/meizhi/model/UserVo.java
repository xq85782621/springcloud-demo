package com.meizhi.model;

import com.meizhi.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserVo {

    private Integer id;
    private String username;
    private String address;

    private List<Order> orderList;
}
