package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Role;

import java.util.List;
import java.util.Map;

public interface RoleService {

    boolean insertRole(Role role);

    boolean deleteRoleById(int id);

    boolean deleteRoleByIds(int[] ids);

    boolean updateRole(Role role);

    List<Role> listRoles();

    List<Map> listRolesBySort(String sort);

    Role listRoleById(int id);

    List<Map> listRolesByLikeName(String name, String sort);

    Role listRoleByName(String name);
}
