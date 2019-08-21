package com.cskaoyan.mall.controller.webcontroller.admin;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.page.Page;
import com.cskaoyan.mall.service.admin.AuthorizationService;
import com.cskaoyan.mall.service.admin.PermissionService;
import com.cskaoyan.mall.service.admin.RoleService;
import com.cskaoyan.mall.utils.json.JsonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequestMapping("admin/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    @Autowired
    AuthorizationService authorizationService;

    @Autowired
    PermissionService permissionService;

    @RequestMapping("list")
    public Map<String, Object> listRole(Page page, String name) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(page.getPage(), page.getLimit());
        List<Map> list ;
        String orderBy = page.getSort() + " " + page.getOrder();
        if (name == null) {
            list = roleService.listRolesBySort(orderBy);
        } else {
            list = roleService.listRolesByLikeName(name, orderBy);
        }
        Map<String, Object> data = returnList(list);
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

    private Map<String, Object> returnList(List<Map> list) {
        PageInfo pageInfo = new PageInfo<>(list);
        long total = pageInfo.getTotal();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("items", list);
        return map;
    }

    @RequestMapping("create")
    public Map createRole(@RequestBody Role role) {
        if (role.getName() == null) return null;
        Role temp = roleService.listRoleByName(role.getName());
        if (temp != null) return null;
        roleService.insertRole(role);
        Role findRole = roleService.listRoleById(role.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("addTime", date2String(findRole.getAddTime()));
        map.put("desc", findRole.getDesc());
        map.put("id", findRole.getId());
        map.put("name", findRole.getName());
        map.put("update_time", date2String(findRole.getUpdateTime()));
        Map<String, Object> data = new HashMap<>();
        data.put("data", map);
        return returnSuccess(data);
    }

    //删除操作
    @RequestMapping("delete")
    public Map<String, Object> deleteRole(@RequestBody Role role) {
        boolean flag = roleService.deleteRoleById(role.getId());
        if (!flag) return null;
        return returnSuccess(new HashMap<>());
    }

    //编辑
    @RequestMapping("update")
    public Map<String, Object> updateRole(@RequestBody Role role) {
        boolean flag = roleService.updateRole(role);
        if (!flag) return null;
        return returnSuccess(new HashMap<>());
    }

    @RequestMapping(value = "/permissions" ,method = RequestMethod.GET)
    public Map<String, Object> permission(int roleId) {
        Map<String, Object> data =  new HashMap<>();
        Map<String, Object> map =  new HashMap<>();
        List<Permission> permissions = permissionService.listPermissionByRoleId(roleId);
        List<String> assignedPermissions = new ArrayList<>();
        if (permissions.size() == 1 && permissions.get(0).getPermission().equals("*")){
            assignedPermissions = authorizationService.listAllPermission();
        }
        else if (permissions != null) {
            for (Permission permission : permissions) {
                assignedPermissions.add(permission.getPermission());
            }
        }
        List<Map<String, Object>> systemPermissions = authorizationService.permissionInfo();
        data.put("assignedPermissions", assignedPermissions);
        data.put("systemPermissions", systemPermissions);
        map.put("errmsg", "成功");
        map.put("errno", 0);
        map.put("data", data);
        return map;
    }

    @RequestMapping(value = "/permissions", method = RequestMethod.POST)
    public Map<String, Object> permission(@RequestBody JSONObject jsonObject) {
        int roleId = (int) jsonObject.get("roleId");
        List<String> permissions = (List<String>) jsonObject.get("permissions");
        permissionService.updateRolePermission(roleId, permissions);
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "成功");
        map.put("errno", 0);
        return map;
    }













    //    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
