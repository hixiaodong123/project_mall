package com.cskaoyan.mall.controller.webController.admin;


import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.page.Page;
import com.cskaoyan.mall.service.admin.AdminService;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.utils.MD5Utils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    LogService logService;

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
    public Map createAdmin(@RequestBody Admin admin, HttpServletRequest request) {
        if (admin.getUsername() == null) return null;
        admin.setPassword(MD5Utils.getMd5(admin.getPassword(), admin.getUsername()));
        Date date = new Date();
        admin.setAddTime(date);
        admin.setUpdateTime(date);
        boolean b = adminService.insertAdmin(admin);
        if (!b) {
            Subject subject = SecurityUtils.getSubject();
            String username = String.valueOf(subject.getPrincipals());
            Log log = new Log(username,request.getRemoteAddr(),1,"新增管理员",
                    false,"已存在管理员" + admin.getUsername());
            logService.insertLog(log);
            Map<String, Object> returnMap = new HashMap<>();
            returnMap.put("errmsg", "已存在同名管理员");
            returnMap.put("errno", 403);
            returnMap.put("data", null);
            return returnMap;
        }
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


        Subject subject = SecurityUtils.getSubject();
        String username = String.valueOf(subject.getPrincipals());
        Log log = new Log(username,request.getRemoteAddr(),1,
                "新增管理员",true,"成功新增管理员" + admin.getUsername());
        logService.insertLog(log);

        return returnSuccess(map);
    }

    //删除操作
    @RequestMapping("delete")
    public Map<String, Object> deleteAdmin(@RequestBody Admin admin,HttpServletRequest request) {
        boolean b = adminService.deleteAdminById(admin.getId());
        Subject subject = SecurityUtils.getSubject();
        String username = String.valueOf(subject.getPrincipals());
        if (!b) {
            Log log = new Log(username,request.getRemoteAddr(),1,
                    "删除管理员",false,"删除管理员失败");
            logService.insertLog(log);
            Map<String, Object> map = new HashMap<>();
            map.put("errmsg", "失败");
            map.put("errno", 0);
            return map;
        }
        Log log = new Log(username,request.getRemoteAddr(),1,
                "删除管理员",true,"成功删除管理员" + admin.getUsername());
        logService.insertLog(log);

        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }

    //编辑操作
    @RequestMapping("update")
    public Map<String, Object> updateAdmin(@RequestBody Admin admin, HttpServletRequest request) {
        boolean b = adminService.updateAdmin(admin);
        Subject subject = SecurityUtils.getSubject();
        String username = String.valueOf(subject.getPrincipals());
        if (!b){
            Log log = new Log(username,request.getRemoteAddr(),1,
                    "更新管理员",false,"更新管理员失败");
            logService.insertLog(log);
            Map<String, Object> map = new HashMap<>();
            map.put("errmsg", "更新失败");
            map.put("errno", 0);
            return map;
        }
        Admin findAdmin = adminService.listAdminById(admin.getId());
        String updateTime = date2String(findAdmin.getUpdateTime());
        Map<String, Object> map = new HashMap<>();
        map.put("avatar", findAdmin.getAvatar());
        map.put("id", findAdmin.getId());
        map.put("password", findAdmin.getPassword());
        map.put("roleIds", findAdmin.getRoleIds());
        map.put("updateTime", updateTime);
        map.put("username", findAdmin.getUsername());

        Log log = new Log(username,request.getRemoteAddr(),1,
                "更新管理员",true,"成功更新管理员" + admin.getUsername());
        logService.insertLog(log);


        return returnSuccess(map);
    }

//    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }

}
