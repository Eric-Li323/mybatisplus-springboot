package com.itcast.mp.mybatisplusspringboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itcast.mp.mybatisplusspringboot.pojo.User;


public interface UserMapper extends BaseMapper<User> {

    User findById(Long id);
}