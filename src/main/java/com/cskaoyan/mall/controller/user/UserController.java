package com.cskaoyan.mall.controller.user;

import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.base.BaseResponseModel;
import com.cskaoyan.mall.service.user.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public BaseResponseModel<Map<String,Object>> listUser(int page,int limit,String username,String mobile,String sort,String order){
        //首先判断username和mobile字段是否填写,若未填写则将其设为空字符串，否则直接进行模糊查询
        username = BaseEncapsulation.judgeString(username);
        mobile = BaseEncapsulation.judgeString(mobile);
        //按页查询user
        PageHelper.startPage(page,limit);
        List<User> userList = userService.listUserByCondition("%" + username + "%","%" + mobile + "%");
        //封装BaseResponseModel
        BaseResponseModel<Map<String, Object>> encapsulation = BaseEncapsulation.encapsulation(userList);
        return encapsulation;
    }
}
