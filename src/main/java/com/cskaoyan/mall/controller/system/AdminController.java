package com.cskaoyan.mall.controller.system;


import com.cskaoyan.mall.bean.page.Page;
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
@RequestMapping("admin/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @RequestMapping("list")
    public Map<String, Object> listAdmin(Page page,String username) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Map> list = new ArrayList<>();
        String orderBy = page.getSort() + " " + page.getOrder();
        if (username == null) {
            list = adminService.listAdminsBySort(orderBy);
        } else {
            list = adminService.listAdminsByLikeName(username, orderBy);
        }
        Map data = returnList(list);
        map.put("data", data);
        return returnSuccess(map);
    }

    private Map<String, Object> returnSuccess(Map<String,Object> map) {
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }

    private Map returnList(List<Map> list) {
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", list);
        return map;
    }

}
