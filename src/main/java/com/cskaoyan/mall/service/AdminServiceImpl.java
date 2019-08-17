package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminExample;
import com.cskaoyan.mall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean insertAdmin(Admin admin) {
        return false;
    }

    @Override
    public boolean deleteAdminById(String adminId) {
        return false;
    }

    @Override
    public boolean deleteAdminByIds(String[] adminIds) {
        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        return false;
    }

    @Override
    public List<Map> listAdmins() {
        return null;
    }



    @Override
    public List<Map> listAdminsBySort(String orderBy) {
        AdminExample adminExample = new AdminExample();
        adminExample.setOrderByClause(orderBy);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return returnMap(admins);
    }

    private List<Map> returnMap(List<Admin> admins) {
        List<Map> list = new ArrayList<>();
        for (Admin admin : admins) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", admin.getId());
            map.put("username", admin.getUsername());
            map.put("avatar", admin.getAvatar());
            map.put("roleIds", admin.getRoleIds());
            list.add(map);
        }
        return list;
    }

    @Override
    public Admin listAdminById(String adminId) {
        return null;
    }

    @Override
    public List<Map> listAdminsByLikeId(String adminId) {
        return null;
    }

    @Override
    public List<Map> listAdminsByLikeName(String username, String orderBy) {
        if ("".equals(username)) return listAdminsBySort(orderBy);
        AdminExample adminExample = new AdminExample();
        username = "%" + username + "%";
        adminExample.createCriteria().andUsernameLike(username);
        adminExample.setOrderByClause(orderBy);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return returnMap(admins);
    }
}
