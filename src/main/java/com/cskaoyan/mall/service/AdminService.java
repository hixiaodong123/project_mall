package com.cskaoyan.mall.service;

import com.cskaoyan.mall.bean.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    boolean insertAdmin(Admin admin);

    boolean deleteAdminById(String adminId);

    boolean deleteAdminByIds(String[] adminIds);

    boolean updateAdmin(Admin admin);

    List<Map> listAdmins();

    List<Map> listAdminsBySort(String orderBy);

    Admin listAdminById(String adminId);

    List<Map> listAdminsByLikeId(String adminId);

    List<Map> listAdminsByLikeName(String username, String orderBy);
    

}
