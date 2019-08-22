package com.cskaoyan.mall.controller;

import com.cskaoyan.mall.bean.Admin;
import com.cskaoyan.mall.bean.Log;
import com.cskaoyan.mall.bean.login.BaseRespModel;
import com.cskaoyan.mall.bean.login.LoginBean;
import com.cskaoyan.mall.bean.login.UserInfo;
import com.cskaoyan.mall.realm.MallToken;
import com.cskaoyan.mall.service.admin.LogService;
import com.cskaoyan.mall.service.login.LoginService;
import com.cskaoyan.mall.utils.MD5Utils;
import com.cskaoyan.mall.utils.ReturnMapUntil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminLoginController {

    @Autowired
    LoginService loginService;

    @Autowired
    LogService logService;

    @RequestMapping("admin/auth/login")
    public BaseRespModel login(@RequestBody LoginBean loginBean, HttpServletRequest request){
        BaseRespModel<Object> baseRespModel = new BaseRespModel<>();
        String username = loginBean.getUsername();
        String password = loginBean.getPassword();
        if (password == null) {
            Log log = new Log("匿名用户",request.getRemoteAddr(),1,"登录",false,"用户账号或密码不正确");
            logService.insertLog(log);
            baseRespModel.setErrno(605);
            baseRespModel.setErrmsg("用户账号或密码不正确");
            return baseRespModel;
        }
//        password = MD5Utils.getMd5(password, username);
        MallToken mallToken = new MallToken(username, password, "admin");
        //String hashpw = BCrypt.hashpw(password, BCrypt.gensalt());
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(mallToken);
        } catch (AuthenticationException e) {
            //type: 0:一般操作，1:安全操作,2：订单操作，3：其他操作；status:成功或者失败
            Log log = new Log(username,request.getRemoteAddr(),1,"登录",false,"用户账号或密码不正确");
            logService.insertLog(log);
            baseRespModel.setErrno(605);
            baseRespModel.setErrmsg("用户账号或密码不正确");
            return baseRespModel;
        }
        Log log = new Log(username,request.getRemoteAddr(),1,"登录",true,"");
        logService.insertLog(log);
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
        map.put("avatar",admin.getAvatar());
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
    public Map<String,Object> logout(HttpServletRequest request){

        Subject subject = SecurityUtils.getSubject();
        String username = String.valueOf(subject.getPrincipals());
        Log log = new Log(username,request.getRemoteAddr(),1,
                "退出系统",true,"成功退出");
        logService.insertLog(log);

        SecurityUtils.getSubject().logout();
        return ReturnMapUntil.returnMap(null,"成功",0);
    }

    /*@RequestMapping("user/query")
    @RequiresPermissions("user:query")
    public BaseRespModel queryUser(){

    }
*/
}
