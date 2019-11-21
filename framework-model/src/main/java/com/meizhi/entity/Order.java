package com.meizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@TableName("tb_order")
public class Order {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private BigDecimal orderMoney;

    private Integer status;

    private Date addTime;

    private Integer userId;
}
