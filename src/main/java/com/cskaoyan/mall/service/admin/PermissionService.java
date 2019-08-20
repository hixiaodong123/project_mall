package com.cskaoyan.mall.service.admin;



import com.cskaoyan.mall.bean.Permission;

import java.util.List;
import java.util.Map;

public interface PermissionService {

    boolean insertPermission(Permission permission);

    boolean deletePermissionByAutId(int id);

    boolean updatePermission(Permission permission);

    List<Permission> listPermissions();

    List<Permission> listPermissionsByType(byte type);

    List<Permission> listPermissionsById(String id);

    List<Permission> listPermissionByRoleId(int roleId);

    void updateRolePermission(int roleId, List<String> permissions);
}
