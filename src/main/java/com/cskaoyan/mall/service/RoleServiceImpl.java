package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Role;
import com.cskaoyan.mall.bean.RoleExample;
import com.cskaoyan.mall.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public boolean insertRole(Role role) {
        return false;
    }

    @Override
    public boolean deleteRoleById(String roleId) {
        return false;
    }

    @Override
    public boolean deleteRoleByIds(String[] roleIds) {
        return false;
    }

    @Override
    public boolean updateRole(Role role) {
        return false;
    }

    @Override
    public List<Role> listRoles() {
        RoleExample roleExample = new RoleExample();
        return roleMapper.selectByExample(roleExample);
    }
    @Override
    public List<Role> listRolesBySort(String sort) {
        RoleExample roleExample = new RoleExample();
        roleExample.setOrderByClause(sort);
        return roleMapper.selectByExample(roleExample);
    }

    @Override
    public Role listRoleById(String roleId) {
        return null;
    }

    @Override
    public List<Role> listRolesByLikeId(String roleId) {
        return null;
    }

    @Override
    public List<Role> listRolesByLikeType(String roleId) {
        return null;
    }
}
