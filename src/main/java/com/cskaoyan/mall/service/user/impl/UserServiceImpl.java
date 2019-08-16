package com.cskaoyan.mall.service.user.impl;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import com.cskaoyan.mall.mapper.UserMapper;
import com.cskaoyan.mall.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> listUserByCondition(String username,String mobile) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUsernameLike(username).andMobileLike(mobile);
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }
}
