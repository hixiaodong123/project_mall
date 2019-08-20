package com.cskaoyan.mall.service.login;

import com.cskaoyan.mall.bean.Admin;
import java.util.List;
import java.util.Map;

public interface LoginService {

    Admin queryUserMessage(String username);

    List<String> queryUserPermsByName(Admin admin);

    List<String> queryUserRolesByName(Admin admin);

    Map<String,Object> getTotals();
}
