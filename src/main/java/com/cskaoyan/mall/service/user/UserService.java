package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.User;

import java.util.List;

public interface UserService {

    //查询全部user，用于获得total参数
    List<User> listUserByCondition(String username,String mobile, String sort, String order);

    User selectByPrimaryKey(int id);
}
