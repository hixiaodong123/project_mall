package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.login.BaseRespModel;
import com.cskaoyan.mall.bean.login.LoginBean;
import com.cskaoyan.mall.bean.login.UserInfo;
import com.cskaoyan.mall.service.login.LoginService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @RequestMapping("admin/auth/login")
    public BaseRespModel login(@RequestBody LoginBean loginBean){
        BaseRespModel<Object> baseRespModel = new BaseRespModel<>();
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        //String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
        } catch (AuthenticationException e) {
            //e.printStackTrace();
            baseRespModel.setErrno(605);
            baseRespModel.setErrmsg("用户账号或密码不正确");
            return baseRespModel;
        }
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        Serializable id = subject.getSession().getId();
        baseRespModel.setData(id);
        return baseRespModel;

    }
    @RequestMapping(value = "admin/auth/info")
    public Map<String,Object> info(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        //根据username去查询 role和permission信息
        BaseRespModel<UserInfo> baseRespModel = new BaseRespModel<>();
        Admin admin = loginService.queryUserMessage(username);
        List<String> perms = loginService.queryUserPermsByName(admin);
        List<String> roles = loginService.queryUserRolesByName(admin);
        HashMap<String, Object> map = new HashMap<>();
        map.put("avator",admin.getAvatar());
        map.put("name",admin.getUsername());
        map.put("perms",perms);
        map.put("roles",roles);
        return ReturnMapUntil.returnMap(map,"成功",0);
    }

    @RequestMapping("admin/dashboard")
    public Map<String,Object> dashBoard(){
        Map<String, Object> map = loginService.getTotals();
        return ReturnMapUntil.returnMap(map,"成功",0);
    }
    @RequestMapping("admin/auth/logout")
    public Map<String,Object> logout(){
        SecurityUtils.getSubject().logout();
        return ReturnMapUntil.returnMap(null,"成功",0);
    }

    /*@RequestMapping("user/query")
    @RequiresPermissions("user:query")
    public BaseRespModel queryUser(){

    }
*/
}
