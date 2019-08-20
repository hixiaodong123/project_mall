package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Role;

import java.util.List;

public interface RoleService {

    boolean insertRole(Role role);

    boolean deleteRoleById(String roleId);

    boolean deleteRoleByIds(String[] roleIds);

    boolean updateRole(Role role);

    List<Role> listRoles();

    List<Role> listRolesBySort(String sort);

    Role listRoleById(String roleId);

    List<Role> listRolesByLikeId(String roleId);

    List<Role> listRolesByLikeType(String roleId);
    

}
