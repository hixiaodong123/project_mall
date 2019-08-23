package com.cskaoyan.mall.controller;

import com.alibaba.fastjson.JSONObject;
import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.User;
import com.cskaoyan.mall.bean.login.BaseRespModel;
import com.cskaoyan.mall.bean.login.LoginBean;
import com.cskaoyan.mall.bean.login.UserInfo;
import com.cskaoyan.mall.bean.login.UserToken;
import com.cskaoyan.mall.bean.retister.UserRegister;
import com.cskaoyan.mall.realm.MallToken;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.login.LoginService;
import com.cskaoyan.mall.service.user.UserService;
import com.cskaoyan.mall.utils.MD5Utils;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import com.cskaoyan.mall.utils.wx_util.UserTokenManager;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WXLoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    UserService userService;

    @Autowired
    LogService logService;

    @RequestMapping("wx/auth/login")
    public BaseRespModel login(@RequestBody LoginBean loginBean, HttpServletRequest request) {
        BaseRespModel<Object> baseRespModel = new BaseRespModel<>();
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
//        password = MD5Utils.getMd5(password, username);
        MallToken mallToken = new MallToken(username, password, "user");

        User user1 = userService.selectByUsernameAndPassword(username, password);
        UserToken userToken = UserTokenManager.generateToken(user1.getId());
        Map<Object, Object> result = new HashMap<Object, Object>();
        result.put("token", userToken.getToken());
        result.put("tokenExpire", userToken.getExpireTime().toString());

        Subject subject = SecurityUtils.getSubject();
        //subject.getSession().setAttribute("user",loginBean);
        try {
            subject.login(mallToken);
        } catch (AuthenticationException e) {
            //type: 0:一般操作，1:安全操作,2：订单操作，3：其他操作；status:成功或者失败
//            Log log = new Log(username,request.getRemoteAddr(),1,"登录",false,"用户账号或密码不正确");
//            logService.insertLog(log);
            baseRespModel.setErrno(605);
            baseRespModel.setErrmsg("用户账号或密码不正确");
            baseRespModel.setData(null);
            return baseRespModel;
        }
        User user = userService.selectByUsername(username);
        int userId = user.getId();
        user.setLastLoginTime(new Date());
        userService.update(user);
        String avatar = user.getAvatar();
        HashMap<String, Object> map = new HashMap<>();
        map.put("avatarUrl",avatar);
        map.put("nickName",username);
        result.put("userInfo",map);
        baseRespModel.setErrno(0);
        baseRespModel.setErrmsg("成功");
        baseRespModel.setData(result);
        return baseRespModel;
    }
//    @RequestMapping(value = "wx/auth/info")
//    public Map<String,Object> info(){
//        String username = (String) SecurityUtils.getSubject().getPrincipal();
//        //根据username去查询 role和permission信息
//        BaseRespModel<UserInfo> baseRespModel = new BaseRespModel<>();
//        Admin admin = loginService.queryUserMessage(username);
//        List<String> perms = loginService.queryUserPermsByName(admin);
//        List<String> roles = loginService.queryUserRolesByName(admin);
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("avatar",admin.getAvatar());
//        map.put("name",admin.getUsername());
//        map.put("perms",perms);
//        map.put("roles",roles);
//        return ReturnMapUntil.returnMap(map,"成功",0);
//    }
//
//    @RequestMapping("wx/dashboard")
//    public Map<String,Object> dashBoard(){
//        Map<String, Object> map = loginService.getTotals();
//        return ReturnMapUntil.returnMap(map,"成功",0);
//    }

    @RequestMapping("wx/auth/register")
    public Map<String, Object> reigster(@RequestBody UserRegister userRegister) {

        String username = userRegister.getUsername();
        String password = userRegister.getPassword();
        String mobile = userRegister.getMobile();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setMobile(mobile);
        Subject subject = SecurityUtils.getSubject();
        Serializable id = subject.getSession().getId();
        boolean flag = userService.insert(user);
        if (!flag) {
            Map<String, Object> map = new HashMap<>();
            map.put("errmsg", "已存在同名用户");
            map.put("errno", 0);
            map.put("data", id);
            return map;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "注册成功");
        map.put("errno", 0);
        map.put("data", id);
        return map;
    }

    @RequestMapping("wx/auth/regCaptcha")
    public Map<String, Object> regCaptcha(@RequestBody JSONObject jsonObject) {
        Map<String, Object> map = new HashMap<>();
        map.put("errmsg", "验证码已发送");
        map.put("errno", 0);
        return map;
    }


    @RequestMapping("wx/auth/logout")
    public Map<String, Object> logout(HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        String username = String.valueOf(subject.getPrincipals());
        User user = userService.selectByUsername(username);
        //user.setLastLoginIp(request.getRemoteAddr());
        userService.update(user);
        SecurityUtils.getSubject().logout();
        return ReturnMapUntil.returnMap(null, "成功", 0);
    }

}
