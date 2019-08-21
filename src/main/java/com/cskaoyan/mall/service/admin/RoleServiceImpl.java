package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.RoleExample;
import com.cskaoyan.mall.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public boolean insertRole(Role role) {
        return 0 != roleMapper.insert(role);
    }

    @Override
    public boolean deleteRoleById(int id) {
        Role role = roleMapper.selectByPrimaryKey(id);
        role.setDeleted(true);
        return 0 != roleMapper.updateByPrimaryKey(role);
    }

    @Override
    public boolean deleteRoleByIds(int[] ids) {
        return false;
    }

    @Override
    public boolean updateRole(Role role) {
        if (role.getName() == null) return false;
        Role findRole = listRoleByName(role.getName());
        if (findRole == null) {
            findRole = listRoleById(role.getId());
        }
        if (findRole.getName().equals(role.getName()) && findRole.getId() != role.getId()) return false;
        findRole.setDesc(role.getDesc());
        findRole.setName(role.getName());
        return 0 != roleMapper.updateByPrimaryKey(findRole);
    }

    @Override
    public List<Role> listRoles() {
        RoleExample roleExample = new RoleExample();
        return roleMapper.selectByExample(roleExample);
    }
    @Override
    public List<Map> listRolesBySort(String sort) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andDeletedEqualTo(false);
        roleExample.setOrderByClause(sort);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return returnMap(roles);
    }

    private List<Map> returnMap(List<Role> roles) {
        List<Map> list = new ArrayList<>();
        for (Role role: roles) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("addTime", date2String(role.getAddTime()));
            map.put("deleted", role.getDeleted());
            map.put("desc", role.getDesc());
            map.put("enabled", role.getEnabled());
            map.put("id", role.getId());
            map.put("name", role.getName());
            map.put("updateTime", date2String(role.getUpdateTime()));
            list.add(map);
        }
        return list;
    }

    @Override
    public Role listRoleById(int id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public Role listRoleByName(String name) {
        RoleExample roleExample = new RoleExample();
        roleExample.createCriteria().andNameEqualTo(name);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        if (roles.size() == 0) return null;
        return roles.get(0);
    }

    @Override
    public List<Map> listRolesByLikeName(String name, String sort) {
        RoleExample roleExample = new RoleExample();
        name = "%" + name + "%";
        roleExample.createCriteria().andNameLike(name).andDeletedEqualTo(false);
        roleExample.setOrderByClause(sort);
        List<Role> roles = roleMapper.selectByExample(roleExample);
        return returnMap(roles);
    }
    

    //    将数据库中的日期转化为指定格式的字符串
    private String date2String(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
