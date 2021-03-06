package com.wd.myservice.userservice.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wd.myservice.userservice.web.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserDao extends BaseMapper<User> {
}
