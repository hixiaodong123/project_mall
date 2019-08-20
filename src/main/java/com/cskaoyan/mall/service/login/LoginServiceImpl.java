package com.cskaoyan.mall.service.login;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoginServiceImpl implements LoginService{
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    PermissionMapper permissionMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsProductMapper goodsProductMapper;
    @Autowired
    UserMapper userMapper;
    @Override
    public Admin queryUserMessage(String username) {
        Admin admin = adminMapper.queryUserByName(username);
        return admin;
    }

    @Override
    public List<String> queryUserPermsByName(Admin admin) {
        int[] ids = admin.getRoleIds();
        /*int[] ids = adminMapper.selectRolesIdByName(admin.getUsername());*/
        ArrayList<String> list = new ArrayList<>();
        for (int id : ids) {
            List<String> s = permissionMapper.queryPermissionsByRolesId(id);
            for (String s1 : s) {
                if(!s1.equals("*")){
                    String s2 = s1.replace(':', '/');
                    if(s2.contains("creat")||s2.contains("delete")||s2.contains("update")||s2.contains("permissions")||
                            s2.contains("reply")||s2.contains("ship")||s2.contains("storage/read")||s2.contains("refund")){
                        String s3 = "POST /" + s2;
                        list.add(s3);
                    }else{
                        String s3 = "GET /" + s2;
                        list.add(s3);
                    }
                }else{
                    list.add(s1);
                }
            }
        }
        return list;
    }

    @Override
    public List<String> queryUserRolesByName(Admin admin) {
        int[] ids = admin.getRoleIds();
        ArrayList<String> list = new ArrayList<>();
        for (int id : ids) {
            String s = roleMapper.queryRolesNameById(id);
            list.add(s);
        }
        return list;
    }

    @Override
    public Map<String, Object> getTotals() {
        int goodsNum = goodsMapper.selectAllGoodsNum();
        int orderNum = orderMapper.queryAllOrderNum();
        int goodsProductNum = goodsProductMapper.queryAllGoodsProductNum();
        int userNum = userMapper.queryAllUserNum();
        HashMap<String, Object> map = new HashMap<>();
        map.put("goodsTotal",goodsNum);
        map.put("orderTotal",orderNum);
        map.put("productTotal",goodsProductNum);
        map.put("userTotal",userNum);
        return map;
    }
}
