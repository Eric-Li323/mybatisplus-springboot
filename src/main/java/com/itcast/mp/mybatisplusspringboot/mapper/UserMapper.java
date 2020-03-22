package com.itcast.mp.mybatisplusspringboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itcast.mp.mybatisplusspringboot.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {

    User findById(Long id);
}