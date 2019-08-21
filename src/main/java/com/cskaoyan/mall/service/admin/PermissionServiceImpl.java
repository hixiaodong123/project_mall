package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Permission;
import com.cskaoyan.mall.bean.PermissionExample;
import com.cskaoyan.mall.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public boolean insertPermission(Permission permission) {
        return 0 != permissionMapper.insert(permission);
    }

    @Override
    public boolean deletePermissionByAutId(int id) {
        return false;
    }

    @Override
    public boolean updatePermission(Permission permission) {
        return false;
    }

    @Override
    public List<Permission> listPermissions() {
        return null;
    }

    @Override
    public List<Permission> listPermissionsByType(byte type) {
        return null;
    }

    @Override
    public List<Permission> listPermissionsById(String id) {
        return null;
    }

    @Override
    public List<Permission> listPermissionByRoleId(int roleId) {
        PermissionExample permissionExample = new PermissionExample();
        permissionExample.createCriteria().andRoleIdEqualTo(roleId).andDeletedEqualTo(false);
        return permissionMapper.selectByExample(permissionExample);
    }

    @Override
    public void updateRolePermission(int roleId, List<String> permissions) {


        //删除全部
        if (permissions.size() == 0) {
            List<Permission> permissions1 = listPermissionByRoleId(roleId);
            if (permissions1.size() == 0) return;
            for (Permission permission : permissions1) {
                permission.setDeleted(true);
                permissionMapper.updateByPrimaryKey(permission);
            }
            return;
        }
        //已有权限不在新的权限里面
        PermissionExample permissionExample2 = new PermissionExample();
        permissionExample2.createCriteria().andRoleIdEqualTo(roleId).andDeletedEqualTo(false).andPermissionNotIn(permissions);
        List<Permission> permissionsDelete = permissionMapper.selectByExample(permissionExample2);
        for (Permission permission : permissionsDelete) {
            permission.setDeleted(true);
            permissionMapper.updateByPrimaryKey(permission);
        }

        //新增权限原表中没有的权限
        for (String permission : permissions) {
            PermissionExample permissionExample = new PermissionExample();
            permissionExample.createCriteria().andPermissionEqualTo(permission).andRoleIdEqualTo(roleId);
            List<Permission> permissionList = permissionMapper.selectByExample(permissionExample);
            if (permissionList == null || permissionList.size() == 0) {
                Permission newPermission = new Permission();
                newPermission.setDeleted(false);
                newPermission.setPermission(permission);
                newPermission.setRoleId(roleId);
                permissionMapper.insert(newPermission);
            }
        }


        //恢复被删除的权限
        PermissionExample permissionExample1 = new PermissionExample();
        permissionExample1.createCriteria().andRoleIdEqualTo(roleId).andPermissionIn(permissions);
        List<Permission> permissionsAdd = permissionMapper.selectByExample(permissionExample1);
        for (Permission permission : permissionsAdd) {
            permission.setDeleted(false);
            permissionMapper.updateByPrimaryKey(permission);
        }


    }
}
