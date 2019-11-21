package com.meizhi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@TableName("user")
@ToString
public class User implements Serializable {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String username;
    private String address;

}
