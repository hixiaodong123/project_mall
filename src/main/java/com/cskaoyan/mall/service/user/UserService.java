package com.cskaoyan.mall.service.user;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserService {

    //查询全部user，用于获得total参数
    List<User> listUserByCondition(String username,String mobile, String sort, String order);

    boolean deleteByPrimaryKey(int id);

    boolean insert(User record);

    User selectByPrimaryKey(int id);

    User selectByUsername(String username);

    boolean update(User record);

}
