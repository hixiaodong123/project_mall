package com.cskaoyan.mall.service.admin;

import com.cskaoyan.mall.bean.Admin;

import java.util.List;
import java.util.Map;

public interface AdminService {

    boolean insertAdmin(Admin admin);

    boolean deleteAdminById(int adminId);

    boolean deleteAdminByIds(int[] adminIds);

    boolean updateAdmin(Admin admin);

    List<Map> listAdmins();

    List<Map> listAdminsBySort(String orderBy);

    Admin listAdminById(int adminId);

    List<Map> listAdminsByLikeId(int adminId);

    List<Map> listAdminsByLikeName(String username, String orderBy);


    Admin listAdminByName(String username);
}
