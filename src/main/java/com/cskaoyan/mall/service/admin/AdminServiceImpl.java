package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.AdminExample;
import com.cskaoyan.mall.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public boolean insertAdmin(Admin admin) {
        String username = admin.getUsername();
        if (null == username) return false;
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() == 0) return 0 != adminMapper.insert(admin);
        return false;
    }

    @Override
    public boolean deleteAdminById(int adminId) {
        Admin admin = adminMapper.selectByPrimaryKey(adminId);
        admin.setDeleted(true);
        return 0 != adminMapper.updateByPrimaryKey(admin);
    }

    @Override
    public boolean deleteAdminByIds(int[] adminIds) {
        boolean flag = true;
        for (int adminId : adminIds) {
            Admin admin = adminMapper.selectByPrimaryKey(adminId);
            admin.setDeleted(true);
            flag =  0 != adminMapper.updateByPrimaryKey(admin);
            if (!flag) break;
        }
        return flag;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(admin.getUsername());
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        Admin findAdmin = admins.get(0);
        if (findAdmin.getUsername().equals(admin.getUsername()) && findAdmin.getId() != admin.getId()) return false;
        findAdmin.setAvatar(admin.getAvatar());
        findAdmin.setPassword(admin.getPassword());
        findAdmin.setRoleIds(admin.getRoleIds());
        findAdmin.setUsername(admin.getUsername());
        findAdmin.setUpdateTime(new Date());
        return 0 != adminMapper.updateByPrimaryKey(findAdmin);
    }

    @Override
    public List<Admin> listAdmins() {
        return null;
    }



    @Override
    public List<Map> listAdminsBySort(String orderBy) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andDeletedEqualTo(false);
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
    public Admin listAdminById(int adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public List<Map> listAdminsByLikeId(int adminId) {
        return null;
    }

    @Override
    public List<Map> listAdminsByLikeName(String username, String orderBy) {
        if ("".equals(username)) return listAdminsBySort(orderBy);
        AdminExample adminExample = new AdminExample();
        username = "%" + username + "%";
        adminExample.createCriteria().andUsernameLike(username).andDeletedEqualTo(false);
        adminExample.setOrderByClause(orderBy);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        return returnMap(admins);
    }

    @Override
    public Admin listAdminByName(String username) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andUsernameEqualTo(username);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins.size() == 0) return null;
        return admins.get(0);
    }
}
