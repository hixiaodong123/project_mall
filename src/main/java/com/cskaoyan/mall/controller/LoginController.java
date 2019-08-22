package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.login.BaseRespModel;
import com.cskaoyan.mall.bean.login.LoginBean;
import com.cskaoyan.mall.bean.login.UserInfo;
import com.cskaoyan.mall.service.login.LoginService;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import com.sun.javafx.util.Logging;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
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
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
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

    //微信登录认证
    @RequestMapping("wx/auth/login")
    public Map<String,Object> wxlogin(@RequestBody LoginBean loginBean){
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        Map<String, Object> map = new HashMap<>();
        Admin admin = loginService.queryUserMessage(username);
        if(admin==null){
            map = ReturnMapUntil.returnMap(null,"账号密码不对",700);
        }else{
            HashMap<String, Object> map2 = new HashMap<>();
            String token= "s6e8ybwkv7isd3n7jdtxe79lak0vojiz";
            map2.put("token",token);
            map2.put("tokenExpire",LocalDateTime.now().plusDays(1));
            HashMap<String, Object> map1 = new HashMap<>();
            map1.put("nickName",username);
            map1.put("avatarUrl",admin.getAvatar());
            map2.put("userInfo",map1);
            map=ReturnMapUntil.returnMap(map2,"成功",0);
        }
        return map;
    }
}
