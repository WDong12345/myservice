package com.wd.myservice.userservice.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@TableName(value = "user",schema = "test")
public class User {
//    @Id
    @TableId(type = IdType.AUTO)
//    @Column
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String qq;
    private String wechat;
    private String email;
    private String nickname;
    private String province;
    private String city;
    private String userType;
    private String district;
    private String street;
    private String address;


}
