package com.cskaoyan.mall.controller.webController.admin;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.page.Page;
import com.cskaoyan.mall.service.admin.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
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
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Map> list ;
        String orderBy = page.getSort() + " " + page.getOrder();
        if (username == null) {
            list = adminService.listAdminsBySort(orderBy);
        } else {
            list = adminService.listAdminsByLikeName(username, orderBy);
        }
        Map<String, Object> data = returnList(list);
        return returnSuccess(data);
    }

    private Map<String, Object> returnSuccess(Object map) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("errmsg", "成功");
        returnMap.put("errno", 0);
        returnMap.put("data", map);
        return returnMap;
    }

    private Map<String, Object> returnList(List<Map> list) {
        PageInfo<Map> pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", list);
        return map;
    }

    @RequestMapping("create")
    public Map createAdmin(@RequestBody Admin admin) {
        if (admin.getUsername() == null) return null;
        admin.setPassword(encryption(admin.getPassword()));
        Date date = new Date();
        admin.setAddTime(date);
        admin.setUpdateTime(date);
        adminService.insertAdmin(admin);
        Admin findAdmin = adminService.listAdminById(admin.getId());
        String addTime = date2String(findAdmin.getAddTime());
        String updateTime = date2String(findAdmin.getUpdateTime());
        Map<String, Object> map = new HashMap<>();
        map.put("id", findAdmin.getId());
        map.put(("password"), findAdmin.getPassword());
        map.put("avatar", findAdmin.getAvatar());
        map.put("add_time", addTime);
        map.put("update_time", updateTime);
        map.put("username", findAdmin.getUsername());
        map.put("roleIds", findAdmin.getRoleIds());
        return returnSuccess(map);
    }

    //之后需要对密码进行加密处理
    private String encryption(String password) {

        return password;
    }


    //删除操作
    @RequestMapping("delete")
    public Map<String, Object> deleteAdmin(@RequestBody Admin admin) {
        boolean b = adminService.deleteAdminById(admin.getId());
        if (!b) return null;
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }

    //编辑操作
    @RequestMapping("update")
    public Map<String, Object> updateAdmin(@RequestBody Admin admin) {
        boolean b = adminService.updateAdmin(admin);
        if (!b) return null;
        Admin findAdmin = adminService.listAdminById(admin.getId());
        String updateTime = date2String(findAdmin.getUpdateTime());
        Map<String, Object> map = new HashMap<>();
        map.put("avatar", findAdmin.getAvatar());
        map.put("id", findAdmin.getId());
        map.put("password", findAdmin.getPassword());
        map.put("roleIds", findAdmin.getRoleIds());
        map.put("updateTime", updateTime);
        map.put("username", findAdmin.getUsername());
        return returnSuccess(map);
    }

//    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
