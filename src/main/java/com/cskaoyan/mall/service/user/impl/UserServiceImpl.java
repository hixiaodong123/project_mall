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
    public List<User> listUserByCondition(String username,String mobile, String sort, String order) {
        UserExample userExample = new UserExample();
        userExample.setOrderByClause(sort + " " + order);
        userExample.createCriteria().andUsernameLike(username).andMobileLike(mobile);
        List<User> users = userMapper.selectByExample(userExample);
        return users;
    }

    @Override
    public boolean deleteByPrimaryKey(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        user.setDeleted(true);
        return 0 != userMapper.updateByPrimaryKey(user);
    }

    @Override
    public boolean insert(User record) {
        User user = userMapper.selectByUsername(record.getUsername());
        if (user == null) return 0 != userMapper.insert(record);
        return false;
    }

    @Override
    public User selectByPrimaryKey(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override

    public User selectByUsernameAndPassword(String username, String password) {
        return userMapper.selectByUsernameAndPassword(username,password);
    }
    public User selectByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public boolean update(User record) {
        return 0 != userMapper.updateByPrimaryKey(record);
    }

}
