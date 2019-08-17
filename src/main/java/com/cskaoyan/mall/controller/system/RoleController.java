package com.cskaoyan.mall.controller.system;


import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.page.Page;
import com.cskaoyan.mall.service.AdminService;
import com.cskaoyan.mall.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("admin/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    @RequestMapping("list")
    public Map<String, Object> listRole(Page page) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Role> roles = roleService.listRolesBySort(page.getSort()+" " +page.getOrder());
        Map data = returnList(roles);
        map.put("data", data);
        return returnSuccess(map);
    }

    @RequestMapping("options")
    public Map<String, Object> options() {
        List data = new ArrayList();
        List<Role> roles = roleService.listRoles();
        for (Role role : roles) {
            Map<String, Object> temp = new HashMap<>();
            temp.put("value", role.getId());
            temp.put("label", role.getName());
            data.add(temp);
        }
        Map map = new HashMap();
        map.put("data", data);
        return returnSuccess(map);
    }

    private Map<String, Object> returnSuccess(Map<String,Object> map) {
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }

    private Map returnList(List list) {
        PageInfo pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", list);
        return map;
    }

}
