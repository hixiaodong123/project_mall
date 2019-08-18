package com.cskaoyan.mall.service.mall.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.mall.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;


    @Override
    public User selectByPrimaryKey(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        return user;
    }
}
